package isa.pharmacy.Repositories;

import isa.pharmacy.Models.OrderOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferReppository extends JpaRepository<OrderOffer,Long> {
}
