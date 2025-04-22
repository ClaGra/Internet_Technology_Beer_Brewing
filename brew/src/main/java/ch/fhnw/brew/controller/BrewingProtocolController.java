package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.BrewingProtocolService;
import ch.fhnw.brew.data.domain.BrewingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/protocols")
public class BrewingProtocolController {

    @Autowired
    private BrewingProtocolService brewingProtocolService;

    @PostMapping
    public ResponseEntity<BrewingProtocol> addProtocol(@RequestBody BrewingProtocol protocol) {
        return ResponseEntity.ok(brewingProtocolService.addBrewingProtocol(protocol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrewingProtocol> editProtocol(@PathVariable Integer id, @RequestBody BrewingProtocol protocol) {
        return ResponseEntity.ok(brewingProtocolService.editBrewingProtocol(id, protocol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProtocol(@PathVariable Integer id) {
        brewingProtocolService.deleteBrewingProtocol(id);
        return ResponseEntity.ok(Map.of("message", "Brewing Protocol deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrewingProtocol> getProtocol(@PathVariable Integer id) {
        return ResponseEntity.ok(brewingProtocolService.getBrewingProtocol(id));
    }

    @GetMapping
    public ResponseEntity<List<BrewingProtocol>> getAllProtocols() {
        return ResponseEntity.ok(brewingProtocolService.getAllBrewingProtocols());
    }
}
