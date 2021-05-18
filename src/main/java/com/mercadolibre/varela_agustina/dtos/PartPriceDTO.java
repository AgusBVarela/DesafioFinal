package com.mercadolibre.varela_agustina.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartPriceDTO {


    private Long id_part_price;
    private Double price;
    private Double urgent_price;
    private Double sale_price;

    public PartPriceDTO(Long id_part_price, Double price, Double urgent_price, Double sale_price) {
        this.id_part_price = id_part_price;
        this.price = price;
        this.urgent_price = urgent_price;
        this.sale_price = sale_price;
    }


}
