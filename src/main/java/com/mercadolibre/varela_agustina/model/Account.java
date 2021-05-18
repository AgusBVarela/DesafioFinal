package com.mercadolibre.varela_agustina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username, password;
    private Integer rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_dealer", referencedColumnName = "id_dealer")
    @JsonIgnore // todo esto no lo pone Damian
    private Dealer id_dealer;
}