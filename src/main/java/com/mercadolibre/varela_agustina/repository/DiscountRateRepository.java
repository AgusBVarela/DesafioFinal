package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.DiscountRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRateRepository extends JpaRepository<DiscountRate,Long> {
}
