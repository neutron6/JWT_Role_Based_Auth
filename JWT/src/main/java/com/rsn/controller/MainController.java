package com.rsn.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String authenticateAll() {
        return "THIS IS ACCESSIBLE BY ALL";
    }

    @GetMapping("/user")
    public String authenticateUser() {
        return "THIS IS ACCESSIBLE BY AUTHENTICATED USER";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String authenticateAdmin() {
        return "THIS IS ACCESSIBLE BY ADMIN";
    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping("/editor")
    public String authenticateEditor() {
        return "THIS IS ACCESSIBLE BY EDITOR";
    }
}
