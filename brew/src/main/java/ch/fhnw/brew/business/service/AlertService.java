package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Alert;
import ch.fhnw.brew.data.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public void triggerLowInventoryAlert(String category, int currentAmount) {
        String keywordPrefix = "Inventory for " + category + " is below threshold";

        boolean exists = alertRepository.existsByAlertTriggerStartingWith(keywordPrefix);

        if (!exists) {
            Alert alert = new Alert();
            alert.setAlertName("Low Inventory");
            alert.setAlertTrigger(keywordPrefix + ": " + currentAmount + " units.");
            alertRepository.save(alert);
        }
    }

    @Transactional
    public void resolveAlertIfRecovered(String category, int currentAmount) {
        if (currentAmount >= 72) {
            String keywordPrefix = "Inventory for " + category + " is below threshold";
            alertRepository.deleteByAlertTriggerStartingWith(keywordPrefix);
        }
    }

    public List<Alert> getOpenAlerts() {
        return alertRepository.findAll();
    }

    @Transactional
    public void deleteAlert(Integer id) {
        alertRepository.deleteById(id);
    }
}
