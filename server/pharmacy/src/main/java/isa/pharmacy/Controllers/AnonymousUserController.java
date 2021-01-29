package isa.pharmacy.Controllers;

import isa.pharmacy.Models.User;
import isa.pharmacy.Services.AnonymousUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AnonymousUserController {

    @Autowired
    private AnonymousUserService anonymousUserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOpt = this.anonymousUserService.findById(id);

        if (userOpt.isPresent()){
            return new ResponseEntity<>(userOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
