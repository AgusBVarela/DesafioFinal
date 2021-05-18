package com.mercadolibre.varela_agustina.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_cm")
public class OrderCM {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_order_cm;


    private LocalDate order_date;
    private Integer days_delayed;
    private String serial_number;
    private LocalDate delibery_date;

    @ManyToOne
    @JoinColumn(name = "id_shipping", nullable = false)
    private ShippingType shipping;
    @ManyToOne
    @JoinColumn(name = "id_delivery_status", nullable = false)
    private DeliveryStatus delivery_status;
    @ManyToOne
    @JoinColumn(name = "id_carrier", nullable = false)
    private Carrier carrier;
    @ManyToOne
    @JoinColumn(name = "id_order_status", nullable = false)
    private OrderStatus order_status;
    @ManyToOne
    @JoinColumn(name = "id_order_type", nullable = false)
    private OrderType order_type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subsidiary",referencedColumnName = "id")
    private Account subsidiary;


}
