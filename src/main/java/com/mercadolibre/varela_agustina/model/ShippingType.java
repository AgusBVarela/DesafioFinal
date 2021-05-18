package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipping_type")
public class ShippingType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_shipping_type;
    private Integer code;
    private String description;

    @OneToMany(mappedBy = "shipping")
    private Set<OrderCM> order_cm;
}
