package com.mercadolibre.varela_agustina.controller;

import com.mercadolibre.varela_agustina.dtos.OrderDTO;
import com.mercadolibre.varela_agustina.dtos.requests.NewOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.PartDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.UpdateOrdenStatusDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartsListResponseDTO;
import com.mercadolibre.varela_agustina.services.OrderService;
import com.mercadolibre.varela_agustina.services.PartService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {
    private final PartService partService;
    private final OrderService orderService;

    public PartController(PartService partService, OrderService orderService) {
        this.partService = partService;
        this.orderService = orderService;
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity getPartsList(@RequestParam(required = false) Map<String, String> allParams) throws Exception {
        return new ResponseEntity(partService.getPartsList(allParams), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity newPart(@Valid @RequestBody PartDTORequest partDto) throws Exception {
        return new ResponseEntity(partService.newPart(partDto), HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity getOrdersList(@RequestParam Map<String, String> allParams) throws Exception {

        OrderDTOResponse listOrders = orderService.getOrderList(allParams);
        return new ResponseEntity(listOrders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderNumberCM}")
    public ResponseEntity getOrdersNumberList(@PathVariable String orderNumberCM) throws Exception {

        OrderDTO order = orderService.getOrderNumberDetail(orderNumberCM);
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity newOrder(@Valid @RequestBody NewOrderDTORequest orderDto) throws Exception {
        return new ResponseEntity(orderService.newOrder(orderDto), HttpStatus.OK);
    }

    @PostMapping("/orders/update_status")
    @ResponseBody
    public ResponseEntity updateStatus(@Valid @RequestBody UpdateOrdenStatusDTORequest orderDto) throws Exception {
        return new ResponseEntity(orderService.updateOrdenStatus(orderDto), HttpStatus.OK);
    }

    @GetMapping("/charges")
    public ResponseEntity getPartsWithOrder(@RequestParam Map<String, String> allParams) throws Exception {
        /*Todas las partes que tienen algun encargo (orden) a partir (inclusive) la fecha ingresada por parámetro.*/
        PartsListResponseDTO parts = partService.getPartsWithOrder(allParams);
        return new ResponseEntity(parts, HttpStatus.OK);
    }
    @GetMapping("/offer")
    public ResponseEntity getPartsToOffer(@RequestParam Map<String, String> allParams) throws Exception {
        PartsListResponseDTO order = partService.getPartsToOffer(allParams);
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
