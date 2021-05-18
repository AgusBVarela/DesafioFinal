package com.mercadolibre.varela_agustina.dtos.responses;

import com.mercadolibre.varela_agustina.dtos.requests.PartDTORequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPartDTOResponse {
    private Integer code;
    private String message;
    private PartDTORequest part;
}
