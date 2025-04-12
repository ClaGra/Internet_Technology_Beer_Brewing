package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Alert;
import ch.fhnw.brew.data.repository.AlertRepository;
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

    public void deleteAlert(Integer id) throws Exception {
        if (!alertRepository.existsById(id)) {
            throw new Exception("Alert not found");
        }
        alertRepository.deleteById(id);
    }

    public Alert getAlert(Integer id) throws Exception {
        return alertRepository.findById(id).orElseThrow(() -> new Exception("Alert not found"));
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
