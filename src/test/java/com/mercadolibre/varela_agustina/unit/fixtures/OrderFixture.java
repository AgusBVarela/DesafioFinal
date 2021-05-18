package com.mercadolibre.varela_agustina.unit.fixtures;

import com.mercadolibre.varela_agustina.dtos.OrderDTO;
import com.mercadolibre.varela_agustina.dtos.OrderDetailNumberDTO;
import com.mercadolibre.varela_agustina.dtos.OrderFindDTO;
import com.mercadolibre.varela_agustina.dtos.OrderStatusDTO;
import com.mercadolibre.varela_agustina.dtos.requests.NewOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.NewPartOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.UpdateOrdenStatusDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.*;
import com.mercadolibre.varela_agustina.model.Dealer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderFixture {
    public static List<OrderFindDTO> getAllOrders() {
        List<OrderFindDTO> listaADevolver = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate1 = LocalDate.parse("2020-10-04", formatter);
        LocalDate deliveryDate1 = LocalDate.parse("2020-10-02", formatter);

        LocalDate orderDate2 = LocalDate.parse("2020-12-15", formatter);
        LocalDate deliveryDate2 = LocalDate.parse("2020-12-14", formatter);

        LocalDate orderDate3 = LocalDate.parse("2021-01-22", formatter);
        LocalDate deliveryDate3 = LocalDate.parse("2021-01-20", formatter);

        Dealer dealer1 = new Dealer();
        dealer1.setId_dealer(Long.parseLong("1"));
        Dealer dealer2 = new Dealer();
        dealer2.setId_dealer(Long.parseLong("1"));
        Dealer dealer3 = new Dealer();
        dealer3.setId_dealer(Long.parseLong("3"));

        OrderFindDTO orderFindDTO1 = new OrderFindDTO(orderDate1, deliveryDate1, 2, "C", dealer1, Long.parseLong("2"), Long.parseLong("1"));
        OrderFindDTO orderFindDTO2 = new OrderFindDTO(orderDate2, deliveryDate2, 1, "D", dealer2, Long.parseLong("1"), Long.parseLong("1"));
        OrderFindDTO orderFindDTO3 = new OrderFindDTO(orderDate3, deliveryDate3, 2, "C", dealer3, Long.parseLong("3"), Long.parseLong("1"));

        listaADevolver.add(orderFindDTO2);
        listaADevolver.add(orderFindDTO1);
        listaADevolver.add(orderFindDTO3);

        return listaADevolver;
    }

    public static List<OrderFindDTO> getAllOrdersWithSameDeliveryStatus() {
        List<OrderFindDTO> listaADevolver = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate1 = LocalDate.parse("2020-10-04", formatter);
        LocalDate deliveryDate1 = LocalDate.parse("2020-10-02", formatter);


        Dealer dealer1 = new Dealer();
        dealer1.setId_dealer(Long.parseLong("1"));

        OrderFindDTO orderFindDTO1 = new OrderFindDTO(orderDate1, deliveryDate1, 2, "C", dealer1, Long.parseLong("2"), Long.parseLong("1"));

        listaADevolver.add(orderFindDTO1);

        return listaADevolver;
    }


    public static List<OrderDetailsDTO> getListDetails() {
        List<OrderDetailsDTO> listaADevolver = new ArrayList<>();

        OrderDetailsDTO orderDetailsDTO1 = new OrderDetailsDTO(90874389, "Suburban 2500", 1, "R", "Sin Motivo");
        OrderDetailsDTO orderDetailsDTO2 = new OrderDetailsDTO(39729828, "J", 3, "G", "Sin Motivo");
        OrderDetailsDTO orderDetailsDTO3 = new OrderDetailsDTO(89134622, "Grand Prix", 2, "G", "Sin Motivo");

        listaADevolver.add(orderDetailsDTO1);
        listaADevolver.add(orderDetailsDTO2);
        listaADevolver.add(orderDetailsDTO3);

        return listaADevolver;
    }

    public static List<OrderDTO> getResponseListOrdersDTO() {
        List<OrderDTO> listaADevolver = new ArrayList<>();

        List<OrderFindDTO> findDTOList = OrderFixture.getAllOrders();

        OrderDTO orderDTO1 = new OrderDTO("0001-0001-00000001", null, findDTOList.get(0).getOrderDate().toString(), findDTOList.get(0).getDeliveryDate().toString(), String.valueOf(findDTOList.get(0).getDaysDelayed()), findDTOList.get(0).getDeliveryStatus(), null, OrderFixture.getListDetails());
        OrderDTO orderDTO3 = new OrderDTO("0001-0001-00000002", null, findDTOList.get(1).getOrderDate().toString(), findDTOList.get(1).getDeliveryDate().toString(), String.valueOf(findDTOList.get(1).getDaysDelayed()), findDTOList.get(1).getDeliveryStatus(), null, OrderFixture.getListDetails());

        listaADevolver.add(orderDTO1);
        listaADevolver.add(orderDTO3);

        return listaADevolver;
    }

    public static List<OrderDTO> getResponseListOrdersDTOOrderByASC() {
        List<OrderDTO> listaADevolver = new ArrayList<>();

        List<OrderFindDTO> findDTOList = OrderFixture.getAllOrders();

        OrderDTO orderDTO1 = new OrderDTO("0001-0001-00000001", null, findDTOList.get(0).getOrderDate().toString(), findDTOList.get(0).getDeliveryDate().toString(), String.valueOf(findDTOList.get(0).getDaysDelayed()), findDTOList.get(0).getDeliveryStatus(), null, OrderFixture.getListDetails());
        OrderDTO orderDTO2 = new OrderDTO("0001-0001-00000002", null, findDTOList.get(1).getOrderDate().toString(), findDTOList.get(1).getDeliveryDate().toString(), String.valueOf(findDTOList.get(1).getDaysDelayed()), findDTOList.get(1).getDeliveryStatus(), null, OrderFixture.getListDetails());

        listaADevolver.add(orderDTO2);
        listaADevolver.add(orderDTO1);

        return listaADevolver;
    }

    public static List<OrderDTO> getResponseListOrdersDTOOrderByDesc() {
        List<OrderDTO> listaADevolver = new ArrayList<>();

        List<OrderFindDTO> findDTOList = OrderFixture.getAllOrders();

        OrderDTO orderDTO1 = new OrderDTO("0001-0001-00000001", null, findDTOList.get(0).getOrderDate().toString(), findDTOList.get(0).getDeliveryDate().toString(), String.valueOf(findDTOList.get(0).getDaysDelayed()), findDTOList.get(0).getDeliveryStatus(), null, OrderFixture.getListDetails());
        OrderDTO orderDTO2 = new OrderDTO("0001-0001-00000002", null, findDTOList.get(1).getOrderDate().toString(), findDTOList.get(1).getDeliveryDate().toString(), String.valueOf(findDTOList.get(1).getDaysDelayed()), findDTOList.get(1).getDeliveryStatus(), null, OrderFixture.getListDetails());

        listaADevolver.add(orderDTO1);
        listaADevolver.add(orderDTO2);

        return listaADevolver;
    }

    public static List<OrderDTO> getResponseListOrdersDTOWithSpecificDeliveryStatus() {
        List<OrderDTO> listaADevolver = new ArrayList<>();

        List<OrderFindDTO> findDTOList = OrderFixture.getAllOrders();

        OrderDTO orderDTO1 = new OrderDTO("0001-0001-00000002", null, findDTOList.get(1).getOrderDate().toString(), findDTOList.get(1).getDeliveryDate().toString(), String.valueOf(findDTOList.get(1).getDaysDelayed()), findDTOList.get(1).getDeliveryStatus(), null, OrderFixture.getListDetails());

        listaADevolver.add(orderDTO1);

        return listaADevolver;
    }

    public static OrderDTOResponse getOrderDTOResponse() {
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse("0001", OrderFixture.getResponseListOrdersDTO());

        return orderDTOResponse;
    }

    public static OrderDTOResponse getOrderDTOResponseWithSpecificDeliveryStatus() {
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse("0001", OrderFixture.getResponseListOrdersDTOWithSpecificDeliveryStatus());

        return orderDTOResponse;
    }

    public static OrderDTOResponse getOrderDTOResponseOrderByASC() {
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse("0001", OrderFixture.getResponseListOrdersDTOOrderByASC());

        return orderDTOResponse;
    }

    public static OrderDTOResponse getOrderDTOResponseOrderByDesc() {
        OrderDTOResponse orderDTOResponse = new OrderDTOResponse("0001", OrderFixture.getResponseListOrdersDTOOrderByDesc());

        return orderDTOResponse;
    }

    public static OrderDetailNumberDTO getOrderDetailNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-12-15", formatter);

        OrderDetailNumberDTO orderDetailNumberDTO = new OrderDetailNumberDTO(Long.parseLong("1"), Long.parseLong("1"), localDate, "P");

        return orderDetailNumberDTO;
    }

    public static OrderDTO getOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-12-15", formatter);

        return new OrderDTO(null, "0001-00000001", "2020-12-15", null, null, null, "P", OrderFixture.getListDetails());
    }

    public static OrderDTOResponse getPartListResponseDTO() {
        PartsListResponseDTO partsListResponseDTO = new PartsListResponseDTO();
        return new OrderDTOResponse("0001", OrderFixture.getResponseListOrdersDTO());
    }

    public static NewOrderDTOResponse getNewOrderResponse() {
        return new NewOrderDTOResponse(201, " New order created.");
    }
    public static NewOrderDTORequest getNewOrderDTORequest() {
        NewPartOrderDTORequest part = new NewPartOrderDTORequest(2,1,1,null);
        List<NewPartOrderDTORequest> parts = new ArrayList<NewPartOrderDTORequest>();
        parts.add(part);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-03-09", formatter);

        return new NewOrderDTORequest(localDate,"serialNumber",1,1, parts );
    }
    public static NewOrderDTORequest getNewOrderBigQuantityDTORequest() {
        NewPartOrderDTORequest part = new NewPartOrderDTORequest(2,1,150,null);
        List<NewPartOrderDTORequest> parts = new ArrayList<NewPartOrderDTORequest>();
        parts.add(part);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-03-09", formatter);

        return new NewOrderDTORequest(localDate,"serialNumber",1,1, parts );
    }
    public static NewOrderDTORequest getNewInvalidOrderDTORequest() {
        List<NewPartOrderDTORequest> parts = new ArrayList<NewPartOrderDTORequest>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2020-03-09", formatter);

        return new NewOrderDTORequest(localDate,"serialNumber",1,1, parts );
    }

    public static NewOrderDTOResponse getStatusDTOResponse() {
        return new NewOrderDTOResponse(201, " Modified status. ");
    }
    public static UpdateOrdenStatusDTORequest getStatusDTORequest() {
        return new UpdateOrdenStatusDTORequest((long)2, "C");
    }
    public static UpdateOrdenStatusDTORequest getInvalidStatusDTORequest() {
        return new UpdateOrdenStatusDTORequest((long)2, "ERROR");
    }

    public static UpdateOrdenStatusDTORequest getUpdateOrderStatusDto(){
        UpdateOrdenStatusDTORequest orderStatusDto = new UpdateOrdenStatusDTORequest(1L, "P");
        return orderStatusDto;
    }

    public static OrderStatusDTO getOrderStatusDto(){
        OrderStatusDTO orderStatusDTO = new OrderStatusDTO(1L, "221321", "Descripcion");
        return orderStatusDTO;
    }

    public static NewOrderDTOResponse getNewOrderDTOResponseUpdated(){
        return new NewOrderDTOResponse(201, " Modified status. ");
    }

    public static UpdateOrdenStatusDTORequest getUpdateOrderStatusDtoF(){
        UpdateOrdenStatusDTORequest orderStatusDto = new UpdateOrdenStatusDTORequest(1L, "F");
        return orderStatusDto;
    }
}
