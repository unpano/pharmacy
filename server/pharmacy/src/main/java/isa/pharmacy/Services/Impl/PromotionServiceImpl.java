package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.Promotion;

import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.PromotionRepository;

import isa.pharmacy.Services.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {


        @Autowired
        private PromotionRepository promotionRepository;

        @Autowired
        private PharmacyRepository pharmacyRepository;





    @Override
    public List<Promotion> findAllByPharmacyId(Long id) throws AccessDeniedException {

        List<Promotion> prom =  promotionRepository.findAll();
        List<Promotion> returnProms = new ArrayList<>();

            for( Promotion p : prom)
            {
                Pharmacy ph = p.getPharmacy();
                if (ph.getId() == id)
                {
                    returnProms.add(p);
                    }

            }

        return returnProms;

    }






    @Override
    public Promotion add(Promotion p)
    {
        return promotionRepository.save(p);
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }


}
