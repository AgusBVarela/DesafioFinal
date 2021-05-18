package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO;
import com.mercadolibre.varela_agustina.model.OrderDetailCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailCMRepository extends JpaRepository<OrderDetailCM,Long> {
    @Query(value=" SELECT new com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO (p.part_code as partCode, p.description as description, o.quantity as quantity, a.code as accountType, o.reason as reason, ps.code as partStatus)" +
            " FROM OrderDetailCM o " +
            " INNER JOIN OrderCM od on o.id_order_cm=od.id_order_cm " +
            " INNER JOIN Part p on p.id_part=o.part " +
            " INNER JOIN AccountType a on o.account_type=a.id_account_type " +
            " INNER JOIN PartStatus ps on ps.id_part_status=o.part_status " +
            " WHERE od.id_order_cm = :idOrderCM " , nativeQuery = false)
    List<OrderDetailsDTO> getPartOrderNumber(@Param("idOrderCM") Long idOrderCM);

    @Query(value = "INSERT INTO order_detail_cm(id_order_detail_cm,quantity,id_account_type,id_order_cm,id_part,id_part_status,reason) " +
            " VALUES( :idOrderDetail , :quantity , :idAccountType , :idOrderCM, :idPart , 1, :reason ); " , nativeQuery = true)
    @Modifying
    @Transactional
    void insertOrderDetail(@Param("idOrderDetail") Integer idOrderDetail,@Param("idOrderCM") Integer idOrderCM,@Param("quantity") Integer quantity,@Param("idAccountType") Integer idAccountType,@Param("idPart") Long idPart,@Param("reason") String reason);

    @Query(value = " select IF(ISNULL(MAX(id_order_detail_cm))=1, 0 , MAX(id_order_detail_cm)+1) from  order_detail_cm" ,nativeQuery = true)
    Integer getIdOrderDetailCM();
}
