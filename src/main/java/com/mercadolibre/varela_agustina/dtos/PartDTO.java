package com.mercadolibre.varela_agustina.dtos;

import com.mercadolibre.varela_agustina.model.Provider;
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
public class PartDTO {
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
    @NotNull(message = "partprice no puede ser nulo")
    private Long partprice;
    @NotNull(message = "description no puede ser nulo")
    private String description;
    @PositiveOrZero(message = "quantity no puede ser negativo")
    private int quantity;
    public PartDTO(@Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode) {
        this.partcode = partcode;
    }
    public PartDTO( @NotNull(message = "description no puede ser nulo") String description, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double talldimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double widthdimension, @NotNull(message = "provider no puede ser nulo") Provider provider) {
        this.partcode = partcode;
        this.widthdimension = widthdimension;
        this.talldimension = talldimension;
        this.longdimension = longdimension;
        this.netweight = netweight;
        this.provider = provider.getId_provider();
        this.description = description;
    }
    public PartDTO(@NotNull(message = "description no puede ser nulo") String description, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double talldimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double widthdimension, @NotNull(message = "partPrice no puede ser nulo")Long partPrice, @NotNull(message = "provider no puede ser nulo") Provider provider) {
        this.partcode = partcode;
        this.widthdimension = widthdimension;
        this.talldimension = talldimension;
        this.longdimension = longdimension;
        this.netweight = netweight;
        this.provider = provider.getId_provider();
        this.description = description;
    }
    public PartDTO( @NotNull(message = "description no puede ser nulo") String description, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double talldimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double widthdimension, @NotNull(message = "provider no puede ser nulo") Provider provider, int quantity) {
        this.partcode = partcode;
        this.widthdimension = widthdimension;
        this.talldimension = talldimension;
        this.longdimension = longdimension;
        this.netweight = netweight;
        this.provider = provider.getId_provider();
        this.description = description;
        this.quantity = quantity;
    }


    public PartDTO(long idpart, @NotNull(message = "description no puede ser nulo") String description, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @Digits(integer = 8, fraction = 0, message = "partcode debe estar compuesto por 8 dígitos") int partcode, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double talldimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double widthdimension, @NotNull(message = "provider no puede ser nulo") Provider provider) {
        this.idpart = idpart;
        this.partcode = partcode;
        this.widthdimension = widthdimension;
        this.talldimension = talldimension;
        this.longdimension = longdimension;
        this.netweight = netweight;
        this.provider = provider.getId_provider();
        this.partprice = idpart;
        this.description = description;
    }
}

