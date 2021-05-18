package com.mercadolibre.varela_agustina.dtos;

import com.mercadolibre.varela_agustina.model.Dealer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderFindDTO {

    private Long idOrderCM;
    private Long idDealer;
    private Long idMatrixHouse;


    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private int daysDelayed;
    private String deliveryStatus;
    private String orderStatus;

    public OrderFindDTO(LocalDate orderDate, LocalDate deliveryDate, int daysDelayed, String deliveryStatus, Dealer idDealer, Long idOrderCM, Long idMatrixHouse) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.daysDelayed = daysDelayed;
        this.deliveryStatus = deliveryStatus;
        this.idDealer = idDealer.getId_dealer();
        this.idOrderCM = idOrderCM;
        this.idMatrixHouse = idMatrixHouse;
    }
}
