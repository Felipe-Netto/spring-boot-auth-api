package com.github.felipenetto.auth_api.controllers;

import com.github.felipenetto.auth_api.DTOs.auth.AuthenticationDTO;
import com.github.felipenetto.auth_api.DTOs.auth.LoginResponseDTO;
import com.github.felipenetto.auth_api.DTOs.auth.RegisterDTO;
import com.github.felipenetto.auth_api.domain.user.User;
import com.github.felipenetto.auth_api.infra.security.TokenService;
import com.github.felipenetto.auth_api.repositories.UserRepositorie;
import jakarta.validation.Valid;
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
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepositorie userRepositorie;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepositorie.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encriptedPassword, data.role());

        System.out.printf("Registering user: %s with role: %s%n", data.login(), data.role().name());

        this.userRepositorie.save(user);

        return ResponseEntity.ok().build();
    }
}
