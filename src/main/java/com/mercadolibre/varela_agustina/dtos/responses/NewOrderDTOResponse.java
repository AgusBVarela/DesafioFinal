package com.mercadolibre.varela_agustina.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDTOResponse {
    private Integer code;
    private String message;
}
