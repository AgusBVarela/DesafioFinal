package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.StockSubsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StockSubsidiaryRepository extends JpaRepository<StockSubsidiary,Long> {
    @Query(value = "  UPDATE stock_subsidiary SET quantity = quantity + :quantity WHERE id = :id ;",nativeQuery = true)
    @Modifying
    @Transactional
    void updateStock(@Param("id") Integer id, @Param("quantity") Integer quantity);
    @Query(value = " INSERT INTO stock_subsidiary (quantity,dealear_id,part_id) VALUES (  :quantity , :dealerId , :partId ); ",nativeQuery = true)
    @Modifying
    @Transactional
    void insertStock( @Param("quantity") Integer quantity, @Param("dealerId") long dealerId, @Param("partId") Integer partId);
    @Query(value = " SELECT IFNULL(id,0) as id FROM  stock_subsidiary where dealear_id= :dealerId and part_id= :partId ; ",nativeQuery = true)
    Integer getId(@Param("dealerId") Long dealerId, @Param("partId") Integer partId);
}
