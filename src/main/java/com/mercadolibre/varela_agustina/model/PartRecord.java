package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_record")
public class PartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_part_record")
    private Long id_part_record;

    private LocalDate last_modification;
    private Double normal_price;
    private Double sale_price;
    private Double urgent_price;

    private String description;
    private Integer width_dimension;
    private Integer tall_dimension;
    private Integer long_dimension;
    private Integer net_weight;
    private Boolean update_price;

    @ManyToOne
    @JoinColumn(name = "id_discount_rate", nullable = false)
    private DiscountRate discount_rate;

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private Part part;

}
