package com.alura.foro_hub.controllers;

import com.alura.foro_hub.domain.usersModels.User;
import com.alura.foro_hub.domain.usersModels.userDataAuthentication;
import com.alura.foro_hub.infra.security.tokenJWT;
import com.alura.foro_hub.infra.security.tokenServiceAPI;
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
public class userAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private tokenServiceAPI tokenService;
    @PostMapping
    public ResponseEntity userAuthenticate(@RequestBody @Valid userDataAuthentication dataUserAuthentication){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataUserAuthentication.username(),
                dataUserAuthentication.secret());
        var userAuthenticate = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generateNewToken((User) userAuthenticate.getPrincipal());
        return ResponseEntity.ok(new tokenJWT(JWToken));
    }
}
