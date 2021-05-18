package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.PartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartStatusRepository extends JpaRepository<PartStatus,Long> {
}
