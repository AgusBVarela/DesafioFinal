package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
