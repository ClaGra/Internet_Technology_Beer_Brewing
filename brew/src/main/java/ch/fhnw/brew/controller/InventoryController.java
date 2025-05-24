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
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> editInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.editInventory(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(Map.of("message", "Inventory deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventory(id));
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Integer>> getInventorySummary() {
        return ResponseEntity.ok(inventoryService.getTotalInventoryByCategory());
    }
}
