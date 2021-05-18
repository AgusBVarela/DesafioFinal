package com.mercadolibre.varela_agustina.exceptions;

import lombok.Data;

@Data
public class ApiExceptionMessages {

    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String USER_NOT_FOUND_MSG = "No se encontraró el usuario";
    public static final String PART_NOT_FOUND = "PART_NOT_FOUND";
    public static final String PART_NOT_FOUND_MSG = "No se encontraron partes";
    public static final String ORDER_NOT_FOUND = "ORDER_NOT_FOUND";
    public static final String ORDER_NOT_FOUND_MSG = "No se encontró orden";
    public static final String ORDERS_NOT_FOUND = "ORDERS_NOT_FOUND";
    public static final String ORDERS_NOT_FOUND_MSG = "No se encontraron ordenes";
    public static final String STOCK_NOT_FOUND = "STOCK_NOT_FOUND";
    public static final String STOCK_NOT_FOUND_MSG = "No se encontró Stock";
    public static final String DATE_NOT_FOUND = "DATE_NOT_FOUND";
    public static final String DATE_NOT_FOUND_MSG = "No se encontró la fecha a partir de la cual se desea buscar.";
    public static final String SEARCHTYPE_NOT_FOUND = "SEARCHTYPE_NOT_FOUND";
    public static final String SEARCHTYPE_NOT_FOUND_MSG = "No se encontró el tipo de búsqueda (searchType) que se desea realizar. Siendo 1 previo, 2 posterior a la fecha establecida.";

    public static final String NUMBER_FORMAT = "NUMBER_FORMAT";
    public static final String NUMBER_FORMAT_MSG = "Error con el formato de algún número";
    public static final String DATE_FORMAT = "DATE_FORMAT";
    public static final String DATE_FORMAT_MSG = "Error con el formato de alguna fecha";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String INTERNAL_SERVER_ERROR_MSG = "Error en el servidor";

    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String INVALID_INPUT_MSG = "No es posible procesar alguno de los parametros";
    public static final String INVALID_QUERY_TYPE = "INVALID_QUERY_TYPE";
    public static final String INVALID_QUERY_TYPE_MSG = "El 'query type' debe ser de tipo: C, P y V";
    public static final String INVALID_DELIVERY_STATUS = "INVALID_DELIVERY_STATUS";
    public static final String INVALID_DELIVERY_STATUS_MSG = "El 'delivery status' debe ser de tipo: P, D, F o C";
    public static final String INVALID_DEALER_NUMBER = "INVALID_DEALER_NUMBER";
    public static final String INVALID_DEALER_NUMBER_MSG = "'Dealer number' inválido";
    public static final String INVALID_ORDER_NUMBER = "INVALID_ORDER_NUMBER";
    public static final String INVALID_ORDER_NUMBER_MSG = "'Order number' inválido";
    public static final String INVALID_PART_LIST = "INVALID_PART_LIST";
    public static final String INVALID_PART_LIST_MSG = "Lista de partes inválida";
    public static final String INVALID_ORDER_TYPE = "INVALID_ORDER_TYPE";
    public static final String INVALID_ORDER_TYPE_MSG = "'Order type' inválido";
    public static final String INVALID_ACCOUNT_TYPE = "INVALID_ACCOUNT_TYPE";
    public static final String INVALID_ACCOUNT_TYPE_MSG = "'Account type' inválido";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String INVALID_CREDENTIALS_MSG = "Error en las credenciales";
    public static final String INVALID_SEARCHTYPE = "INVALID_SEARCHTYPE";
    public static final String INVALID_SEARCHTYPE_MSG = "El parámetro de searchType debe ser 1 (previo) o 2 (posterior).";

    public static final String MISSING_QUERY_TYPE_OR_DATE = "MISSING_QUERY_TYPE_OR_DATE";
    public static final String MISSING_QUERY_TYPE_OR_DATE_MSG = "Es necesario ingresar un 'queryType' y un 'date' para realizar la consulta si su 'queryType' es distinto a 'C'";
    public static final String MISSING_DEALER_NUMBER = "MISSING_DEALER_NUMBER";
    public static final String MISSING_DEALER_NUMBER_MSG = "Es necesario especificar un 'dealer number";

    public static final String INCONSISTENT_DATA = "INCONSISTENT_DATA";
    public static final String INCONSISTENT_DATA_MSG = "No se pudo realizar el proceso por alguna incosistencia en los datos especificados";

    public static final String INSUFFICIENT_STOCK = "INSUFFICIENT_STOCK";
    public static final String INSUFFICIENT_STOCK_MSG = "No hay suficiente producto en stock";

    public static final String METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED";
    public static final String METHOD_NOT_ALLOWED_MSG = "Error en la petición";

    public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public static final String USER_ALREADY_EXISTS_MSG = "El ususario ya existe";
    public static final String USER_DISABLED = "USER_DISABLED";
    public static final String USER_DISABLED_MSG = "El ususario está inactivo";
    public static final String INVALID_ORDER = "INVALID_ORDER";
    public static final String INVALID_ORDER_MSG = "La orden es invalida";

}
