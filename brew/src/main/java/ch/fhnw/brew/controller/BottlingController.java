package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.BottlingService;
import ch.fhnw.brew.data.domain.Bottling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bottlings")
public class BottlingController {

    @Autowired
    private BottlingService bottlingService;

    @PostMapping
    public ResponseEntity<Bottling> addBottling(@RequestBody Bottling bottling) {
        return ResponseEntity.ok(bottlingService.addBottling(bottling));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bottling> editBottling(@PathVariable Integer id, @RequestBody Bottling bottling) {
        return ResponseEntity.ok(bottlingService.editBottling(id, bottling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBottling(@PathVariable Integer id) {
        bottlingService.deleteBottling(id);
        return ResponseEntity.ok(Map.of("message", "Bottling deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bottling> getBottling(@PathVariable Integer id) {
        return ResponseEntity.ok(bottlingService.getBottling(id));
    }

    @GetMapping
    public ResponseEntity<List<Bottling>> getAllBottlings() {
        return ResponseEntity.ok(bottlingService.getAllBottlings());
    }
}
