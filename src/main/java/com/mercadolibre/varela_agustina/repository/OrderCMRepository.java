package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.dtos.OrderDetailNumberDTO;
import com.mercadolibre.varela_agustina.dtos.OrderFindDTO;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO;
import com.mercadolibre.varela_agustina.model.OrderCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderCMRepository extends JpaRepository<OrderCM,Long> {

    String queryStart = "";
    @Query(value = queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.OrderFindDTO (o.order_date as orderDate ,o.delibery_date as deliveryDate , o.days_delayed as daysDelayed, d.code as deliveryStatus, u.id_dealer as idDealer, o.id_order_cm as idOrderCM, m.id_matrix_house as idMatrixHouse)" +
            " FROM OrderCM o " +
            " INNER JOIN DeliveryStatus d on d.id_delivery_status=o.delivery_status " +
            " INNER JOIN Account u on o.subsidiary=u.id" +
            " INNER JOIN Dealer de on de.id_dealer=u.id_dealer" +
            " INNER JOIN MatrixHouse m on m.id_matrix_house=de.id_matrix_house" +
            " WHERE d.code= :deliveryStatus " , nativeQuery = false)
    List<OrderFindDTO> findAllOrders(@Param("deliveryStatus") String deliveryStatus);


    @Query(value = queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.OrderFindDTO (o.order_date as orderDate ,o.delibery_date as deliveryDate , o.days_delayed as daysDelayed, d.code as deliveryStatus, u.id_dealer as idDealer, o.id_order_cm as idOrderCM, m.id_matrix_house as idMatrixHouse)" +
            " FROM OrderCM o " +
            " INNER JOIN DeliveryStatus d on d.id_delivery_status=o.delivery_status " +
            " INNER JOIN Account u on o.subsidiary=u.id" +
            " INNER JOIN Dealer de on de.id_dealer=u.id_dealer" +
            " INNER JOIN MatrixHouse m on m.id_matrix_house=de.id_matrix_house" , nativeQuery = false)
    List<OrderFindDTO> findAllOrdersDealer();

    @Query(value = queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO (p.part_code as partCode, p.description as description, o.quantity as quantity, a.code as accountType, o.reason as reason)" +
            " FROM OrderDetailCM o " +
            " INNER JOIN OrderCM od on o.id_order_cm=od.id_order_cm " +
            " INNER JOIN Part p on p.id_part=o.part " +
            " INNER JOIN AccountType a on o.account_type=a.id_account_type " +
            " WHERE od.id_order_cm= :orderCmNumber" , nativeQuery = false)
    List<OrderDetailsDTO> findPartOrderDetail(@Param("orderCmNumber") long orderCmNumber);

    @Query(value = queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.OrderDetailNumberDTO (d.id_dealer as idDealer, o.id_order_cm as idOrderCM, o.order_date as orderDate, os.code as orderStatus)" +
            "  FROM  OrderCM o" +
            "  INNER JOIN OrderStatus os on os.id_order_type=o.order_status " +
            "  INNER JOIN Account u on u.id=o.subsidiary " +
            "  INNER JOIN Dealer d on d.id_dealer=u.id_dealer " +
            "  WHERE o.id_order_cm= :idOrderCM " , nativeQuery = false)
    OrderDetailNumberDTO findOrderNumberCM(@Param("idOrderCM") Long idOrderCM);



    @Query(value = "INSERT INTO `vareladb`.`order_cm` " +
            "(`id_order_cm`,`days_delayed`,`delibery_date`,`order_date`,`serial_number`,`id_carrier`,`id_delivery_status`,`id_order_status`,`id_order_type`,`id_shipping`,`subsidiary`) " +
            "VALUES " +
            "( :idOrderCM ,0, :deliveryDate , CURDATE(), :serialNumber , 1,4,1, :idOrderType , :idShipping , 2); " , nativeQuery = true)
    @Modifying
    @Transactional
    void insertOrderCM(@Param("idOrderCM") Long idOrderCM, @Param("deliveryDate") LocalDate deliveryDate, @Param("serialNumber") String serialNumber, @Param("idShipping") Integer idShipping, @Param("idOrderType") Integer idOrderType);


    @Query(value = " select IF(ISNULL(MAX(id_order_cm))=1, 0 , MAX(id_order_cm)+1) from  order_cm; ",nativeQuery = true)
    Integer getIdOrderCMInsert();


    @Query(value = " select id_order_cm from order_cm where id_order_cm= :idOrdenCM ; ",nativeQuery = true)
    Integer getIdOrderCM(@Param("idOrdenCM") Long idOrdenCM);

    @Query(value =" UPDATE order_cm SET id_order_status = :idOrderStatus WHERE id_order_cm= :idOrdenCM ; ",nativeQuery = true)
    @Modifying
    @Transactional
    void updateStatus(@Param("idOrdenCM") Long idOrdenCM,@Param("idOrderStatus") Long idOrderStatus);
}