package com.factura.facturacion.service.user;

import com.factura.facturacion.dto.user.DTOResponseUser;
import com.factura.facturacion.dto.user.DTOUser;
import com.factura.facturacion.model.user.User;
import com.factura.facturacion.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Page<DTOResponseUser> findAll(Pageable pageable){
    Page<User> users = userRepository.findByStatus(true, pageable);
    List<DTOResponseUser> descUsers = users.stream()
            .sorted(Comparator.comparing(User::getIdUser).reversed())
            .map(DTOResponseUser::new)
            .toList();

    return new PageImpl<>(descUsers, pageable, users.getTotalElements());
  }

  public DTOResponseUser findById(Integer id){
    Optional<User> user = userRepository.findById(id);
    if(user.isPresent()){
      User u = user.get();
      return new DTOResponseUser(u.getIdUser(), u.getUsername(), u.getStatus());
    }
    return null;
  }

  public User createUser(DTOUser dtoUser){
    return userRepository.save( new User(dtoUser, passwordEncoder));
  }

//  public User updateUser(Integer id, DTOUser dtoUser){
//    User user = userRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("User doesn´t exist"));
//    user.setUserName(dtoUser.userName());
//    user.set
//  }
}
