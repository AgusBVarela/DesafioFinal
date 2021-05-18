package com.mercadolibre.varela_agustina.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_status")
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_delivery_status;
    private String code;
    private String description;

    @OneToMany(mappedBy = "delivery_status")
    private Set<OrderCM> order_cm;
}
