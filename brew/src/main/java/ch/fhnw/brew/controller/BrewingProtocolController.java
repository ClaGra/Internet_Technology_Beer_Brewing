package ch.fhnw.brew.controller;

import ch.fhnw.brew.business.service.BrewingProtocolService;
import ch.fhnw.brew.data.domain.BrewingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protocols")
public class BrewingProtocolController {

    @Autowired
    private BrewingProtocolService brewingProtocolService;

    @PostMapping
    public ResponseEntity<?> addProtocol(@RequestBody BrewingProtocol protocol) {
        return ResponseEntity.ok(brewingProtocolService.addBrewingProtocol(protocol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProtocol(@PathVariable Integer id, @RequestBody BrewingProtocol protocol) {
        try {
            return ResponseEntity.ok(brewingProtocolService.editBrewingProtocol(id, protocol));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProtocol(@PathVariable Integer id) {
        try {
            brewingProtocolService.deleteBrewingProtocol(id);
            return ResponseEntity.ok("Brewing Protocol deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProtocol(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(brewingProtocolService.getBrewingProtocol(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<BrewingProtocol> getAllProtocols() {
        return brewingProtocolService.getAllBrewingProtocols();
    }
}
