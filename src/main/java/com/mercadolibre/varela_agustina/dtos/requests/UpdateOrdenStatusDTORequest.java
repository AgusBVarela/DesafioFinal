package com.mercadolibre.varela_agustina.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrdenStatusDTORequest {
    @NotNull(message = "'idorden' no puede ser nulo")
    @PositiveOrZero(message = "'idorden' no puede ser nulo o negativo")
    private Long idorden;

    @NotNull(message = "'codestatus' no puede ser nulo")
    private String codestatus;
}
