package isa.pharmacy.Services;

import isa.pharmacy.Models.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    User findByUsername(String username);
    Page<User> findAll ();
    User add(User user);
    Optional<User> delete(Long id);
    Optional<User> update(User user);
    //User save(UserRequest userRequest);



}

