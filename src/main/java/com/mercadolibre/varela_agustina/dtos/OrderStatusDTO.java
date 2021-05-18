package com.mercadolibre.varela_agustina.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderStatusDTO {
    private Long id_order_type;
    private String code;
    private String description;

    public OrderStatusDTO(Long idOrdenStatus, String code, String description) {
        this.id_order_type = idOrdenStatus;
        this.code = code;
        this.description = description;
    }
}
