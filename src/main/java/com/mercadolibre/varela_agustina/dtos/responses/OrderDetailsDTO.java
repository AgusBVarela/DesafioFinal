package com.mercadolibre.varela_agustina.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDTO {
    private int partCode;
    private String description;
    private int quantity;
    private String accountType;
    private String reason;
    private String partStatus;

    public OrderDetailsDTO(int partCode, String description, int quantity, String accountType, String reason) {
        this.partCode = partCode;
        this.description = description;
        this.quantity = quantity;
        this.accountType = accountType;
        this.reason = reason;
    }

    public OrderDetailsDTO(int partCode, String description, int quantity, String accountType, String reason, String partStatus) {
        this.partCode = partCode;
        this.description = description;
        this.quantity = quantity;
        this.accountType = accountType;
        this.reason = reason;
        this.partStatus = partStatus;
    }
}
