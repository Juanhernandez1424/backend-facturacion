package com.factura.facturacion.model.invoices;

import com.factura.facturacion.dto.invoice.DTOInvoice;
import com.factura.facturacion.model.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "invoice")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idInvoice;
  @Setter
  @Column(unique = true)
  private String cufeInvoice;
  @Setter
  private Date issueDateInvoice;
  @Setter
  private String identifyIssuingInvoice;
  @Setter
  private String identifyClientInvoice;
  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idClient")
  private Client client;
  @Setter
  private Double subtotalValueInvoice;
  @Setter
  private Double ivaValueInvoice;
  @Setter
  private Double totalValueInvoice;

  public Invoice(DTOInvoice dtoInvoice, Client client) {
    this.idInvoice = dtoInvoice.idInvoice();
    this.issueDateInvoice = dtoInvoice.issueDateInvoice();
    this.identifyIssuingInvoice = dtoInvoice.identifyIssuingInvoice();
    this.identifyClientInvoice = client.getIdentifyClient();
    this.client = client;
    this.subtotalValueInvoice = dtoInvoice.subtotalValueInvoice();
    this.ivaValueInvoice = dtoInvoice.ivaValueInvoice();
    this.totalValueInvoice = dtoInvoice.totalValueInvoice();
  }

  @PrePersist
  public void generatedCufeUUID(){
    if(this.cufeInvoice == null || this.cufeInvoice.isEmpty()){
      this.cufeInvoice = UUID.randomUUID().toString().replace("-", "");
    }
  }
}
