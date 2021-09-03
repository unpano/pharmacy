package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.MedsOrder;
import isa.pharmacy.Models.OrderOffer;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.VacationRequest;
import isa.pharmacy.Repositories.OfferReppository;
import isa.pharmacy.Repositories.OrderRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Repositories.VacationRepository;
import isa.pharmacy.Services.OfferService;
import isa.pharmacy.Services.VacationService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;


@Service
public class OfferServiceImpl implements OfferService {



    @Autowired
    private OfferReppository offerReppository;


    @Autowired
    private OrderRepository orderRepository;



    @Override
    public List<OrderOffer> findOfferByOrderId(Long id)
    {
        MedsOrder medsOrder = orderRepository.findById(id).get();   //narudzbina koju istrazujemo
        List<OrderOffer> offers = medsOrder.getOffers();


        for ( OrderOffer o : offers)
        {
            System.out.println(o.getPrice());
        }

        System.out.println("da li uospetee dodje u servis");
        return offers;
    }






    @Override
    public Object approve(OrderOffer orderOffer)
    {
        OrderOffer offer = offerReppository.findById(orderOffer.getId()).get();

        if( offer.isAnswered() )
        {
            throw new OptimisticLockException();
        }
        offer.setAccepted(true);
        offer.setAnswered(true );
        return offerReppository.save(offer);
    }

    @Override
    public Object decline(OrderOffer orderOffer )
    {
        OrderOffer offer = offerReppository.findById(orderOffer.getId()).get();
        if( offer.isAnswered() )
        {
            throw new OptimisticLockException();
        }

        offer.setAccepted(false);
        offer.setAnswered(true);
        return offerReppository.save(offer);
    }
}




