package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.ShippingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingTypeRepository extends JpaRepository<ShippingType,Long> {
}
