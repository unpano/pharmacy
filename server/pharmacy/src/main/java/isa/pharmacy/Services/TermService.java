package isa.pharmacy.Services;

import isa.pharmacy.Models.DateTimeJSON;
import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.Pharmacy;

import java.util.Date;
import java.util.List;

public interface TermService {
    List<Pharmacy> findAllFree(DateTimeJSON dateTimeJSON);

    List<Pharmacist> findAllFreePharmacists(DateTimeJSON dateTimeJSON, Long id);

    Object add(Date dateValue, Long userId, Long pharmacistId);
}
