package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PartRecordRepository extends JpaRepository<PartRecord,Long> {
    @Query(value = "select IF(ISNULL(MAX(id_part_record))=1, 0 , MAX(id_part_record)+1) from  part_record;", nativeQuery = true)
    Integer getIdInsertPartRecord();

    @Query(value = " INSERT INTO part_record " +
            " (id_part_record,description,last_modification,long_dimension,net_weight,normal_price, " +
            " sale_price,tall_dimension,update_price,urgent_price,width_dimension,id_discount_rate,id_part) " +
            " VALUES  ( :idPartRecord , :description , CURDATE() , :longDimension, :netWeight , :price , " +
            " :salePrice , :tallDimension, :updatePrice , :urgentPrice, :widthDimension , 1 , :idPart );", nativeQuery = true)
    @Modifying
    @Transactional
    void insertPartRecord(@Param("idPartRecord") Long idPartRecord, @Param("description") String description, @Param("longDimension") Double longDimension,
                          @Param("netWeight") Double netWeight, @Param("price") Double price,
                          @Param("salePrice") Double salePrice, @Param("tallDimension") Double tallDimension, @Param("updatePrice") Boolean updatePrice, @Param("urgentPrice") Double urgentPrice,
                          @Param("widthDimension") Double widthDimension, @Param("idPart") Long idPart);
}
