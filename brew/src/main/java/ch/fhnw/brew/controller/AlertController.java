package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.AlertService;
import ch.fhnw.brew.data.domain.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<?> addAlert(@RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.addAlert(alert));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable Integer id) {
        try {
            alertService.deleteAlert(id);
            return ResponseEntity.ok("Alert deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlert(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(alertService.getAlert(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}
