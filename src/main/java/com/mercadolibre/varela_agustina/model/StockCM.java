package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_cm")
public class StockCM {
    @Id
    @Column(name = "id_stock_cm")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_stock_cm;

    @Column(name = "quantity", precision = 8)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private Part part;
}
