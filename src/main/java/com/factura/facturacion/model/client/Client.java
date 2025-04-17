package com.factura.facturacion.model.client;

import com.factura.facturacion.dto.client.DTOClient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idClient;
  @Setter
  private String firstNameClient;
  @Setter
  private String lastNameClient;
  @Column(unique = true)
  @Setter
  private String identifyClient;
  @Setter
  private String phoneNumberClient;
  @Setter
  private String addressClient;
  @Setter
  private String emailClient;
  @Setter
  private Boolean status;

  public Client(DTOClient dtoClient) {
    this.idClient = dtoClient.idClient();
    this.firstNameClient = dtoClient.firstNameClient();
    this.lastNameClient = dtoClient.lastNameClient();
    this.identifyClient = dtoClient.identifyClient();
    this.phoneNumberClient = dtoClient.phoneNumberClient();
    this.addressClient = dtoClient.addressClient();
    this.emailClient = dtoClient.emailClient();
    this.status = dtoClient.status();
  }
}
