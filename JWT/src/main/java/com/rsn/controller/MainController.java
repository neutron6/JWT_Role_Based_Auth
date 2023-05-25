package com.rsn.controller;

import com.rsn.model.AppUser;
import com.rsn.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/")
    public String authenticateAll() {
        return "THIS IS ACCESSIBLE BY ALL";
    }

    @GetMapping("/user")
    public List<Object> authenticateUser() {
        return userDetailsServiceImpl.getDataByRoleBase();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public List<AppUser> authenticateAdmin() {
        return userDetailsServiceImpl.getAlldata();

    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping("/editor")
    public String authenticateEditor() {
        return "THIS IS ACCESSIBLE BY EDITOR";
    }
}
