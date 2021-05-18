package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_price")
public class PartPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_part_price;

    @OneToOne(mappedBy = "part_price")
    private Part part;

    private Double price;
    private Double urgent_price;
    private Double sale_price;
}
