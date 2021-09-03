package isa.pharmacy.Controllers;

import isa.pharmacy.Models.GeneralUser;
import isa.pharmacy.Models.OrderOffer;
import isa.pharmacy.Models.VacationRequest;
import isa.pharmacy.Services.OfferService;
import isa.pharmacy.Services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/allOffers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findOffersByOrderId(@PathVariable Long id) {


        return new ResponseEntity<List<OrderOffer>>(offerService.findOfferByOrderId(id), HttpStatus.OK);
    }



    @PutMapping("/accept/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveRequest(@RequestBody OrderOffer o){

        return new ResponseEntity<>(offerService.approve(o), HttpStatus.OK);

    }

    @PutMapping("/decline/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> declineRequest(@RequestBody OrderOffer o){

        return new ResponseEntity<>(offerService.decline(o), HttpStatus.OK);

    }



}








