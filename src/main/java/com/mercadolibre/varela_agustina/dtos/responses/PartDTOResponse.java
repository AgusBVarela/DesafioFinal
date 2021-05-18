package com.mercadolibre.varela_agustina.dtos.responses;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Data
@NoArgsConstructor

public class PartDTOResponse {

    private Long idPart;
    private String description;
    private String maker;
    private Long quantity;
    private String discountType;
    private Double normalPrice;
    private Double urgentPrice;
    private Double netWeight;
    private Double longDimension;
    private Double widthDimension;
    private Double tallDimension;
    private String lastModification;

    public PartDTOResponse(Long id_part, String description, String maker, Long quantity, String discountType, Double normalPrice, Double urgentPrice, Integer netWeight, Integer longDimension, Integer widthDimension, Integer tallDimension, LocalDate lastmodif) {
        this.idPart = id_part;
        this.description = description;
        this.maker = maker;
        this.quantity = quantity;
        this.discountType = discountType;
        this.normalPrice = normalPrice;
        this.urgentPrice = urgentPrice;
        this.netWeight = Double.valueOf(netWeight);
        this.longDimension = Double.valueOf(longDimension);
        this.widthDimension = Double.valueOf(widthDimension);
        this.tallDimension = Double.valueOf(tallDimension);
        this.lastModification = lastmodif.toString();
    }


    public PartDTOResponse(long idpart, @NotNull(message = "description no puede ser nulo") String description, @Positive(message = "netweight no puede ser nulo, cero o negativo") double netweight, @Positive(message = "longdimension no puede ser nulo, cero o negativo") double longdimension, @Positive(message = "talldimension no puede ser nulo, cero o negativo") double widthdimension, @Positive(message = "widthdimension no puede ser nulo, cero o negativo") double talldimension) {
        this.idPart = idpart;
        this.widthDimension = widthdimension;
        this.tallDimension = talldimension;
        this.longDimension = longdimension;
        this.netWeight = netweight;
        this.description = description;
    }



}
