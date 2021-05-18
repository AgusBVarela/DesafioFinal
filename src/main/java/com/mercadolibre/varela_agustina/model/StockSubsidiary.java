package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_subsidiary")
public class StockSubsidiary {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id", referencedColumnName = "id_part")
    private Part part;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealear_id", referencedColumnName = "id_dealer")
    private Dealer dealear_id;

    private Integer quantity;


}
