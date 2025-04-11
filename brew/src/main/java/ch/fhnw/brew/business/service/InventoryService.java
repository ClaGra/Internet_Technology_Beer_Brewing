package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Inventory;
import ch.fhnw.brew.data.repository.InventoryRepository;
import ch.fhnw.brew.data.domain.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private AlertService alertService; 

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory editInventory(Integer id, Inventory updated) throws Exception {
        Inventory existing = inventoryRepository.findById(id).orElseThrow(() -> new Exception("Inventory not found"));
        existing.setInventoryAmount(updated.getInventoryAmount());
        existing.setInventoryCategoryName(updated.getInventoryCategoryName());
        return inventoryRepository.save(existing);
    }

    public void deleteInventory(Integer id) throws Exception {
        if (!inventoryRepository.existsById(id)) {
            throw new Exception("Inventory not found");
        }
        inventoryRepository.deleteById(id);
    }

    public Inventory getInventory(Integer id) throws Exception {
        return inventoryRepository.findById(id).orElseThrow(() -> new Exception("Inventory not found"));
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory updateInventoryAmount(String category, int change) {
        Inventory inventory = inventoryRepository.findByInventoryCategoryName(category);
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setInventoryCategoryName(category);
            inventory.setInventoryAmount(0);
        }

        inventory.setInventoryAmount(inventory.getInventoryAmount() + change);
        inventory = inventoryRepository.save(inventory);

        // Trigger alert if inventory is critically low
        if (inventory.getInventoryAmount() < 5) {
            Alert alert = new Alert();
            alert.setAlertName("Low Inventory");
            alert.setAlertTrigger("Inventory for " + category + " is below 5 units.");
            alertService.addAlert(alert);
        }

        return inventory;
    }
}
