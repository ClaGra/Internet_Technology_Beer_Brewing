package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.BottlingService;
import ch.fhnw.brew.data.domain.Bottling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bottlings")
public class BottlingController {

    @Autowired
    private BottlingService bottlingService;

    @PostMapping
    public ResponseEntity<?> addBottling(@RequestBody Bottling bottling) {
        return ResponseEntity.ok(bottlingService.addBottling(bottling));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBottling(@PathVariable Integer id, @RequestBody Bottling bottling) {
        try {
            return ResponseEntity.ok(bottlingService.editBottling(id, bottling));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBottling(@PathVariable Integer id) {
        try {
            bottlingService.deleteBottling(id);
            return ResponseEntity.ok("Bottling deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBottling(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(bottlingService.getBottling(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Bottling> getAllBottlings() {
        return bottlingService.getAllBottlings();
    }
}
