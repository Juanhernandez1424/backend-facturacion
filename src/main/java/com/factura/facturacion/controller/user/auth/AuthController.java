package com.factura.facturacion.controller.user.auth;

import com.factura.facturacion.dto.user.auth.DTOAuthUser;
import com.factura.facturacion.infraestructure.security.DataJWTToken;
import com.factura.facturacion.infraestructure.security.TokenService;
import com.factura.facturacion.model.user.User;
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
@RequestMapping("facturacion/api/v1/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService){
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody @Valid DTOAuthUser dtoAuthUser){
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dtoAuthUser.userName(), dtoAuthUser.userPassword());
    Authentication userAuthentication = authenticationManager.authenticate(authentication);
    String JWTToken = tokenService.generateToken((User) userAuthentication.getPrincipal());
    return ResponseEntity.ok(new DataJWTToken(JWTToken));
  }
}
