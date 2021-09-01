package isa.pharmacy.Services;

import isa.pharmacy.Models.Dermatologist;

import java.util.List;

public interface DermatologistService {

    Dermatologist addMewDermatologist(Dermatologist d );

    List<Dermatologist> findDermatologistByPharmacyId(Long id);
}




