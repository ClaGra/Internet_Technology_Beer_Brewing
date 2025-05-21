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

@Service
public class BottlingService {

    @Autowired
    private BottlingRepository bottlingRepository;

    @Autowired
    private BrewingProtocolRepository brewingProtocolRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Bottling addBottling(Bottling bottling) {
        Integer batchNr = bottling.getBrewingProtocol().getBatchNr();

        BrewingProtocol fullProtocol = brewingProtocolRepository.findById(batchNr)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));

        bottling.setBrewingProtocol(fullProtocol);
        setExpirationDate(bottling);

        Bottling saved = bottlingRepository.save(bottling);
        inventoryService.addInventoryFromBottling(saved);
        return saved;
    }

    @Transactional
    public Bottling editBottling(Integer id, Bottling updatedBottling) {
        Bottling existing = bottlingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bottling not found"));

        int oldAmount = existing.getAmount();
        int newAmount = updatedBottling.getAmount();
        int delta = newAmount - oldAmount;

        Integer batchNr = existing.getBrewingProtocol().getBatchNr();
        List<Inventory> inventories = inventoryRepository.findByBatchNr(batchNr);

        if (inventories.isEmpty()) {
            throw new NotFoundException("Inventory for batch " + batchNr + " not found");
        }

        Inventory inventory = inventories.get(0);
        int currentInventory = inventory.getInventoryAmount();
        int projectedInventory = currentInventory + delta;

        // Get how much has already been ordered from this batch
        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(batchNr);
        if (orderedAmount == null) orderedAmount = 0;

        // Prevent reducing inventory below ordered quantity
        if (projectedInventory < orderedAmount) {
            throw new IllegalStateException(
                "Cannot reduce bottling: would drop inventory below ordered amount (" + orderedAmount + ")"
            );
        }

        // Apply inventory update
        inventory.setInventoryAmount(projectedInventory);
        inventoryRepository.save(inventory);

        // Update bottling fields
        existing.setAmount(newAmount);
        existing.setBottlingDate(updatedBottling.getBottlingDate());
        existing.setFinalGravity(updatedBottling.getFinalGravity());

        BrewingProtocol fullProtocol = brewingProtocolRepository.findById(batchNr)
                .orElseThrow(() -> new NotFoundException("Brewing Protocol not found"));
        existing.setBrewingProtocol(fullProtocol);

        setExpirationDate(existing);
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
        int currentInventory = inventory.getInventoryAmount();
        int remainingAfterDeletion = currentInventory - existing.getAmount();

        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(batchNr);
        if (orderedAmount == null) orderedAmount = 0;

        if (remainingAfterDeletion < orderedAmount) {
            throw new IllegalStateException(
                "Cannot delete bottling: would drop inventory below ordered amount (" + orderedAmount + ")"
            );
        }

        inventoryService.removeInventoryForBatch(batchNr);
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
}
