package com.mercadolibre.varela_agustina.dtos.requests;

import com.mercadolibre.varela_agustina.dtos.PartPriceDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@Data
@NoArgsConstructor
public class PartDTORequest {


    private long idpart;

    @Digits(integer=8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos")
    private int partcode;

    @Positive(message = "widthdimension no puede ser nulo, cero o negativo")
    private double widthdimension;

    @Positive(message = "talldimension no puede ser nulo, cero o negativo")
    private double talldimension;

    @Positive(message = "longdimension no puede ser nulo, cero o negativo")
    private double longdimension;

    @Positive(message = "netweight no puede ser nulo, cero o negativo")
    private double netweight;

    @NotNull(message = "provider no puede ser nulo")
    private Long provider;


    @NotNull(message = "description no puede ser nulo")
    private String description;

    @PositiveOrZero(message = "quantity no puede ser negativo")
    private int quantity;

    private PartPriceDTO partprice;


    public PartDTORequest(@Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double widthdimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double talldimension, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @NotNull(message = "provider no puede ser nulo") Long provider, @NotNull(message = "description no puede ser nulo") String description, @PositiveOrZero(message = "quantity no puede ser negativo") int quantity, PartPriceDTO partPrice) {
        this.partcode = partcode;
        this.widthdimension = widthdimension;
        this.talldimension = talldimension;
        this.longdimension = longdimension;
        this.netweight = netweight;
        this.provider = provider;
        this.description = description;
        this.quantity = quantity;
        this.partprice = partPrice;
    }


}
