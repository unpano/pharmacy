package isa.pharmacy.Repositories;

import isa.pharmacy.Models.MedsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<MedsOrder,Long> {
}


