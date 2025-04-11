package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Bottling;
import ch.fhnw.brew.data.repository.BottlingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottlingService {

    @Autowired
    private BottlingRepository bottlingRepository;

    @Autowired
    private InventoryService inventoryService;

    public Bottling addBottling(Bottling bottling) {
        Bottling saved = bottlingRepository.save(bottling);
        // Assume each bottling adds to the category named after its protocol
        String category = bottling.getBrewingProtocol().getRecipe().getRecipeName();
        inventoryService.updateInventoryAmount(category, bottling.getAmount());
        return saved;
    }

    public Bottling editBottling(Integer id, Bottling updatedBottling) throws Exception {
        Bottling existing = bottlingRepository.findById(id).orElseThrow(() -> new Exception("Bottling not found"));
        updatedBottling.setBottlingID(existing.getBottlingID()); // Preserve ID
        return bottlingRepository.save(updatedBottling);
    }

    public void deleteBottling(Integer id) throws Exception {
        if (!bottlingRepository.existsById(id)) {
            throw new Exception("Bottling record does not exist");
        }
        bottlingRepository.deleteById(id);
    }

    public Bottling getBottling(Integer id) throws Exception {
        return bottlingRepository.findById(id).orElseThrow(() -> new Exception("Bottling record not found"));
    }

    public List<Bottling> getAllBottlings() {
        return bottlingRepository.findAll();
    }
}
