package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.BrewingProtocol;
import ch.fhnw.brew.data.repository.BrewingProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrewingProtocolService {

    @Autowired
    private BrewingProtocolRepository brewingProtocolRepository;

    public BrewingProtocol addBrewingProtocol(BrewingProtocol protocol) {
        return brewingProtocolRepository.save(protocol);
    }

    public BrewingProtocol editBrewingProtocol(Integer id, BrewingProtocol updated) throws Exception {
        BrewingProtocol existing = brewingProtocolRepository.findById(id).orElseThrow(() -> new Exception("Brewing Protocol not found"));
        updated.setBatchNr(existing.getBatchNr()); // Keep ID
        return brewingProtocolRepository.save(updated);
    }

    public void deleteBrewingProtocol(Integer id) throws Exception {
        if (!brewingProtocolRepository.existsById(id)) {
            throw new Exception("Brewing Protocol does not exist");
        }
        brewingProtocolRepository.deleteById(id);
    }

    public BrewingProtocol getBrewingProtocol(Integer id) throws Exception {
        return brewingProtocolRepository.findById(id).orElseThrow(() -> new Exception("Brewing Protocol not found"));
    }

    public List<BrewingProtocol> getAllBrewingProtocols() {
        return brewingProtocolRepository.findAll();
    }
}
