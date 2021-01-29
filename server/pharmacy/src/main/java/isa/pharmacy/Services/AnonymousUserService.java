package isa.pharmacy.Services;

import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AnonymousUserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id){ return userRepository.findById(id); }

}

