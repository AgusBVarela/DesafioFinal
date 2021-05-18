package com.mercadolibre.varela_agustina.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_status")
public class PartStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_part_status;
    private String code;
    private String description;
}
