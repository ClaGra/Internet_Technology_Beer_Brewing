package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Bottling;
import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.repository.InventoryRepository;
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
    private AlertService alertService;

    public Inventory addInventory(Inventory inventory) {
        int totalBefore = getTotalInventoryByCategory().getOrDefault(inventory.getInventoryCategoryName(), 0);
        Inventory saved = inventoryRepository.save(inventory);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(inventory.getInventoryCategoryName(), 0);
        handleThresholdChange(inventory.getInventoryCategoryName(), totalBefore, totalAfter);
        return saved;
    }

    public Inventory editInventory(Integer id, Inventory updated) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        String category = existing.getInventoryCategoryName();
        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        existing.setInventoryAmount(updated.getInventoryAmount());
        existing.setInventoryCategoryName(updated.getInventoryCategoryName());
        existing.setBatchNr(updated.getBatchNr());
        existing.setExpirationDate(updated.getExpirationDate());

        Inventory saved = inventoryRepository.save(existing);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(updated.getInventoryCategoryName(), 0);
        handleThresholdChange(updated.getInventoryCategoryName(), totalBefore, totalAfter);
        return saved;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteInventory(Integer id) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        String category = existing.getInventoryCategoryName();
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

    public Inventory updateInventoryAmount(String category, int change) {
        List<Inventory> inventories = inventoryRepository.findByInventoryCategoryName(category);
        Inventory latest = inventories.stream()
                .max(Comparator.comparing(Inventory::getInventoryID))
                .orElse(null);

        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        Inventory target = (latest != null) ? latest : new Inventory();
        target.setInventoryCategoryName(category);
        target.setInventoryAmount(Optional.ofNullable(target.getInventoryAmount()).orElse(0) + change);

        if (target.getInventoryAmount() < 0) {
            throw new RuntimeException("Inventory would go below zero for category: " + category);
        }

        Inventory saved = inventoryRepository.save(target);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);
        return saved;
    }

    public Inventory addInventoryFromBottling(Bottling bottling) {
        String category = bottling.getBrewingProtocol().getRecipe().getRecipeName();
        int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);

        Inventory inventory = new Inventory();
        inventory.setInventoryCategoryName(category);
        inventory.setInventoryAmount(bottling.getAmount());
        inventory.setBatchNr(bottling.getBrewingProtocol().getBatchNr());
        inventory.setExpirationDate(bottling.getExpirationDate());

        Inventory saved = inventoryRepository.save(inventory);
        int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
        handleThresholdChange(category, totalBefore, totalAfter);
        return saved;
    }

    public void removeInventoryForBatch(Integer batchNr) {
        List<Inventory> toRemove = inventoryRepository.findByBatchNr(batchNr);
        for (Inventory i : toRemove) {
            String category = i.getInventoryCategoryName();
            int totalBefore = getTotalInventoryByCategory().getOrDefault(category, 0);
            inventoryRepository.delete(i);
            int totalAfter = getTotalInventoryByCategory().getOrDefault(category, 0);
            handleThresholdChange(category, totalBefore, totalAfter);
        }
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
