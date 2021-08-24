package isa.pharmacy.Services;

import isa.pharmacy.Models.DateTimeJSON;
import isa.pharmacy.Models.Pharmacist;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Models.Term;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TermService {
    List<Pharmacy> findAllFree(DateTimeJSON dateTimeJSON);

    List<Pharmacist> findAllFreePharmacists(DateTimeJSON dateTimeJSON, Long id);

    Object add(Date dateValue, Long userId, Long pharmacistId);

    List<Term>  findFutureTermsByUserId(Long id);

    List<Term> findPastTermsByUserId(Long id);

    Optional<Term> freeTerm(Long id, Long id1);
}
