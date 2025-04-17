package com.factura.facturacion.dto.client;

import com.factura.facturacion.model.client.Client;

public record DTOResponseClient(
        Integer idClient,
        String firstNameClient,
        String lastNameClient,
        String identifyClient,
        String phoneNumberClient,
        String addressClient,
        String emailClient,
        Boolean status
) {
  public DTOResponseClient(Client client){
    this(
            client.getIdClient(),
            client.getFirstNameClient(),
            client.getLastNameClient(),
            client.getIdentifyClient(),
            client.getPhoneNumberClient(),
            client.getAddressClient(),
            client.getEmailClient(),
            client.getStatus()
    );
  }
}
