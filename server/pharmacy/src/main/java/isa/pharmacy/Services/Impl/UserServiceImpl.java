package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.*;
import isa.pharmacy.Repositories.PharmacistRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.UserRepository;
import isa.pharmacy.Services.AuthorityService;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Autowired
    private PharmacistRepository pharmacistRepository;


    @Override
    public User addMewUser(User u )
    {
        return userRepository.save(u);
    }

    @Override
    public List<Pharmacist> findPharmacistsByPharmacyId(Long id)
    {
        Pharmacy pharm = pharmacyRepository.findById(id).get();
        System.out.println(pharm.getId());

        List<Pharmacist> pharmacists = pharm.getPharmacists();
        System.out.println(pharmacists);
        List<Pharmacist> returnList = new ArrayList<>();

        for( Pharmacist p : pharmacists)
        {
            GeneralUser user  = pharmacistRepository.findById( Long.valueOf(p.getId()) ).orElse(null);


            if(user != null)
            {
                GrantedAuthority a = user.getAuthorities().stream().findFirst().orElse(null);

                System.out.println(a.getAuthority());
                if(a.getAuthority().equals("ROLE_PHARMACIST") )
                {
                    returnList.add(p);
                }
            }
        }



        return returnList;
    }




    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

    }

    public Page<User> findAll() throws AccessDeniedException {
        return (Page<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id);
    }



    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        List<Authority> auth = authService.findByName("ROLE_USER");
        user.setAuthorities(auth);


        userRepository.save(user);


        return user;
    }

    public Optional<User> delete(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
            return userOpt;
        }

        return Optional.empty();
    }

    public Optional<User> update(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());


        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();

            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }

            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }


            if (user.getAddress() != null) {
                existingUser.setAddress(user.getAddress());
            }

            if (user.getCity() != null) {
                existingUser.setCity(user.getCity());
            }

            if (user.getCountry() != null) {
                existingUser.setCountry(user.getCountry());
            }

            if (user.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(user.getPhoneNumber());
            }

            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }

            userRepository.save(existingUser);

            return Optional.of(existingUser);
        }

        return Optional.empty();
    }
}
