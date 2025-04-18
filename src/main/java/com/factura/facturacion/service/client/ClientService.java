package com.factura.facturacion.service.client;

import com.factura.facturacion.dto.client.DTOClient;
import com.factura.facturacion.dto.client.DTOResponseClient;
import com.factura.facturacion.model.category.Category;
import com.factura.facturacion.model.client.Client;
import com.factura.facturacion.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
  @Autowired
  private ClientRepository repository;

  public Page<DTOResponseClient> findAll(Pageable pageable){
    Page<Client> clients = repository.findByStatus(true, pageable);
    List<DTOResponseClient> descClients = clients.stream()
            .sorted(Comparator.comparing(Client::getIdClient).reversed())
            .map(DTOResponseClient::new)
            .toList();

    return new PageImpl<>(descClients, pageable, clients.getTotalElements());
  }

  public DTOResponseClient findById(Integer id){
    Optional<Client> client = repository.findById(id);
    if(client.isPresent()){
      Client c = client.get();
      return new DTOResponseClient(c.getIdClient(), c.getFirstNameClient(), c.getLastNameClient(), c.getIdentifyClient(), c.getPhoneNumberClient(),
      c.getAddressClient(), c.getEmailClient(), c.getStatus());
    }

    return null;
  }

  public Client getClientById(Integer id){
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client doesn't exist"));
  }

  public Client createClient(DTOClient dtoClient){
    return repository.save(new Client(dtoClient));
  }

  public Client updateClient(Integer id, DTOClient dtoClient){
    Client client = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client doesn't exist"));
    client.setFirstNameClient(dtoClient.firstNameClient());
    client.setLastNameClient(dtoClient.lastNameClient());
    client.setIdentifyClient(dtoClient.identifyClient());
    client.setPhoneNumberClient(dtoClient.phoneNumberClient());
    client.setAddressClient(dtoClient.addressClient());
    client.setEmailClient(dtoClient.emailClient());
    client.setStatus(dtoClient.status());

    return repository.save(client);
  }

  public void deleteClient(Integer id){
    Client client = repository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Client doesn´t exist"));
    client.setStatus(false);

    repository.save(client);
  }
}
