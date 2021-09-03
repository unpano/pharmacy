package isa.pharmacy.Repositories;

import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Models.OrderOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedsQRepository extends JpaRepository<MedsQuantity,Long> {
}
