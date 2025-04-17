package com.factura.facturacion.controller.user;

import com.factura.facturacion.dto.user.DTOResponseUser;
import com.factura.facturacion.dto.user.DTOUser;
import com.factura.facturacion.model.user.User;
import com.factura.facturacion.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("facturacion/api/v1/users")
public class UserController {
  @Autowired
  private UserService userService;


  @PostMapping("/post")
  public ResponseEntity<DTOResponseUser> postUser(@RequestBody @Valid DTOUser dtoUser, UriComponentsBuilder uriComponentsBuilder){
    User user = userService.createUser(dtoUser);
    DTOResponseUser dtoResponseUser = new DTOResponseUser(user.getIdUser(), user.getUsername(), user.getStatus());

    URI url = uriComponentsBuilder.path("facturacion/api/v1/users/{id}").buildAndExpand(user.getIdUser()).toUri();
    return ResponseEntity.created(url).body(dtoResponseUser);

  }
}
