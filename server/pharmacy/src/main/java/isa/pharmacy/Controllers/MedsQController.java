package isa.pharmacy.Controllers;

import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Models.Promotion;
import isa.pharmacy.Services.MedsQService;
import isa.pharmacy.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quantity")
public class MedsQController {


    @Autowired
    private MedsQService medsQService;

    //kreiranje promocije
    @PostMapping("/addMedQuantity/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMedQuantity(@RequestBody MedsQuantity medsQuantity) {


        return new ResponseEntity<>(medsQService.add(medsQuantity), HttpStatus.OK);
    }
}
