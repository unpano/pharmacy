package isa.pharmacy.Controllers;

import isa.pharmacy.Models.User;
import isa.pharmacy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Page<User>>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOpt = this.userService.findById(id);

        if (userOpt.isPresent()){
            return new ResponseEntity<>(userOpt.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        userService.add(user);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> optUser = userService.delete(id);

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        Optional<User> optUser = userService.update(user);

        if (optUser.isPresent()) {
            return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
