package isa.pharmacy.Repositories;


import isa.pharmacy.Models.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface DermatologistRepository extends JpaRepository<Dermatologist,Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Dermatologist w where w.id = :id")
    Dermatologist findOneForUpdate(@Param("id") Long id);
}

