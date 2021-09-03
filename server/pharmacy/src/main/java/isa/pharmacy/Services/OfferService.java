package isa.pharmacy.Services;

import isa.pharmacy.Models.GeneralUser;
import isa.pharmacy.Models.OrderOffer;
import isa.pharmacy.Models.VacationRequest;

import java.util.List;

public interface OfferService {


    List<OrderOffer> findOfferByOrderId(Long id);

    Object approve(OrderOffer orderOffer );

    Object decline(OrderOffer orderOffer );
}





