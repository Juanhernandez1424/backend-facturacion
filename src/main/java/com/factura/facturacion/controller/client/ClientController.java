package com.factura.facturacion.controller.client;

import com.factura.facturacion.dto.client.DTOClient;
import com.factura.facturacion.dto.client.DTOResponseClient;
import com.factura.facturacion.model.client.Client;
import com.factura.facturacion.service.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("facturacion/api/v1/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<Page<DTOResponseClient>> findAll(@PageableDefault(size = 10) Pageable pageable){
    return ResponseEntity.ok(clientService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DTOResponseClient> findById(@PathVariable Integer id){
    DTOResponseClient dtoResponseClient = clientService.findById(id);
    return ResponseEntity.ok(dtoResponseClient);
  }

  @PostMapping("/post")
  public ResponseEntity<DTOResponseClient> postClient(@RequestBody @Valid DTOClient dtoClient, UriComponentsBuilder uriComponentsBuilder){
    Client client = clientService.createClient(dtoClient);
    DTOResponseClient dtoResponseClient = new DTOResponseClient(client.getIdClient(), client.getFirstNameClient(),
            client.getLastNameClient(), client.getIdentifyClient(), client.getPhoneNumberClient(), client.getAddressClient(),
            client.getEmailClient(), client.getStatus());

    URI url = uriComponentsBuilder.path("facturacion/api/v1/clients/{id}").buildAndExpand(client.getIdClient()).toUri();
    return ResponseEntity.created(url).body(dtoResponseClient);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<DTOClient> putClient(@PathVariable Integer id, @RequestBody @Valid DTOClient dtoClient){
    Client client = clientService.updateClient(id, dtoClient);
    return ResponseEntity.ok(new DTOClient(
            client.getIdClient(),
            client.getFirstNameClient(),
            client.getLastNameClient(),
            client.getIdentifyClient(),
            client.getPhoneNumberClient(),
            client.getAddressClient(),
            client.getEmailClient(),
            client.getStatus()
    ));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DTOClient> deleteClient(@PathVariable Integer id){
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}
