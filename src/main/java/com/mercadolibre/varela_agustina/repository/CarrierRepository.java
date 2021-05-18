package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Long> {
}
