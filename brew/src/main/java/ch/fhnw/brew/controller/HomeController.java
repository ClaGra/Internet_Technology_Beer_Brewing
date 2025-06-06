package ch.fhnw.brew.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Happy Brewing!";
    }

    @GetMapping(value="/user")
    public String getUserRole(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String role = userDetails.getAuthorities().toArray()[1].toString();
        return role;
    }
}
