package com.mercadolibre.varela_agustina.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDetailNumberDTO {

    public Long idDealer;
    public Long idOrderCM;
    public LocalDate orderDate;
    public String orderStatus;

    public OrderDetailNumberDTO(Long idDealer, Long idOrderCM, LocalDate orderDate, String orderStatus) {
        this.idDealer = idDealer;
        this.idOrderCM = idOrderCM;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
