package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_response")
public class PartResponse {

    @Id
    private Integer part_code;
    private String description;
    private String maker;
    private int quantity;
    private String discount_type;
    private double normal_price;
    private double urgent_price;
    private Integer net_weight;
    private Integer long_dimension;
    private Integer width_dimension;
    private Integer tall_dimension;
    private String last_modification;
}
