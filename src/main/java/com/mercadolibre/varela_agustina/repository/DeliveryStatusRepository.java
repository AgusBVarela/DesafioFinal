package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus,Long> {
}
