package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Bottling;
import ch.fhnw.brew.data.domain.BrewingProtocol;
import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.repository.BottlingRepository;
import ch.fhnw.brew.data.repository.BrewingProtocolRepository;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.data.repository.OrderRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BottlingService {

    @Autowired
    private BottlingRepository bottlingRepository;

    @Autowired
    private BrewingProtocolRepository brewingProtocolRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AlertService alertService;

    @Transactional
    public Bottling addBottling(Bottling bottling) {
        Integer batchNr = bottling.getBrewingProtocol().getBatchNr();

        BrewingProtocol fullProtocol = brewingProtocolRepository.findById(batchNr)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));

        boolean alreadyExists = bottlingRepository.findAll().stream()
                .anyMatch(b -> b.getBrewingProtocol().getBatchNr().equals(batchNr));

        if (alreadyExists) {
            throw new IllegalStateException("A bottling already exists for batch number " + batchNr);
        }

        bottling.setBrewingProtocol(fullProtocol);
        setExpirationDate(bottling);

        Bottling saved = bottlingRepository.save(bottling);

        String category = fullProtocol.getRecipe().getRecipeName();
        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        Inventory inventory = new Inventory();
        inventory.setInventoryCategoryName(category);
        inventory.setInventoryAmount(saved.getAmount());
        inventory.setBatchNr(batchNr);
        inventory.setExpirationDate(saved.getExpirationDate());

        inventoryRepository.save(inventory);

        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);

        return saved;
    }

    @Transactional
    public Bottling editBottling(Integer id, Bottling updatedBottling) {
        Bottling existing = bottlingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bottling not found"));

        Integer oldBatchNr = existing.getBrewingProtocol().getBatchNr();
        Integer newBatchNr = updatedBottling.getBrewingProtocol().getBatchNr();

        if (!oldBatchNr.equals(newBatchNr)) {
            boolean batchInUse = bottlingRepository.findAll().stream()
                    .anyMatch(b -> !b.getBottlingID().equals(id) && b.getBrewingProtocol().getBatchNr().equals(newBatchNr));

            if (batchInUse) {
                throw new IllegalStateException("A bottling already exists for batch number " + newBatchNr);
            }

            Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(oldBatchNr);
            if (orderedAmount != null && orderedAmount > 0) {
                throw new IllegalStateException("Cannot change the recipe for this bottling because orders have already been placed for this batch.");
            }
        }

        List<Inventory> inventories = inventoryRepository.findByBatchNr(oldBatchNr);
        if (inventories.isEmpty()) {
            throw new NotFoundException("Inventory for batch " + oldBatchNr + " not found");
        }

        Inventory inventory = inventories.get(0);
        int currentInventory = inventory.getInventoryAmount();
        int delta = updatedBottling.getAmount() - existing.getAmount();
        int projectedInventory = currentInventory + delta;

        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(oldBatchNr);
        if (orderedAmount == null) orderedAmount = 0;

        if (projectedInventory < orderedAmount) {
            throw new IllegalStateException("Cannot reduce bottling: would drop inventory below ordered amount (" + orderedAmount + ")");
        }

        Map<String, Integer> inventoryBefore = getTotalInventoryByCategory();

        BrewingProtocol newProtocol = brewingProtocolRepository.findById(newBatchNr)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));

        existing.setAmount(updatedBottling.getAmount());
        existing.setBottlingDate(updatedBottling.getBottlingDate());
        existing.setFinalGravity(updatedBottling.getFinalGravity());
        existing.setBrewingProtocol(newProtocol);
        setExpirationDate(existing);

        inventory.setInventoryAmount(projectedInventory);
        inventory.setBatchNr(newBatchNr);
        inventory.setInventoryCategoryName(newProtocol.getRecipe().getRecipeName());
        inventoryRepository.save(inventory);

        Map<String, Integer> inventoryAfter = getTotalInventoryByCategory();
        String category = inventory.getInventoryCategoryName();

        handleThresholdChange(
                category,
                inventoryBefore.getOrDefault(category, 0),
                inventoryAfter.getOrDefault(category, 0)
        );

        return bottlingRepository.save(existing);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBottling(Integer id) {
        Bottling existing = bottlingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bottling not found"));

        Integer batchNr = existing.getBrewingProtocol().getBatchNr();
        List<Inventory> inventories = inventoryRepository.findByBatchNr(batchNr);

        if (inventories.isEmpty()) {
            throw new NotFoundException("Inventory for batch " + batchNr + " not found");
        }

        Inventory inventory = inventories.get(0);

        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(batchNr);
        if (orderedAmount == null) orderedAmount = 0;

        int totalInventory = getTotalInventoryByBatch(batchNr);
        int remainingAfterDeletion = totalInventory - inventory.getInventoryAmount();

        if (remainingAfterDeletion < orderedAmount) {
            throw new IllegalStateException("Cannot delete bottling: would drop inventory below ordered amount (" + orderedAmount + ")");
        }

        String category = inventory.getInventoryCategoryName();
        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        inventoryRepository.delete(inventory);
        inventoryRepository.flush(); // Ensure deletion is committed

        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);

        bottlingRepository.delete(existing);
    }

    public Bottling getBottling(Integer id) {
        return bottlingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bottling not found"));
    }

    public List<Bottling> getAllBottlings() {
        return bottlingRepository.findAll();
    }

    private void setExpirationDate(Bottling bottling) {
        if (bottling.getBottlingDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(bottling.getBottlingDate());
            calendar.add(Calendar.DAY_OF_YEAR, 180);
            bottling.setExpirationDate(calendar.getTime());
        }
    }

    private Map<String, Integer> getTotalInventoryByCategory() {
        return inventoryRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Inventory::getInventoryCategoryName,
                        Collectors.summingInt(Inventory::getInventoryAmount)
                ));
    }

    private int getTotalInventoryByBatch(Integer batchNr) {
        return inventoryRepository.findByBatchNr(batchNr).stream()
                .mapToInt(Inventory::getInventoryAmount)
                .sum();
    }

    private void handleThresholdChange(String category, int totalBefore, int totalAfter) {
        if (totalBefore >= 72 && totalAfter < 72) {
            alertService.triggerLowInventoryAlert(category, totalAfter);
        } else if (totalBefore < 72 && totalAfter >= 72) {
            alertService.resolveAlertIfRecovered(category, totalAfter);
        }
    }
}
