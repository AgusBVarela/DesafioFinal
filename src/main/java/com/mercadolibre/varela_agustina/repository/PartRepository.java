package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.dtos.PartDTO;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    String queryTest = "SELECT new com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse(p.id_part, p_r.description, pr.name as maker, s.quantity, d.description as discountType, p_r.normal_price as normalPrice, p_r.urgent_price as urgentPrice, p_r.net_weight as netWeight, p_r.long_dimension as longDimension, p_r.width_dimension as widthDimension, p_r.tall_dimension as tallDimension, p_r.last_modification as lastmodif) " +
            "            from Part p\n" +
            "            right JOIN PartRecord p_r ON p_r.part=p.id_part\n" +
            "            left JOIN Provider pr ON pr.id_provider=p.provider\n"+
            "            left JOIN DiscountRate d ON d.id_discount_rates=p_r.discount_rate\n" +
            "            left JOIN StockCM s ON s.part=p.id_part\n" +
            "            left JOIN PartPrice pp ON pp.id_part_price=p.part_price\n" +
            "            WHERE s.quantity > 0\n";


    @Query(value = queryTest, nativeQuery = false)
    List<PartDTOResponse> findAllParts();

    @Transactional
    @Query(value = queryTest + " ORDER BY p_r.description "  ,nativeQuery = false)
    List<PartDTOResponse> findPartsAndOrderAsc();

    @Transactional
    @Query(value = queryTest + " ORDER BY p_r.description DESC "  ,nativeQuery = false)
    List<PartDTOResponse> findPartsAndOrderDesc();

    @Transactional
    @Query(value = queryTest +" ORDER BY p_r.last_modification " ,nativeQuery = false)
    List<PartDTOResponse> findPartsAndOrderDate();

    @Transactional
    @Query(value = queryTest + "AND p_r.last_modification >= :param ORDER BY p_r.description " , nativeQuery = false)
    List<PartDTOResponse> findBetweenDatePartsAndOrderAsc(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest + "AND p_r.last_modification >= :param ORDER BY p_r.description DESC "  ,nativeQuery = false)
    List<PartDTOResponse> findBetweenDatePartsAndOrderDesc(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +"AND p_r.last_modification >= :param ORDER BY p_r.last_modification " ,nativeQuery = false)
    List<PartDTOResponse> findBetweenDatePartsAndOrderDate(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +"AND p_r.last_modification >= :param AND p_r.update_price = 1" , nativeQuery = false)
    List<PartDTOResponse> findFilterVariablePrice(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +"AND p_r.last_modification >= :param AND p_r.update_price = 1 ORDER BY p_r.description DESC" , nativeQuery = false)
    List<PartDTOResponse> findFilterVariablePriceOrderDesc(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +"AND p_r.last_modification >= :param AND p_r.update_price = 1 ORDER BY p_r.last_modification DESC" , nativeQuery = false)
    List<PartDTOResponse> findFilterVariablePriceOrderByDate(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest + "AND p_r.last_modification >= :param ORDER BY p.description" , nativeQuery = false)
    List<PartDTOResponse> findFilterDatePartsAndOrderAsc(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +" AND p_r.last_modification >= :param ORDER BY p.description DESC" , nativeQuery = false)
    List<PartDTOResponse> findFilterDatePartsAndOrderDesc(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryTest +" AND p_r.last_modification >= :param ORDER BY p_r.last_modification" , nativeQuery = false)
    List<PartDTOResponse> findFilterDatePartsAndOrderDate(@Param("param") LocalDate param);

    String queryStart = "";

    @Query(value= queryStart + " SELECT new com.mercadolibre.varela_agustina.dtos.PartDTO(id_part, description, long_dimension as longDimension, net_weight as netWeight, part_code as partCode, tall_dimension as tallDimension, width_dimension as widthDimension, provider)" +
            " FROM Part " +
            " WHERE id_part= :partId OR part_code= :codePart ",nativeQuery = false)
    PartDTO findPartByIDOrCode(@Param("partId") String partId, @Param("codePart") String codePart);



    @Query(value= queryStart + " INSERT INTO part " +
            " (description,long_dimension,net_weight,part_code,tall_dimension,width_dimension,id_provider,id_part_price) " +
            " VALUES" +
            " ( :descriptionPart , :longDimension , :netWeight , :codePart , :tallDimension , :widthDimension , :idProvider, :idPartPrice); ",nativeQuery = true)
    @Modifying
    @Transactional
    void insertPart(@Param("codePart") Integer codePart,@Param("descriptionPart") String descriptionPart,
                    @Param("longDimension") Double longDimension,@Param("tallDimension") Double tallDimension,@Param("netWeight") Double netWeight,@Param("widthDimension") Double widthDimension,
                    @Param("idProvider") long idProvider, @Param("idPartPrice") long idPartPrice);

    @Query(value="SELECT MAX(id_part) FROM part ; ",nativeQuery = true)
    Long findMaxPartId();

    @Query(value=queryStart + " UPDATE part SET id_part_price= :idPartPrice WHERE id_part= :idPart ;",nativeQuery = true)
    @Modifying
    @Transactional
    void updateIdPartPrice(@Param("idPart") Long partId,@Param("idPartPrice") Integer idPartPrice);

    @Query(value=queryStart + " SELECT id_part_price " +
            " FROM part " +
            " WHERE id_part= :partId  ;",nativeQuery = true)
    Integer findPartIdPartPrice(@Param("partId") String partId);

    String queryOffer = "SELECT distinct new com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse(p.id_part, p.description, p.net_weight as netWeight, p.long_dimension as longDimension, p.width_dimension as widthDimension, p.tall_dimension as tallDimension) " +
            "            from Part p\n" +
            "            inner JOIN OrderDetailCM od on od.part = p.id_part\n" +
            "            inner JOIN OrderCM o on od.id_order_cm = o.id_order_cm\n" +
            "            left JOIN StockCM s ON s.part=p.id_part\n" +
            "            WHERE s.quantity > 0\n";

    @Transactional
    @Query(value = queryOffer +" AND o.order_date >= :param " , nativeQuery = false)
    List<PartDTOResponse> getPartsWithOrderAfter(@Param("param") LocalDate param);

    @Transactional
    @Query(value = queryOffer +" AND o.order_date < :param " , nativeQuery = false)
    List<PartDTOResponse> getPartsWithOrderBefore(@Param("param") LocalDate param);

    String queryParts = "SELECT distinct new com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse(p.id_part, p.description, p.net_weight as netWeight, p.long_dimension as longDimension, p.width_dimension as widthDimension, p.tall_dimension as tallDimension) " +
            "            from Part p\n";

    @Transactional
    @Query(value = queryParts , nativeQuery = false)
    List<PartDTOResponse> getParts();

}
