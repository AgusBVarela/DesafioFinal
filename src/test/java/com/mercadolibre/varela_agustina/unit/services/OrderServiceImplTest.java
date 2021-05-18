package com.mercadolibre.varela_agustina.unit.services;

import com.mercadolibre.varela_agustina.exceptions.ApiException;
import com.mercadolibre.varela_agustina.repository.*;
import com.mercadolibre.varela_agustina.services.OrderServiceImpl;
import com.mercadolibre.varela_agustina.unit.fixtures.OrderFixture;
import com.mercadolibre.varela_agustina.unit.fixtures.PartFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {
    @Mock
    OrderCMRepository orderCMRepository;
    @Mock
    OrderDetailCMRepository orderDetailCMRepository;

    @Mock
    OrderStatusRepository orderStatusRepository;

    @Mock
    StockCMRepository stockCMRepository;
    @Mock
    PartRepository partRepository;

    @Mock
    StockSubsidiaryRepository stockSubsidiaryRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Devuelve una lista de ordenes de un Dealer especifico")
    void getAllOrderListFromDealer() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        when(orderCMRepository.findPartOrderDetail(1)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(2)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(3)).thenReturn(OrderFixture.getListDetails());



        Assertions.assertEquals(orderService.getOrderList(mapParameters), OrderFixture.getOrderDTOResponse());
    }

    @Test
    @DisplayName("Devuelve una lista de ordenes de un Dealer especifico, que tengan un status especifico")
    void getOrderListFromDealerWithSpecificStatus() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");
        mapParameters.put("deliveryStatus", "C");

        when(orderCMRepository.findAllOrders(mapParameters.get("deliveryStatus"))).thenReturn(OrderFixture.getAllOrdersWithSameDeliveryStatus());

        when(orderCMRepository.findPartOrderDetail(1)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(2)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(3)).thenReturn(OrderFixture.getListDetails());



        Assertions.assertEquals(orderService.getOrderList(mapParameters), OrderFixture.getOrderDTOResponseWithSpecificDeliveryStatus());
    }

    @Test
    @DisplayName("Devuelve una lista de ordenes de un Dealer especifico, ordenada ascendentemente")
    void getAllOrderListFromDealerOrderByAsc() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");
        mapParameters.put("order", "1");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        when(orderCMRepository.findPartOrderDetail(1)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(2)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(3)).thenReturn(OrderFixture.getListDetails());


        Assertions.assertEquals(orderService.getOrderList(mapParameters), OrderFixture.getOrderDTOResponseOrderByASC());
    }

    @Test
    @DisplayName("Devuelve una lista de ordenes de un Dealer especifico, ordenada descendentemente")
    void getAllOrderListFromDealerOrderByDesc() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");
        mapParameters.put("order", "2");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        when(orderCMRepository.findPartOrderDetail(1)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(2)).thenReturn(OrderFixture.getListDetails());
        when(orderCMRepository.findPartOrderDetail(3)).thenReturn(OrderFixture.getListDetails());



        Assertions.assertEquals(orderService.getOrderList(mapParameters), OrderFixture.getOrderDTOResponseOrderByDesc());
    }

    @Test
    @DisplayName("Devuelve una excepcion por no tener en el parametro un dealerNumber")
    void throwExceptionBecauseNotHavingDealerNumber() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("order", "2");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        Assertions.assertThrows(ApiException.class, () -> orderService.getOrderList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por enviar un dealerNumber invalido")
    void throwExceptionBecauseOfInvalidDealerNumber() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "14M");
        mapParameters.put("order", "2");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        Assertions.assertThrows(ApiException.class, () -> orderService.getOrderList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por tener una respuesta vacia")
    void throwExceptionBecauseOfEmptyResponse() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "11");
        mapParameters.put("order", "2");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        Assertions.assertThrows(ApiException.class, () -> orderService.getOrderList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por tener deliveryStatus invalido")
    void throwExceptionBecauseOfInvalidDeliveryStatus() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("dealerNumber", "1");
        mapParameters.put("deliveryStatus", "ERROR");

        when(orderCMRepository.findAllOrdersDealer()).thenReturn(OrderFixture.getAllOrders());

        Assertions.assertThrows(ApiException.class, () -> orderService.getOrderList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuleve una order con sus detalles correspondientes")
    void getOrderNumberDetail() throws Exception {
        String orderNumber = "0000-0000-00000001";

        when(orderCMRepository.findOrderNumberCM(1L)).thenReturn(OrderFixture.getOrderDetailNumber());
        when(orderDetailCMRepository.getPartOrderNumber(1L)).thenReturn(OrderFixture.getListDetails());

        Assertions.assertEquals(orderService.getOrderNumberDetail(orderNumber), OrderFixture.getOrder());
    }

    @Test
    @DisplayName("Devuleve una excepcion por numero de orden incorrecto")
    void throwsExceptionBecauseOfInvalidOrdenNumber() throws Exception {
        String orderNumber = "0000-0000-000100";

        when(orderCMRepository.findOrderNumberCM(1L)).thenReturn(OrderFixture.getOrderDetailNumber());

        Assertions.assertThrows(ApiException.class, () -> orderService.getOrderNumberDetail(orderNumber), "ERROR");
    }

    @Test
    @DisplayName("Devuleve una excepcion por numero de orden incorrectoe en la actualizacion")
    void throwsExceptionInvalidOrdenNumberInUpdateStatus() throws Exception {

        when(orderCMRepository.getIdOrderCM(any())).thenReturn(null);

        Assertions.assertThrows(ApiException.class, () -> orderService.updateOrdenStatus(OrderFixture.getStatusDTORequest()), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por codeStatus incorrecto en la actualizacion de orden.")
    void throwsExceptionInvalidCodeStatusInUpdateStatus() throws Exception {
        Assertions.assertThrows(ApiException.class, () -> orderService.updateOrdenStatus(OrderFixture.getInvalidStatusDTORequest()), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una nuevo orderDTO")
    void getNewOrder() throws Exception {

        when(stockCMRepository.findByIdPart(any())).thenReturn(PartFixture.getStockDTO());

        when(orderCMRepository.getIdOrderCMInsert()).thenReturn(1);

        when(orderStatusRepository.findByIdCode(OrderFixture.getUpdateOrderStatusDto().getCodestatus())).thenReturn(OrderFixture.getOrderStatusDto());

        Assertions.assertEquals(orderService.updateOrdenStatus(OrderFixture.getUpdateOrderStatusDto()), OrderFixture.getNewOrderDTOResponseUpdated());
    }

    @Test
    @DisplayName("Devuelve una nuevo orderDTO")
    void updateOrder() throws Exception {

        when(stockCMRepository.findByIdPart(any())).thenReturn(PartFixture.getStockDTO());

        when(orderCMRepository.getIdOrderCMInsert()).thenReturn(1);

        Assertions.assertEquals(orderService.newOrder(OrderFixture.getNewOrderDTORequest()), OrderFixture.getNewOrderResponse());
    }

    @Test
    @DisplayName("Devuleve una excepcion por invalid part list al crear un nuevo order siendo parts una lista vacía.")
    void throwsExceptionInvalidPartListNewOrder() throws Exception {
        Assertions.assertThrows(ApiException.class, () -> orderService.newOrder(OrderFixture.getNewInvalidOrderDTORequest()), "ERROR");
    }

    @Test
    @DisplayName("Devuleve una excepcion 'Stock not found' cuando el mismo no se encuentra'.")
    void throwsExceptionStockNotFoundNewOrder() throws Exception {
        when(stockCMRepository.findByIdPart(any())).thenReturn(null);
        Assertions.assertThrows(ApiException.class, () -> orderService.newOrder(OrderFixture.getNewOrderDTORequest()), "ERROR");
    }
    @Test
    @DisplayName("Devuleve una excepcion cuando la cantidad solicitada es mayor al stock disponible.")
    void throwsExceptionStockQuantityNewOrder() throws Exception {
         when(stockCMRepository.findByIdPart(any())).thenReturn(PartFixture.getStockDTO()); //trae 100

        Assertions.assertThrows(ApiException.class, () -> orderService.newOrder(OrderFixture.getNewOrderBigQuantityDTORequest()), "ERROR");
    }

    @Test
    @DisplayName("Modifica todas las ordenes a finalizado")
    void updateOrderStatusF() throws Exception {
        when(orderCMRepository.getIdOrderCM(1L)).thenReturn(Math.toIntExact(OrderFixture.getUpdateOrderStatusDtoF().getIdorden())); //trae 100
        when(orderStatusRepository.findByIdCode(OrderFixture.getUpdateOrderStatusDtoF().getCodestatus())).thenReturn(OrderFixture.getOrderStatusDto());
        when(orderCMRepository.findOrderNumberCM(1L)).thenReturn(OrderFixture.getOrderDetailNumber());
        when(partRepository.findPartByIDOrCode("", "90874389")).thenReturn(PartFixture.getPartDTO());
        when(partRepository.findPartByIDOrCode("", "39729828")).thenReturn(PartFixture.getPartDTO());
        when(partRepository.findPartByIDOrCode("", "89134622")).thenReturn(PartFixture.getPartDTO());
        when(stockSubsidiaryRepository.getId(OrderFixture.getOrderDetailNumber().getIdDealer(), Math.toIntExact(PartFixture.getNewPartDTO().getIdpart()))).thenReturn(0);
        when(stockCMRepository.findByIdPart(any())).thenReturn(PartFixture.getStockDTO());
        when(orderDetailCMRepository.getPartOrderNumber(1L)).thenReturn(OrderFixture.getListDetails());
        Assertions.assertEquals(orderService.updateOrdenStatus(OrderFixture.getUpdateOrderStatusDtoF()), OrderFixture.getNewOrderDTOResponseUpdated());
    }


}