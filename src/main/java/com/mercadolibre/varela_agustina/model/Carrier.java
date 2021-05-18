package com.mercadolibre.varela_agustina.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carrier")
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_carrier;
    private String name;
    private String address;
    private String phone;
    private String country;

    @OneToMany(mappedBy = "carrier")
    private Set<OrderCM> order_cm;
}
