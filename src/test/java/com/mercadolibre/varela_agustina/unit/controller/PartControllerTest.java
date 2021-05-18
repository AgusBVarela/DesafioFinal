package com.mercadolibre.varela_agustina.unit.controller;

import com.mercadolibre.varela_agustina.controller.PartController;
import com.mercadolibre.varela_agustina.services.OrderService;
import com.mercadolibre.varela_agustina.services.PartService;
import com.mercadolibre.varela_agustina.unit.fixtures.OrderFixture;
import com.mercadolibre.varela_agustina.unit.fixtures.PartFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PartControllerTest {

    @Mock
    PartService partService;
    @Mock
    OrderService orderService;

    private PartController partController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        partController = new PartController(partService, orderService);
    }

    @Test
    void getPartsList() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();

        when(partService.getPartsList(mapParameters)).thenReturn(PartFixture.getPartListResponseDto());

        ResponseEntity responseEntity = new ResponseEntity(PartFixture.getPartListResponseDto(), HttpStatus.OK);


        Assertions.assertEquals(partController.getPartsList(mapParameters), responseEntity);
    }

    @Test
    void getOrdersList() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");

        when(orderService.getOrderList(mapParameters)).thenReturn(OrderFixture.getOrderDTOResponse());

        ResponseEntity responseEntity = new ResponseEntity(OrderFixture.getPartListResponseDTO(), HttpStatus.OK);

        Assertions.assertEquals(partController.getOrdersList(mapParameters), responseEntity);
    }

    @Test
    void getOrdersNumberList() throws Exception {
        String orderNumberCM = "1";

        when(orderService.getOrderNumberDetail(orderNumberCM)).thenReturn(OrderFixture.getOrder());

        ResponseEntity responseEntity = new ResponseEntity(OrderFixture.getOrder(), HttpStatus.OK);

        Assertions.assertEquals(partController.getOrdersNumberList(orderNumberCM), responseEntity);
    }

    @Test
    void newOrder() throws Exception {
        when(orderService.newOrder(any())).thenReturn(OrderFixture.getNewOrderResponse());

        ResponseEntity responseEntity = new ResponseEntity(OrderFixture.getNewOrderResponse(), HttpStatus.OK);

        Assertions.assertEquals(partController.newOrder(OrderFixture.getNewOrderDTORequest()), responseEntity);
    }

    @Test
    void updateStatus() throws Exception {
        when(orderService.updateOrdenStatus(any())).thenReturn(OrderFixture.getStatusDTOResponse());

        ResponseEntity responseEntity = new ResponseEntity(OrderFixture.getStatusDTOResponse(), HttpStatus.OK);

        Assertions.assertEquals(partController.updateStatus(OrderFixture.getStatusDTORequest()), responseEntity);
    }

    @Test
    void newPart() throws Exception {
        when(partService.newPart(PartFixture.getNewPartDTO())).thenReturn(PartFixture.getNewPart());

        ResponseEntity responseEntity = new ResponseEntity(PartFixture.getNewPart(), HttpStatus.OK);

        Assertions.assertEquals(partController.newPart(PartFixture.getNewPartDTO()), responseEntity);
    }

    @Test
    void updatePart() throws Exception {
        when(partService.newPart(PartFixture.getUpdatedPartDTO())).thenReturn(PartFixture.getUpdatedPart());

        ResponseEntity responseEntity = new ResponseEntity(PartFixture.getUpdatedPart(), HttpStatus.OK);

        Assertions.assertEquals(partController.newPart(PartFixture.getUpdatedPartDTO()), responseEntity);
    }


    @Test
    void getPartsToOffer() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();

        when(partService.getPartsToOffer(mapParameters)).thenReturn(PartFixture.getPartListResponseDto());

        ResponseEntity responseEntity = new ResponseEntity(PartFixture.getPartListResponseDto(), HttpStatus.OK);

        Assertions.assertEquals(partController.getPartsToOffer(mapParameters), responseEntity);
    }
    @Test
    void getPartsWithOrder() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();

        when(partService.getPartsWithOrder(mapParameters)).thenReturn(PartFixture.getPartListResponseDto());

        ResponseEntity responseEntity = new ResponseEntity(PartFixture.getPartListResponseDto(), HttpStatus.OK);

        Assertions.assertEquals(partController.getPartsWithOrder(mapParameters), responseEntity);
    }
}