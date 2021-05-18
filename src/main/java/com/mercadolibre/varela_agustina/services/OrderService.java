package com.mercadolibre.varela_agustina.services;

import com.mercadolibre.varela_agustina.dtos.OrderDTO;
import com.mercadolibre.varela_agustina.dtos.requests.NewOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.UpdateOrdenStatusDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.NewOrderDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDTOResponse;

import java.util.Map;

public interface OrderService {
    OrderDTOResponse getOrderList(Map<String, String> params) throws Exception;
    OrderDTO getOrderNumberDetail(String orderNumber) throws Exception;
    NewOrderDTOResponse newOrder(NewOrderDTORequest orderDto) throws Exception;
    NewOrderDTOResponse updateOrdenStatus(UpdateOrdenStatusDTORequest ordenStatusDto) throws Exception;
}
