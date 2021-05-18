package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType,Long> {
}
