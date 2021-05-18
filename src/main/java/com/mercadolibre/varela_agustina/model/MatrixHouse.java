package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matrix_house")
public class MatrixHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_matrix_house;
    private String name;
    private String site;

}
