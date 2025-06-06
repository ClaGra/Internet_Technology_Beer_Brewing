package ch.fhnw.brew.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthCheckController {

    @GetMapping("/api/auth-check")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok("Authenticated");
    }
}
