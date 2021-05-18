package com.mercadolibre.varela_agustina.repository;
import com.mercadolibre.varela_agustina.dtos.responses.StockDTO;
import com.mercadolibre.varela_agustina.model.StockCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StockCMRepository extends JpaRepository<StockCM,Long> {

    String queryStart = "";
    @Query(value="SELECT IF(ISNULL(MAX(id_stock_cm))=1, 0 , MAX(id_stock_cm)) FROM stock_cm; ",nativeQuery = true)
    Integer findMaxId();

    @Query(value= queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.responses.StockDTO (id_stock_cm as IdStockCM , quantity ) FROM StockCM WHERE id_part= :idPart ",nativeQuery = false)
    StockDTO findByIdPart(@Param("idPart") Long idPart);

    @Query(value="UPDATE stock_cm SET quantity= :stockQuantity WHERE id_stock_cm = :idStockCM ;",nativeQuery = true)
    @Modifying
    @Transactional
    void updateQuantityPart(@Param("idStockCM") Integer idStockCM,@Param("stockQuantity") Integer stockQuantity);


    @Query(value=" INSERT INTO stock_cm(quantity,id_part) " +
            "VALUES( :stockQuantity , :idPart );",nativeQuery = true)
    @Modifying
    @Transactional
    void insertStockCM(@Param("stockQuantity") Integer stockQuantity,@Param("idPart") Long idPart);
}

