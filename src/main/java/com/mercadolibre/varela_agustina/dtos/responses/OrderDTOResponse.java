package com.mercadolibre.varela_agustina.dtos.responses;

import com.mercadolibre.varela_agustina.dtos.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOResponse {
    private String dealerNumber;
    private List<OrderDTO> orders;
}
