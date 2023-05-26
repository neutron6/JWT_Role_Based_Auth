package com.rsn.repo;

import com.rsn.model.AppUser;
import com.rsn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Employee findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
