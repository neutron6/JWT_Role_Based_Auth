package com.rsn.repo;

import com.rsn.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    boolean existsByUsername(String username);

    Optional<AppUser> findByUsername(String username);



    @Query("SELECT u.username, u.password FROM AppUser u WHERE u.userRole = 'ROLE_USER'")
    List<Object> findUsersByUserRole();
}
