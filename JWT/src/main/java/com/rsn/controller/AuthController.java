package com.rsn.controller;

import com.rsn.dto.AuthReq;
import com.rsn.dto.CreateUserReq;
import com.rsn.model.AppUser;
import com.rsn.security.JwtProvider;
import com.rsn.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/token")
    public String getToken(@RequestBody AuthReq authReq) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());

        if (passwordEncoder.matches(authReq.getPassword(), userDetails.getPassword())) {
            return jwtProvider.generateToken(authReq.getUsername());
        }
        throw new Exception("user details invalid");
    }

    @PostMapping("/createAccount")
    public AppUser createAccount(@RequestBody CreateUserReq createUserReq) throws Exception {
        return userDetailsService.createUser(createUserReq);
    }

  


}
