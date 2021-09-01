package isa.pharmacy.Services;

import isa.pharmacy.Models.VacationRequest;

import java.util.List;

public interface VacationService {

    List<VacationRequest> findVacationsByPharmacyId(Long id);

    String findWorkerName(Long id);

    Object approve(VacationRequest v );

    Object decline(VacationRequest v );
}
