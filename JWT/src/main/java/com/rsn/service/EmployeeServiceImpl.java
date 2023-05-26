package com.rsn.service;

import com.rsn.model.Employee;
import com.rsn.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepo employeeRepo;



    public Optional<Employee> signIn(String email, String password) throws Exception {//employee validation

        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepo.findByEmailAndPassword(email, password));

        if (employeeOptional.isEmpty()) {
            throw new Exception("USER DATA MIGHT BE DELETED OR DATA ENTERED IS INVALID");
        } else {
            return employeeOptional;
        }

    }
}
