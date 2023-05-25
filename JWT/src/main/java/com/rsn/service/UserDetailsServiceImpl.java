package com.rsn.service;

import com.rsn.dto.CreateUserReq;
import com.rsn.model.AppUser;
import com.rsn.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(username + "username not exists"));

        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(appUser.getUserRole()));

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);


    }

    public AppUser createUser(CreateUserReq req) throws Exception {
        // Extract parameters from request
        String username = req.getUsername();
        String password = req.getPassword();
        String userRole = req.getUserRole();

        // Check whether username exists or not
        boolean isExists = appUserRepository.existsByUsername(username);

        if (isExists) {
            throw new Exception("User already exists.");
        }

        // Create new user
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setUserRole(userRole);

        // Save user
        return appUserRepository.save(appUser);
    }
}
