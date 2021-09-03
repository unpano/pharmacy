package isa.pharmacy.Controllers;


import isa.pharmacy.Models.MedsOrder;
import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Models.Promotion;
import isa.pharmacy.Services.OrderService;
import isa.pharmacy.Services.PharmacyService;
import isa.pharmacy.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("/addOrder/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addOrder(@RequestBody MedsOrder medsOrder) {


        return new ResponseEntity<>(orderService.add(medsOrder), HttpStatus.OK);
    }


}
