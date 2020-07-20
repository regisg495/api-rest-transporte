package com.br.transporteapi.controller;

import com.br.transporteapi.configuration.security.AuthenticationTokenProvider;
import com.br.transporteapi.controller.DTO.TokenDTO;
import com.br.transporteapi.controller.form.LoginForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationTokenProvider authenticationTokenProvider;

    @ApiOperation(value = "Faz login no sistema")
    @PostMapping("/usuario/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginForm loginForm) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginForm.convert();
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = authenticationTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }

}
