package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.AlertService;
import ch.fhnw.brew.data.domain.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<Alert> addAlert(@RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.addAlert(alert));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAlert(@PathVariable Integer id) {
        alertService.deleteAlert(id);
        return ResponseEntity.ok(Map.of("message", "Alert deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable Integer id) {
        return ResponseEntity.ok(alertService.getAlert(id));
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
