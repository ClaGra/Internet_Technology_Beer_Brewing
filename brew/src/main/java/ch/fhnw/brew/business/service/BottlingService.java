package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Bottling;
import ch.fhnw.brew.data.repository.BottlingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BottlingService {

    @Autowired
    private BottlingRepository bottlingRepository;

    @Autowired
    private InventoryService inventoryService;

    public Bottling addBottling(Bottling bottling) {
        // Set expiration date = bottling date + 180 days
        if (bottling.getBottlingDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(bottling.getBottlingDate());
            calendar.add(Calendar.DAY_OF_YEAR, 180);
            bottling.setExpirationDate(calendar.getTime());
        }

        Bottling saved = bottlingRepository.save(bottling);
        inventoryService.addInventoryFromBottling(saved);
        return saved;
    }

    public Bottling editBottling(Integer id, Bottling updatedBottling) throws Exception {
        Bottling existing = bottlingRepository.findById(id)
            .orElseThrow(() -> new Exception("Bottling not found"));

        // Remove old inventory linked to original batch
        inventoryService.removeInventoryForBatch(existing.getBrewingProtocol().getBatchNr());

        // Recalculate expiration date in case bottling date changed
        if (updatedBottling.getBottlingDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(updatedBottling.getBottlingDate());
            calendar.add(Calendar.DAY_OF_YEAR, 180);
            updatedBottling.setExpirationDate(calendar.getTime());
        }

        Bottling saved = bottlingRepository.save(updatedBottling);
        inventoryService.addInventoryFromBottling(saved);

        return saved;
    }

    public void deleteBottling(Integer id) throws Exception {
        Bottling existing = bottlingRepository.findById(id)
            .orElseThrow(() -> new Exception("Bottling record not found"));

        inventoryService.removeInventoryForBatch(existing.getBrewingProtocol().getBatchNr());
        bottlingRepository.deleteById(id);
    }

    public Bottling getBottling(Integer id) throws Exception {
        return bottlingRepository.findById(id)
            .orElseThrow(() -> new Exception("Bottling record not found"));
    }

    public List<Bottling> getAllBottlings() {
        return bottlingRepository.findAll();
    }
}
