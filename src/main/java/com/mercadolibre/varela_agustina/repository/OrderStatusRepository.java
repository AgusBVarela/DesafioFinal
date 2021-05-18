package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.dtos.OrderStatusDTO;
import com.mercadolibre.varela_agustina.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    @Query(value=" SELECT new com.mercadolibre.varela_agustina.dtos.OrderStatusDTO(id_order_type, code ,description ) FROM OrderStatus  WHERE code= :statusCode ",nativeQuery = false)
    OrderStatusDTO findByIdCode(@Param("statusCode") String statusCode);
}
