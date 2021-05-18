package com.mercadolibre.varela_agustina.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderNumberCM;
    private String orderNumberCE;

    /*@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String orderDate;
    /*@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String deliveryDate;

    private String daysDelayed;
    private String deliveryStatus;
    private String orderStatus;
    private List<OrderDetailsDTO> orderDetails;
}
