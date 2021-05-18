package com.mercadolibre.varela_agustina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @JsonIgnore
    private String role;
    @Column
    @JsonIgnore
    private String country;
    @Column
    @JsonIgnore
    private String phone;
    @Column
    @JsonIgnore
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_dealer",referencedColumnName = "id_dealer")
    @JsonIgnore
    private Dealer id_dealer;

}
