package isa.pharmacy.Controllers;


import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Services.OrderService;
import isa.pharmacy.Services.PharmacyService;
import isa.pharmacy.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(@PathVariable Long id) {

        return new ResponseEntity<>(orderService.findAllByPharmacyId(id), HttpStatus.OK) ;
    }

    @GetMapping("/getMeds/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getMedsByOrderId(@PathVariable Long id) {

        return new ResponseEntity<List<MedsQuantity>>(orderService.getMedsByOrderId(id) , HttpStatus.OK) ;
    }


}
