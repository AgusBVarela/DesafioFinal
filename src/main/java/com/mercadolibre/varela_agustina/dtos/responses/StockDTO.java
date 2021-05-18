package com.mercadolibre.varela_agustina.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StockDTO {

    private Long id_stock_cm;
    private Long quantity;

    public StockDTO(Long id_stock_cm, Long quantity) {
        this.id_stock_cm = id_stock_cm;
        this.quantity = quantity;
    }

}
