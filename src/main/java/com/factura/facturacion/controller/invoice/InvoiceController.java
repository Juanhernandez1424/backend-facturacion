package com.factura.facturacion.controller.invoice;

import com.factura.facturacion.dto.invoice.DTOInvoice;
import com.factura.facturacion.dto.invoice.DTOResponseInvoice;
import com.factura.facturacion.model.client.Client;
import com.factura.facturacion.model.invoices.Invoice;
import com.factura.facturacion.service.client.ClientService;
import com.factura.facturacion.service.invoice.InvoiceService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("facturacion/api/v1/invoices")
public class InvoiceController {
  @Autowired
  private InvoiceService invoiceService;

  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<Page<DTOResponseInvoice>> findAll(@PageableDefault(size = 10)Pageable pageable){
    return ResponseEntity.ok(invoiceService.findAll(pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<DTOResponseInvoice> findById(@PathVariable Integer id){
    DTOResponseInvoice dtoResponseInvoice = invoiceService.findById(id);
    return ResponseEntity.ok(dtoResponseInvoice);
  }

  @PostMapping("/post")
  public ResponseEntity<DTOResponseInvoice> postInvoice(@RequestBody @Valid DTOInvoice dtoInvoice, UriComponentsBuilder uriComponentsBuilder){
    Invoice invoice = invoiceService.createInvoice(dtoInvoice);
    Client client = clientService.getClientById(dtoInvoice.idClientInvoice());

    DTOResponseInvoice dtoResponseInvoice = new DTOResponseInvoice(
            invoice.getIdInvoice(),
            invoice.getCufeInvoice(),
            invoice.getIssueDateInvoice(),
            invoice.getIdentifyIssuingInvoice(),
            invoice.getIdentifyClientInvoice(),
            client.getIdClient(),
            invoice.getSubtotalValueInvoice(),
            invoice.getIvaValueInvoice(),
            invoice.getTotalValueInvoice()
    );
    URI url = uriComponentsBuilder.path("facturacion/api/v1/invoices/{id}").buildAndExpand(invoice.getIdInvoice()).toUri();
    return ResponseEntity.created(url).body(dtoResponseInvoice);
  }
}
