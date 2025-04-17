package com.factura.facturacion.repository.client;

import com.factura.facturacion.model.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
  Optional<Client> findByFirstNameClient(String firstNameClient);
  Page<Client> findByStatus(Boolean status, Pageable pageable);
}
