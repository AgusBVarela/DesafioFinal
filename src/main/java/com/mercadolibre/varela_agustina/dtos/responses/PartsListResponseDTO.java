package com.mercadolibre.varela_agustina.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartsListResponseDTO {
    private List<PartDTOResponse> parts;
}
