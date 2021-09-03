package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Models.Promotion;
import isa.pharmacy.Repositories.MedsQRepository;
import isa.pharmacy.Repositories.PromotionRepository;
import isa.pharmacy.Services.MedsQService;
import isa.pharmacy.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedsQServiceImpl implements MedsQService {


    @Autowired
    private MedsQRepository medsQRepository;


    @Override
    public MedsQuantity add(MedsQuantity medsQuantity)
    {
        return medsQRepository.save(medsQuantity);
    }


}





