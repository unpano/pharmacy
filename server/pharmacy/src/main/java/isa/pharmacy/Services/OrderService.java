package isa.pharmacy.Services;

import isa.pharmacy.Models.MedsOrder;
import isa.pharmacy.Models.MedsQuantity;


import java.util.List;
import java.util.Optional;

public interface OrderService {

    MedsOrder add(MedsOrder medsOrder);

    List<MedsOrder> findAllByPharmacyId(Long id);

    Optional<MedsOrder> findById(Long id);

    List<MedsQuantity> getMedsByOrderId(Long id);
}

