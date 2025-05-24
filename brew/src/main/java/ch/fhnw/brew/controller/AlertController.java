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

    @GetMapping
    public ResponseEntity<List<Alert>> getOpenAlerts() {
        List<Alert> openAlerts = alertService.getOpenAlerts();
        if (openAlerts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(openAlerts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Integer id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}
