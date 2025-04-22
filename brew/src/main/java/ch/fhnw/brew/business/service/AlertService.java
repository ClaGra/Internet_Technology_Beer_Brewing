package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Alert;
import ch.fhnw.brew.data.repository.AlertRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert addAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public void deleteAlert(Integer id) {
        Alert existing = alertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Alert not found"));
        alertRepository.delete(existing);
    }

    public Alert getAlert(Integer id) {
        return alertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Alert not found"));
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void triggerLowInventoryAlert(String category, int currentAmount) {
        Alert alert = new Alert();
        alert.setAlertName("Low Inventory");
        alert.setAlertTrigger("Inventory for " + category + " is below threshold: " + currentAmount + " units.");
        alertRepository.save(alert);
    }
}
