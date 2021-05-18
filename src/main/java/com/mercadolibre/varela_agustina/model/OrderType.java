package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_type")
public class OrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_order_status;
    private Long code;
    private String description;

    @OneToMany(mappedBy = "order_type")
    private Set<OrderCM> order_cm;
}
