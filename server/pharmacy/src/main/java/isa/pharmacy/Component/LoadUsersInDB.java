package isa.pharmacy.Component;

import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //Only when db is empty load initial users to it
        if (userRepository.count() > 0) {
            return;
        }

        User user = new User("Andjela", "Paunovic", "andjelapaunovic99@gmail.com",
                "andjelita", "Potocanje BB","Uzice", "Srbija", "0616972406");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        user = new User("Aleksandar", "Paunovic", "apaunovic80@gmail.com",
                "paunida", "Potocanje BB", "Uzice", "Srbija", "0605464115");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        user = new User("Jakov", "Paunovic", "stojka.paunovic4@gmail.com",
                "jakovi", "Potocanje BB", "Uzice", "Srbija", "0653546411");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
