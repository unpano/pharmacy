package isa.pharmacy.Controllers;


import isa.pharmacy.Models.*;
import isa.pharmacy.Services.PharmacyService;
import isa.pharmacy.Services.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping("/getPromotions/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(@PathVariable Long id) {

        System.out.println(id);
        return new ResponseEntity<>(promotionService.findAllByPharmacyId(id), HttpStatus.OK) ;
    }

    //kreiranje promocije
    @PostMapping("/addPromotion/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPromotion(@RequestBody Promotion p) {


        return new ResponseEntity<>(promotionService.add(p), HttpStatus.OK);
    }

    @GetMapping("/getSubscribed/{id}")
    public ResponseEntity<?> getSubscribed(@PathVariable Long id) {

        System.out.println(id);
        Pharmacy ph = this.pharmacyService.findById(id).get();
        List<User> users= ph.getSubscribedUsers();

        return new ResponseEntity<>(users, HttpStatus.OK) ;
    }








}
