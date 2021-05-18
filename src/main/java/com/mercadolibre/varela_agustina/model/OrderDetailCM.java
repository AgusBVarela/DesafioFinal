package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail_cm")
public class OrderDetailCM {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_order_detail_cm;

    private Integer quantity;

    private String reason;

    // No esta la relacion con Order CM

    @ManyToOne
    @JoinColumn(name = "id_part", nullable = false)
    private Part part;

    @ManyToOne
    @JoinColumn(name = "id_account_type", nullable = false)
    private AccountType account_type;

    @ManyToOne
    @JoinColumn(name = "id_part_status", nullable = false)
    private PartStatus part_status;

    @ManyToOne
    @JoinColumn(name="id_order_cm",referencedColumnName = "id_order_cm")
    private OrderCM id_order_cm;

}
