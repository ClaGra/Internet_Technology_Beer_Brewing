package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.InventoryService;
import ch.fhnw.brew.data.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<?> addInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        try {
            return ResponseEntity.ok(inventoryService.editInventory(id, inventory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Integer id) {
        try {
            inventoryService.deleteInventory(id);
            return ResponseEntity.ok("Inventory deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventory(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(inventoryService.getInventory(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateInventoryAmount(@RequestParam String category, @RequestParam int amount) {
        return ResponseEntity.ok(inventoryService.updateInventoryAmount(category, amount));
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Integer>> getInventorySummary() {
        return ResponseEntity.ok(inventoryService.getTotalInventoryByCategory());
    }
}
