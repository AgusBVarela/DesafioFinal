package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_provider;

    private String name;
    private String address;
    private Long phone;
    private String country;

    @OneToMany(mappedBy = "provider")
    private Set<Part> parts;
}
