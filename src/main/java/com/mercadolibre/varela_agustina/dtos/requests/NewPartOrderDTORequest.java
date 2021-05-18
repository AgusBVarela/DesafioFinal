package com.mercadolibre.varela_agustina.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPartOrderDTORequest {

    @NotNull(message = "'idpart' no puede ser nulo")
    @PositiveOrZero(message = "'idpart' no puede ser nulo o negativo")
    private long idpart;

    @NotNull(message = "'idaccounttype' no puede ser nulo")
    @Positive(message = "'idaccounttype' no puede ser nulo o negativo")
    private Integer idaccounttype;

    @NotNull(message = "'quantity' no puede ser nulo")
    @Positive(message = "'quantity' no puede ser nulo, cero o negativo")
    private Integer quantity;


    private String reason;
}
