package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Authority;
import isa.pharmacy.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findAll();

    public User findByUsername(String username);

    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);

    public List<User> findByCountry(String country);


}
