package com.challengeone.forohub.controller;

import com.challengeone.forohub.dto.DataRequestUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody @Valid DataRequestUser userAndPass) {
        Authentication token = new UsernamePasswordAuthenticationToken(userAndPass.username(), userAndPass.password());
//        System.out.println(token);
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
