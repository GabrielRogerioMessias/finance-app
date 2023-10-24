package com.integratingProject.financeapp.controllers;

import com.integratingProject.financeapp.dtos.AuthenticationRequest;
import com.integratingProject.financeapp.dtos.LoginResponse;
import com.integratingProject.financeapp.infra.security.TokenService;
import com.integratingProject.financeapp.models.User;
import com.integratingProject.financeapp.repositories.UserRepository;
import com.integratingProject.financeapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        Integer userId = user.getId();

        return ResponseEntity.ok(new LoginResponse(token, userId));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if(this.userRepository.findByEmail(user.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user.getName(), user.getUsername(), encryptedPassword, user.getBalance());
        userService.insert(newUser);

        return ResponseEntity.ok().build();
    }
}
