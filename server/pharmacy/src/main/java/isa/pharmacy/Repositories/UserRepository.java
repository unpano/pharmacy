package isa.pharmacy.Repositories;

import isa.pharmacy.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);

    public List<User> findByCountry(String country);
}
