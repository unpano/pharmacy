package isa.pharmacy.Controllers;

import isa.pharmacy.Models.User;
import isa.pharmacy.Services.AnonymousUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AnonymousUserController {

    @Autowired
    private AnonymousUserService anonymousUserService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());

        return "Login";}

    @PostMapping("/findUser")
    public String findUser(@ModelAttribute User user) {

        //Ovde treba proveriti da li email i password odgovaraju nekom od vec
        //registrovanih korisnika u bazi
        //Podaci su pokupljeni iz html-a i prosledjeni preko user-a ovoj metodi
        //Zatim se redirektuje na index pocetnu stranicu

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());

        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user) {

        //Ovde treba da se kreira novi User i da se sacuva u bazi
        //Podaci su pokupljeni iz html-a i prosledjeni preko user-a ovoj metodi
        //Zatim se redirektuje na index pocetnu stranicu

        return "redirect:/";
    }

}
