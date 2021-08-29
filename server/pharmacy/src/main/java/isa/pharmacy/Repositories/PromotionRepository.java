package isa.pharmacy.Repositories;

import isa.pharmacy.Models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends  JpaRepository<Promotion,Long>{

    Promotion save(Promotion p);
}
