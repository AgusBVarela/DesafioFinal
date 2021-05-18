package com.mercadolibre.varela_agustina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_part;

    @Column(nullable = false, length = 8)
    private Integer part_code;

    private String description;
    private double width_dimension;
    private double tall_dimension;
    private double long_dimension;
    private double net_weight;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false)
    private Provider provider;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "part")
    @JsonIgnore
    private Set<StockCM> stock;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "part")
    @JsonIgnore
    private Set<PartRecord> part_record;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "part")
    @JsonIgnore
    private Set<OrderDetailCM> order_details_cm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_part_price",referencedColumnName = "id_part_price")
    private PartPrice part_price;


}
