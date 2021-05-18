package com.mercadolibre.varela_agustina.util;

import com.mercadolibre.varela_agustina.dtos.OrderDTO;
import com.mercadolibre.varela_agustina.dtos.OrderDetailNumberDTO;
import com.mercadolibre.varela_agustina.dtos.requests.NewPartOrderDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.StockDTO;
import com.mercadolibre.varela_agustina.exceptions.ApiException;
import com.mercadolibre.varela_agustina.exceptions.ApiExceptionMessages;
import com.mercadolibre.varela_agustina.exceptions.FechaExceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidateUtil {

    public static void validatePartResult(List<PartDTOResponse> partsList) {
        if (partsList.isEmpty())
            throw new ApiException(ApiExceptionMessages.PART_NOT_FOUND, ApiExceptionMessages.PART_NOT_FOUND_MSG, 404);
    }

    public static void validateFilterParams(Map<String, String> params) {

        // queryType and date must exists
        if(params.containsKey("queryType")) {
            if(!params.get("queryType").equals("C") && !params.containsKey("date")){
                throw new ApiException(ApiExceptionMessages.MISSING_QUERY_TYPE_OR_DATE, ApiExceptionMessages.MISSING_QUERY_TYPE_OR_DATE_MSG, 422);
            }
        }

        // queryType must be C, P or V
        validatequeryType(params.get("queryType"));
        if (params.containsKey("order"))
            // order must be integer and 1, 2 or 3
            validateOrderType(Integer.parseInt(params.get("order")));
    }

    public static void validatequeryType(String queryType) {
        if (!queryType.equals("C") && !queryType.equals("P") && !queryType.equals("V"))
            throw new ApiException(ApiExceptionMessages.INVALID_QUERY_TYPE, ApiExceptionMessages.INVALID_QUERY_TYPE_MSG, 422);
    }

    public static void validateOrderType(int orderType) {
        if (orderType != 1 && orderType != 2 && orderType != 3)
            throw new ApiException(ApiExceptionMessages.INVALID_INPUT, ApiExceptionMessages.INVALID_INPUT_MSG, 422);
    }

    public static void validateAndGetFilterParams(Map<String, String> params, String[] availableParams) {
        // If Params is null, means that there are not params on the request
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!Arrays.stream(availableParams).anyMatch(val -> val.equals(entry.getKey())))
                    throw new ApiException(ApiExceptionMessages.INVALID_INPUT, ApiExceptionMessages.INVALID_INPUT_MSG, 422);
            }
        }
    }

    public static void validateDealerNumberParam(Map<String,String> filterParams){
        // dealerNumber must exists
        if(!filterParams.containsKey("dealerNumber"))
            throw new ApiException(ApiExceptionMessages.MISSING_DEALER_NUMBER, ApiExceptionMessages.MISSING_DEALER_NUMBER_MSG, 422);
        // dealerNumber must be 4 numeric char
        String regex = "[0-9]{1,4}";
        if(!filterParams.get("dealerNumber").matches(regex))
            throw new ApiException(ApiExceptionMessages.INVALID_DEALER_NUMBER, ApiExceptionMessages.INVALID_DEALER_NUMBER_MSG, 422);
    }

    public static void validateOrderList(List<OrderDTO> list) {
        if (list.isEmpty())
            throw new ApiException(ApiExceptionMessages.ORDERS_NOT_FOUND, ApiExceptionMessages.ORDERS_NOT_FOUND_MSG, 404);
    }

    public static void validateDeliveryStatus(String deliveryStatus) {
        if (!deliveryStatus.equals("P") && !deliveryStatus.equals("D") && !deliveryStatus.equals("F") && !deliveryStatus.equals("C"))
            throw new ApiException(ApiExceptionMessages.INVALID_DELIVERY_STATUS, ApiExceptionMessages.INVALID_DELIVERY_STATUS_MSG, 422);
    }

    public static void validateOrderDetailNumber(OrderDetailNumberDTO orderDetailNumber) {
        if (orderDetailNumber == null || orderDetailNumber.getIdDealer() == null
                || orderDetailNumber.getIdOrderCM() == null
                || orderDetailNumber.getOrderDate() == null
                || orderDetailNumber.getOrderStatus() == null) {
            throw new ApiException(ApiExceptionMessages.ORDERS_NOT_FOUND, ApiExceptionMessages.ORDERS_NOT_FOUND_MSG, 422);
        }
    }

    public static void validateOrderNumber(String orderNumber) {
        String regex = "[0-9]{4}([-])[0-9]{1,4}([-])[0-9]{8}";
        if(!orderNumber.matches(regex))
            throw new ApiException(ApiExceptionMessages.INVALID_ORDER_NUMBER, ApiExceptionMessages.INVALID_ORDER_NUMBER_MSG, 422);
    }

    public static void validateStock(StockDTO stock, int amount) {
        // Stock should not be null
        if (stock == null)
            throw new ApiException(ApiExceptionMessages.STOCK_NOT_FOUND, ApiExceptionMessages.STOCK_NOT_FOUND_MSG, 404);

        if (stock.getQuantity() < amount)
            throw new ApiException(ApiExceptionMessages.INSUFFICIENT_STOCK, ApiExceptionMessages.INSUFFICIENT_STOCK_MSG, 409);
    }

    public static void validatePartsList(List<NewPartOrderDTORequest> partsList) {
        if (partsList.isEmpty())
            throw new ApiException(ApiExceptionMessages.INVALID_PART_LIST, ApiExceptionMessages.INVALID_PART_LIST_MSG, 422);
    }

    public static void validateOrderStatus(String orderStatus) {
        if (!orderStatus.equals("P") && !orderStatus.equals("D") && !orderStatus.equals("F")  && !orderStatus.equals("C"))
            throw new ApiException(ApiExceptionMessages.INVALID_ORDER_TYPE, ApiExceptionMessages.INVALID_ORDER_TYPE_MSG, 422);
    }

    public static void validateOrderId(Integer idOrder) {
        // Order must exists
        if (idOrder == null)
            throw new ApiException(ApiExceptionMessages.ORDER_NOT_FOUND, ApiExceptionMessages.ORDER_NOT_FOUND_MSG, 404);
    }
    public static void validateParamToOffer(Map<String, String> params) throws FechaExceptions {
        if(!params.containsKey("date")) throw new ApiException(ApiExceptionMessages.DATE_NOT_FOUND, ApiExceptionMessages.DATE_NOT_FOUND_MSG, 422);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (ValidateDateUtils.validarFechasFormato(params.get("date"))) {
            LocalDate dateTime = LocalDate.parse(params.get("date"), formatter);
        } else {
            throw new FechaExceptions();
        }
    }
    public static void validateParamPartsWithOffer(Map<String, String> params) throws FechaExceptions {
        if(!params.containsKey("date")) throw new ApiException(ApiExceptionMessages.DATE_NOT_FOUND, ApiExceptionMessages.DATE_NOT_FOUND_MSG, 422);
        if(!params.containsKey("searchType")) throw new ApiException(ApiExceptionMessages.SEARCHTYPE_NOT_FOUND, ApiExceptionMessages.SEARCHTYPE_NOT_FOUND_MSG, 422);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (ValidateDateUtils.validarFechasFormato(params.get("date"))) {
            LocalDate dateTime = LocalDate.parse(params.get("date"), formatter);
        } else {
            throw new FechaExceptions();
        }

        if(!params.get("searchType").equals("1") && !params.get("searchType").equals("2")){
            throw new ApiException(ApiExceptionMessages.INVALID_SEARCHTYPE, ApiExceptionMessages.INVALID_SEARCHTYPE_MSG, 422);
        }

    }

}
