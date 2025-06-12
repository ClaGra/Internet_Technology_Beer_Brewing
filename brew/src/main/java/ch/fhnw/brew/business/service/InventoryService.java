package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.data.repository.OrderRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AlertService alertService;

    public Inventory editInventory(Integer id, Inventory updated) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        // Prevent changing batchNr or category
        if (!Objects.equals(existing.getBatchNr(), updated.getBatchNr())) {
            throw new IllegalStateException("Cannot change batch number directly in inventory. Update bottling instead.");
        }
        if (!Objects.equals(existing.getInventoryCategoryName(), updated.getInventoryCategoryName())) {
            throw new IllegalStateException("Cannot change inventory category directly. Update bottling instead.");
        }

        Integer batchNr = existing.getBatchNr();
        String category = existing.getInventoryCategoryName();
        int oldAmount = existing.getInventoryAmount();
        int newAmount = updated.getInventoryAmount();
        int delta = newAmount - oldAmount;

        int currentTotal = inventoryRepository.findByBatchNr(batchNr).stream()
                .mapToInt(Inventory::getInventoryAmount).sum();
        int projectedTotal = currentTotal + delta;

        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(batchNr);
        if (orderedAmount == null) orderedAmount = 0;

        if (projectedTotal < orderedAmount) {
            throw new IllegalStateException(
                "Cannot reduce inventory: would drop available stock below ordered amount (" + orderedAmount + ")"
            );
        }

        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        existing.setInventoryAmount(newAmount);
        existing.setExpirationDate(updated.getExpirationDate());

        Inventory saved = inventoryRepository.save(existing);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);
        return saved;
    }


     @PreAuthorize("hasRole('ADMIN')")
    public void deleteInventory(Integer id) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        String category = existing.getInventoryCategoryName();
        Integer batchNr = existing.getBatchNr();

        int totalInventoryForBatch = inventoryRepository.findByBatchNr(batchNr)
                .stream()
                .mapToInt(Inventory::getInventoryAmount)
                .sum();

        Integer orderedAmount = orderRepository.getTotalOrderedAmountByBatch(batchNr);
        if (orderedAmount == null) orderedAmount = 0;

        int remainingAfterDeletion = totalInventoryForBatch - existing.getInventoryAmount();

        if (remainingAfterDeletion < orderedAmount) {
            throw new IllegalStateException(
                    "Cannot delete inventory: would drop available stock below ordered amount (" + orderedAmount + ")"
            );
        }

        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);
        inventoryRepository.delete(existing);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);
    }

    public Inventory getInventory(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Map<String, Integer> getTotalInventoryByCategory() {
        return inventoryRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Inventory::getInventoryCategoryName,
                        Collectors.summingInt(Inventory::getInventoryAmount)
                ));
    }

    private void handleThresholdChange(String category, int totalBefore, int totalAfter) {
        if (totalBefore >= 72 && totalAfter < 72) {
            alertService.triggerLowInventoryAlert(category, totalAfter);
        } else if (totalBefore < 72 && totalAfter >= 72) {
            alertService.resolveAlertIfRecovered(category, totalAfter);
        }
    }
}
