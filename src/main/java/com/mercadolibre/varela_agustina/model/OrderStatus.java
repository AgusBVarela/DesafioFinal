package com.mercadolibre.varela_agustina.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_order_type;
    private String code;
    private String description;

    @OneToMany(mappedBy = "order_status")
    private Set<OrderCM> order_cm;
}
