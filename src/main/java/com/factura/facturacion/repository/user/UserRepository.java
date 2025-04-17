package com.factura.facturacion.repository.user;

import com.factura.facturacion.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserName(String userName);
  Page<User> findByStatus(Boolean status, Pageable pageable);
}
