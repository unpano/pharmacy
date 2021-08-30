package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term,Long> {
    List<Term> findAllByUserId(Long id);

    List<Term> findAllByPharmacistId(Long pharmacistId);
}
