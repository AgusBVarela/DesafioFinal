package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.dtos.PartPriceDTO;
import com.mercadolibre.varela_agustina.model.PartPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PartPriceRepository extends JpaRepository<PartPrice,Long> {

    @Query(value=" INSERT INTO `vareladb`.`part_price`(`id_part_price`,`price`,`sale_price`,`urgent_price`) " +
            " VALUES( :idPartPrice , :price , :salePrice , :urgentPrice ); " ,nativeQuery = true)
    @Modifying
    @Transactional
    void insertPartPrice(@Param("idPartPrice") Long idPartPrice , @Param("price") Double price, @Param("salePrice") Double salePrice , @Param("urgentPrice") Double urgentPrice);

    @Query(value=" UPDATE part_price " +
            " SET price= :price ,sale_price= :salePrice ,urgent_price= :urgentPrice " +
            " WHERE id_part_price= :idPartPrice ;", nativeQuery = true)
    @Modifying
    @Transactional
    void updatePartPrice(@Param("idPartPrice") Long idPartPrice , @Param("price") Double price, @Param("salePrice") Double salePrice , @Param("urgentPrice") Double urgentPrice);



    @Query(value="select IF(ISNULL(MAX(id_part_price))=1, 0 , MAX(id_part_price)+1) from  part_price;",nativeQuery = true)
    Integer getIdInsertPartPrice();


    @Query(value=" SELECT new com.mercadolibre.varela_agustina.dtos.PartPriceDTO (id_part_price , price, urgent_price, sale_price ) FROM PartPrice WHERE id_part_price= :idPartPrice ",nativeQuery = false)
    PartPriceDTO findByIdPart(@Param("idPartPrice") Long idPartPrice);

}
