package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dealer")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_dealer;
    private String name;
    private String country;
    private String addres;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_matrix_house", referencedColumnName = "id_matrix_house")
    private MatrixHouse id_matrix_house;

    // TODO creo que falta el @OneToMany(mappedBy= )

}
