package com.factura.facturacion.dto.user;

import com.factura.facturacion.model.user.User;

public record DTOResponseUser (
        Integer idUser,
        String userName,
        Boolean status
){
  public DTOResponseUser(User user){
    this(
            user.getIdUser(),
            user.getUsername(),
            user.getStatus()
    );
  }
}
