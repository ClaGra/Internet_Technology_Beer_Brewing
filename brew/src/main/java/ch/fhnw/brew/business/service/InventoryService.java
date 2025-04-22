package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Bottling;
import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        Inventory saved = inventoryRepository.save(inventory);
        checkAlertThreshold(saved.getInventoryCategoryName());
        return saved;
    }

    public Inventory editInventory(Integer id, Inventory updated) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        existing.setInventoryAmount(updated.getInventoryAmount());
        existing.setInventoryCategoryName(updated.getInventoryCategoryName());
        existing.setBatchNr(updated.getBatchNr());
        existing.setExpirationDate(updated.getExpirationDate());

        Inventory saved = inventoryRepository.save(existing);
        checkAlertThreshold(saved.getInventoryCategoryName());
        return saved;
    }

    public void deleteInventory(Integer id) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        inventoryRepository.delete(existing);
        checkAlertThreshold(existing.getInventoryCategoryName());
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

        Inventory target = (latest != null) ? latest : new Inventory();
        target.setInventoryCategoryName(category);
        target.setInventoryAmount(Optional.ofNullable(target.getInventoryAmount()).orElse(0) + change);

        if (target.getInventoryAmount() < 0) {
            throw new RuntimeException("Inventory would go below zero for category: " + category);
        }

        Inventory saved = inventoryRepository.save(target);
        checkAlertThreshold(category);
        return saved;
    }

    public Inventory addInventoryFromBottling(Bottling bottling) {
        Inventory inventory = new Inventory();
        inventory.setInventoryCategoryName(bottling.getBrewingProtocol().getRecipe().getRecipeName());
        inventory.setInventoryAmount(bottling.getAmount());
        inventory.setBatchNr(bottling.getBrewingProtocol().getBatchNr());
        inventory.setExpirationDate(bottling.getExpirationDate());

        Inventory saved = inventoryRepository.save(inventory);
        checkAlertThreshold(inventory.getInventoryCategoryName());
        return saved;
    }

    public void removeInventoryForBatch(Integer batchNr) {
        List<Inventory> toRemove = inventoryRepository.findByBatchNr(batchNr);
        for (Inventory i : toRemove) {
            inventoryRepository.delete(i);
            checkAlertThreshold(i.getInventoryCategoryName());
        }
    }

    public Map<String, Integer> getTotalInventoryByCategory() {
        return inventoryRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Inventory::getInventoryCategoryName,
                        Collectors.summingInt(Inventory::getInventoryAmount)
                ));
    }

    private void checkAlertThreshold(String category) {
        int total = getTotalInventoryByCategory().getOrDefault(category, 0);
        if (total < 72) {
            alertService.triggerLowInventoryAlert(category, total);
        }
    }
}
