package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount_rate")
public class DiscountRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_discount_rates;
    private String description;
    private Double discount;

    @OneToMany(mappedBy = "discount_rate")
    private Set<PartRecord> part_records;
}
