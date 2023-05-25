package com.rsn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class MainController {

    @GetMapping("/all")
    public String authenticateAll() {
        return "THIS IS ACCESSIBLE BY ALL";
    }

    @GetMapping("/user")
    public String authenticateUser() {
        return "THIS IS ACCESSIBLE BY AUTHENTICATED USER";
    }

    @GetMapping("/admin")
    public String authenticateAdmin() {
        return "THIS IS ACCESSIBLE BY ADMIN";
    }

    @GetMapping("/editor")
    public String authenticateEditor() {
        return "THIS IS ACCESSIBLE BY EDITOR";
    }
}
