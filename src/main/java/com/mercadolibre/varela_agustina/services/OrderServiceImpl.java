package com.mercadolibre.varela_agustina.services;

import com.mercadolibre.varela_agustina.dtos.*;
import com.mercadolibre.varela_agustina.dtos.requests.NewOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.NewPartOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.requests.UpdateOrdenStatusDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.NewOrderDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.OrderDetailsDTO;
import com.mercadolibre.varela_agustina.dtos.responses.StockDTO;
import com.mercadolibre.varela_agustina.model.Dealer;
import com.mercadolibre.varela_agustina.repository.*;
import com.mercadolibre.varela_agustina.util.Util;
import com.mercadolibre.varela_agustina.util.ValidateUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderCMRepository orderCMRepository;
    private final OrderDetailCMRepository orderDetailCMRepository;
    private final StockCMRepository stockCMRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PartRepository partRepository;
    private final StockSubsidiaryRepository stockSubsidiaryRepository;

    private String[] availableParams = {"dealerNumber", "deliveryStatus", "order"};

    public OrderServiceImpl(OrderCMRepository orderCMRepository, OrderDetailCMRepository orderDetailCMRepository, StockCMRepository stockCMRepository, OrderStatusRepository orderStatusRepository, PartRepository partRepository, StockSubsidiaryRepository stockSubsidiaryRepository) {
        this.orderCMRepository = orderCMRepository;
        this.orderDetailCMRepository = orderDetailCMRepository;
        this.stockCMRepository = stockCMRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.partRepository = partRepository;
        this.stockSubsidiaryRepository = stockSubsidiaryRepository;
    }

    @Override
    public OrderDTOResponse getOrderList(Map<String, String> params) throws Exception {
        List<OrderFindDTO> listaADevolver = new ArrayList<>();
        List<OrderDetailsDTO> listDetails = new ArrayList<>();
        List<OrderDTO> orders = new ArrayList<>();

        ValidateUtil.validateAndGetFilterParams(params, availableParams);
        ValidateUtil.validateDealerNumberParam(params);


        Dealer dealer = new Dealer();
        dealer.setId_dealer(Long.parseLong((params.get("dealerNumber"))));


        if (params.containsKey("deliveryStatus")) {
            // Validate delivery Status
            ValidateUtil.validateDeliveryStatus(params.get("deliveryStatus"));
            listaADevolver = orderCMRepository.findAllOrders(params.get("deliveryStatus"));


        } else {
            listaADevolver = orderCMRepository.findAllOrdersDealer();

        }

        listaADevolver.removeIf(orderFindDTO -> !(orderFindDTO.getIdDealer().equals(dealer.getId_dealer())));


        for (OrderFindDTO order : listaADevolver) {
            listDetails = orderCMRepository.findPartOrderDetail(order.getIdOrderCM());


            String orderNumber = Util.lpad(String.valueOf(order.getIdMatrixHouse()), 4, '0') + "-" + Util.lpad(String.valueOf(order.getIdDealer()), 4, '0') + "-" + Util.lpad(String.valueOf(order.getIdOrderCM()), 8, '0');
            OrderDTO objOrder = new OrderDTO(orderNumber, null, String.valueOf(order.getOrderDate()), String.valueOf(order.getDeliveryDate()), String.valueOf(order.getDaysDelayed()), order.getDeliveryStatus(), null, null);

            List<OrderDetailsDTO> ordersDetails = new ArrayList<>(listDetails);

            objOrder.setOrderDetails(ordersDetails);

            orders.add(objOrder);
        }


        ValidateUtil.validateOrderList(orders);

        if (params.containsKey("order")) {
            switch (params.get("order")) {
                case "1":
                    orders = orders.stream().sorted(Comparator.comparing(OrderDTO::getOrderDate)).collect(Collectors.toList());
                    break;
                case "2":
                    orders = orders.stream().sorted(Comparator.comparing(OrderDTO::getOrderDate).reversed()).collect(Collectors.toList());
                    break;
            }
        }
        String dealerNumber = Util.lpad(params.get("dealerNumber"), 4, '0');
        return new OrderDTOResponse(dealerNumber, orders);
    }

    @Override
    public OrderDTO getOrderNumberDetail(String orderNumber) {
        ValidateUtil.validateOrderNumber(orderNumber);

        String[] vector = orderNumber.split("-");
        OrderDTO order = new OrderDTO();

        Long parametro = Long.parseLong(String.valueOf(vector[2]));
        OrderDetailNumberDTO orderDetailNumberDTO = orderCMRepository.findOrderNumberCM(parametro);

        ValidateUtil.validateOrderDetailNumber(orderDetailNumberDTO);

        order.setOrderNumberCE(Util.lpad(String.valueOf(orderDetailNumberDTO.getIdDealer()), 4, '0') + "-" + Util.lpad(String.valueOf(orderDetailNumberDTO.getIdOrderCM()), 8, '0'));
        order.setOrderDate(String.valueOf(orderDetailNumberDTO.getOrderDate()));
        order.setOrderStatus(orderDetailNumberDTO.getOrderStatus());

        List<OrderDetailsDTO> listaOrderDetails = orderDetailCMRepository.getPartOrderNumber(orderDetailNumberDTO.getIdOrderCM());

        order.setOrderDetails(listaOrderDetails);

        return order;
    }

    @Override
    public NewOrderDTOResponse newOrder(NewOrderDTORequest orderDto) {

        // Validate parts list
        ValidateUtil.validatePartsList(orderDto.getParts());

        for (NewPartOrderDTORequest part : orderDto.getParts()) {
            StockDTO stockDTO = stockCMRepository.findByIdPart(part.getIdpart());
            // Validate Stock
            ValidateUtil.validateStock(stockDTO, part.getQuantity());
        }

        Integer idOrder = orderCMRepository.getIdOrderCMInsert();
        orderCMRepository.insertOrderCM((long) idOrder, orderDto.getDeliverydate(), orderDto.getSerialnumber(), orderDto.getIdshipping(), orderDto.getOrdertype());

        for (NewPartOrderDTORequest part : orderDto.getParts()) {
            Integer idDetailOrden = orderDetailCMRepository.getIdOrderDetailCM();
            orderDetailCMRepository.insertOrderDetail(idDetailOrden, idOrder, part.getQuantity(), part.getIdaccounttype(), part.getIdpart(), part.getReason());

            StockDTO stockDTO = stockCMRepository.findByIdPart(part.getIdpart());
            if (orderDto.getOrdertype() == 1) {
                Integer cantidad = Math.toIntExact(stockDTO.getQuantity() + part.getQuantity());
                stockCMRepository.updateQuantityPart(Math.toIntExact(stockDTO.getId_stock_cm()), cantidad);
            } else {
                Integer cantidad = Math.toIntExact(stockDTO.getQuantity() - part.getQuantity());
                stockCMRepository.updateQuantityPart(Math.toIntExact(stockDTO.getId_stock_cm()), cantidad);
            }
        }

        NewOrderDTOResponse newOrder = new NewOrderDTOResponse();
        newOrder.setCode(201);
        newOrder.setMessage(" New order created.");
        return newOrder;

    }

    @Override
    public NewOrderDTOResponse updateOrdenStatus(UpdateOrdenStatusDTORequest orderStatusDto) {
        NewOrderDTOResponse newOrder = new NewOrderDTOResponse();
        String message = "";
        // Validate Order status
        ValidateUtil.validateOrderStatus(orderStatusDto.getCodestatus());
        Integer idOrder = orderCMRepository.getIdOrderCM(orderStatusDto.getIdorden());
        // Validate idOrder
        ValidateUtil.validateOrderId(idOrder);
        OrderStatusDTO orderStatus = orderStatusRepository.findByIdCode(orderStatusDto.getCodestatus());
        OrderDetailNumberDTO order = orderCMRepository.findOrderNumberCM((long) orderStatusDto.getIdorden());
        orderCMRepository.updateStatus(orderStatusDto.getIdorden(), orderStatus.getId_order_type());
        List<OrderDetailsDTO> listaOrderDetails = orderDetailCMRepository.getPartOrderNumber(orderStatusDto.getIdorden());
        if (orderStatusDto.getCodestatus().equals("F")) {
            for (OrderDetailsDTO orderDetail : listaOrderDetails) {
                Integer cantidad = orderDetail.getQuantity();
                PartDTO part = partRepository.findPartByIDOrCode("", Objects.toString(orderDetail.getPartCode()));
                Integer id = stockSubsidiaryRepository.getId(order.getIdDealer(),(int) part.getIdpart());
                StockDTO stockDTO = stockCMRepository.findByIdPart( part.getIdpart());
                if (id != null) {
                    stockSubsidiaryRepository.updateStock(id, cantidad);
                } else {
                    stockSubsidiaryRepository.insertStock(cantidad, order.getIdDealer(), (int) part.getIdpart());
                }
                stockCMRepository.updateQuantityPart(Math.toIntExact(stockDTO.getId_stock_cm()), cantidad);
            }
        }
        newOrder.setCode(201);
        newOrder.setMessage(" Modified status. ");
        return newOrder;
    }
}

