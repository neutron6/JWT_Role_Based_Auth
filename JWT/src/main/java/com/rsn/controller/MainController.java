package com.rsn.controller;

import com.rsn.dto.CreateEmployeeReq;
import com.rsn.model.AppUser;
import com.rsn.model.Employee;
import com.rsn.service.EmployeeServiceImpl;
import com.rsn.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/")
    public String authenticateAll() {
        return "THIS IS ACCESSIBLE BY ALL";
    }

    @PostMapping("/signUp")
    public ResponseEntity<Optional<Employee>> createEmployeeAccount(@RequestBody CreateEmployeeReq createEmployeeReq) throws Exception {
        return ResponseEntity.ok(Optional.ofNullable(userDetailsServiceImpl.createEmployee(createEmployeeReq)));
    }

    @GetMapping("/logIn/{email}/{password}")
    public ResponseEntity<Optional<Employee>> logIn(@PathVariable String email, @PathVariable String password) throws Exception {
        return ResponseEntity.ok(employeeServiceImpl.signIn(email, password));
    }

    @GetMapping("/user")
    public List<Object> authenticateUser() {
        return userDetailsServiceImpl.getDataByRoleBase();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public List<Employee> authenticateAdmin() {
        return userDetailsServiceImpl.getAlldata();

    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping("/editor")
    public String authenticateEditor() {
        return "THIS IS ACCESSIBLE BY EDITOR";
    }
}
