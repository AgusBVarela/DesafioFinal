package com.mercadolibre.varela_agustina.unit.services;

import com.mercadolibre.varela_agustina.dtos.PartDTO;
import com.mercadolibre.varela_agustina.dtos.responses.NewPartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartsListResponseDTO;
import com.mercadolibre.varela_agustina.exceptions.ApiException;
import com.mercadolibre.varela_agustina.exceptions.FechaExceptions;
import com.mercadolibre.varela_agustina.model.Part;
import com.mercadolibre.varela_agustina.repository.PartPriceRepository;
import com.mercadolibre.varela_agustina.repository.PartRecordRepository;
import com.mercadolibre.varela_agustina.repository.PartRepository;
import com.mercadolibre.varela_agustina.repository.StockCMRepository;
import com.mercadolibre.varela_agustina.services.PartServiceImpl;
import com.mercadolibre.varela_agustina.unit.fixtures.PartFixture;
import com.mercadolibre.varela_agustina.unit.fixtures.PartPriceFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.Mockito.when;

class PartServiceImplTest {
    @Mock
    PartRepository partRepository;

    @Mock
    StockCMRepository stockCMRepository;

    @Mock
    PartRecordRepository partRecordRepository;

    @Mock
    PartPriceRepository partPriceRepository;


    @InjectMocks
    PartServiceImpl partService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Obtiene la lista de las partes")
    void getPartsList() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();

        when(partRepository.findAllParts()).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsList());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenadas por descripcion ASC enviandole un C como parametro")
    void getPartsDescripASCListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");

        when(partRepository.findPartsAndOrderAsc()).thenReturn(PartFixture.getAllPartsListDescripASC());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDescripASC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenados por descripcion DESC enviandole un C como parametro")
    void getPartsDescripDESCListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");
        mapParameters.put("order", "2");

        when(partRepository.findPartsAndOrderDesc()).thenReturn(PartFixture.getAllPartsListDescripDESC());

        List<PartDTOResponse>list = partService.getPartsList(mapParameters).getParts();
        List<PartDTOResponse>list1 = PartFixture.getAllPartsListDescripDESC();

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDescripDESC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenados por fecha ASC enviandole un C como parametro")
    void getPartsDateDESCListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");
        mapParameters.put("order", "3");

        when(partRepository.findPartsAndOrderDate()).thenReturn(PartFixture.getAllPartsListDateASC());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDateASC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenados por fecha ASC a partir de fecha")
    void getPartsDateDESCBetweenDateListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");
        mapParameters.put("order", "3");
        mapParameters.put("date", "2016-08-30");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("2016-08-30" + " 00:00:00", formatter);

        when(partRepository.findBetweenDatePartsAndOrderDate(dateTime1)).thenReturn(PartFixture.getAllPartsListDateASC());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDateASC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenadas por descripcion DESC y a partir de fecha")
    void getPartsDescripDESCBeteweenDateListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");
        mapParameters.put("order", "2");
        mapParameters.put("date", "2016-08-30");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("2016-08-30" + " 00:00:00", formatter);

        when(partRepository.findBetweenDatePartsAndOrderDesc(dateTime1)).thenReturn(PartFixture.getAllPartsListDescripDESC());


        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDescripDESC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes ordenadas por descripcion ASC y a partir de fecha")
    void getPartsDescripASCBeteweenDateListWithC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "C");
        mapParameters.put("date", "2016-08-30");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("2016-08-30" + " 00:00:00", formatter);

        when(partRepository.findBetweenDatePartsAndOrderAsc(dateTime1)).thenReturn(PartFixture.getAllPartsListDescripASC());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsListDescripASC());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes que se han modificado los precios a partir de una fecha")
    void getPartsWithV() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "V");
        mapParameters.put("date", "2020-08-18");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-18", formatter);

        when(partRepository.findFilterVariablePrice(dateTime1)).thenReturn(PartFixture.getAllPartsPriceModifiedBetweenDateList());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsPriceModifiedBetweenDateList());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes que se han modificado los precios a partir de una fecha, con orden descendente de descripcion")
    void getPartsWithVOrderByDescripDESC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "V");
        mapParameters.put("order", "2");
        mapParameters.put("date", "2020-08-18");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-18", formatter);

        when(partRepository.findFilterVariablePriceOrderDesc(dateTime1)).thenReturn(PartFixture.getAllPartsPriceModifiedBetweenDateListOrderByDesc());

        List<PartDTOResponse> list1 = PartFixture.getAllPartsPriceModifiedBetweenDateListOrderByDesc();
        List<PartDTOResponse> list2 = partService.getPartsList(mapParameters).getParts();

        Assertions.assertIterableEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsPriceModifiedBetweenDateListOrderByDesc());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes que se han modificado los precios a partir de una fecha, con orden ascendente de fecha")
    void getPartsWithVOrderByDateDESC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "V");
        mapParameters.put("order", "3");
        mapParameters.put("date", "2020-08-18");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("2020-08-18" + " 00:00:00", formatter);

        when(partRepository.findFilterVariablePriceOrderByDate(dateTime1)).thenReturn(PartFixture.getAllPartsPriceModifiedBetweenDateListOrderByDateAsc());

        Assertions.assertIterableEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsPriceModifiedBetweenDateListOrderByDateAsc());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes modificadas P y una fecha como parametro")
    void getPartsListWithP() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "P");
        mapParameters.put("date", "2016-01-01");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("2016-01-01" + " 00:00:00", formatter);

        when(partRepository.findFilterDatePartsAndOrderAsc(dateTime1)).thenReturn(PartFixture.getAllPartsModifiedBetweenDateList());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsModifiedBetweenDateList());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes modificadas P, una fecha como parametro en order DESC")
    void getPartsListWithPOrderByDESC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "P");
        mapParameters.put("order", "2");
        mapParameters.put("date", "2016-01-01");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2016-01-01", formatter);

        when(partRepository.findFilterDatePartsAndOrderDesc(dateTime1)).thenReturn(PartFixture.getAllPartsModifiedBetweenDateListOrderByDesc());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsModifiedBetweenDateListOrderByDesc());
    }

    @Test
    @DisplayName("Obtiene la lista de las partes moodificadas P, una fecha como parametro en orden ASC por fecha")
    void getPartsListWithPOrderByDateASC() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "P");
        mapParameters.put("order", "3");
        mapParameters.put("date", "2016-01-01");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2016-01-01", formatter);

        when(partRepository.findFilterDatePartsAndOrderDate(dateTime1)).thenReturn(PartFixture.getAllPartsModifiedBetweenDateListOrderByDateASC());

        Assertions.assertEquals(partService.getPartsList(mapParameters).getParts(), PartFixture.getAllPartsModifiedBetweenDateListOrderByDateASC());
    }

    @Test
    @DisplayName("Devuelve una excepcion por fecha incorrecta")
    void throwsExceptionsFromIncorrectDate() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "14-05-2021");
        mapParameters.put("queryType", "C");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDate dateTime1 = LocalDate.parse("14-05-2021" + " 00:00:00", formatter);

        when(partRepository.findBetweenDatePartsAndOrderAsc(dateTime1)).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertThrows(FechaExceptions.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por falta de date")
    void throwsExceptionsFromIncorrectDateRequest() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
;       mapParameters.put("queryType", "P");

        when(partRepository.findAllParts()).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por queryType incorrecta")
    void throwsExceptionsFromIncorrectqueryTypeRequest() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "R");
        mapParameters.put("date", "2021-05-15");

        when(partRepository.findAllParts()).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por order incorrecta")
    void throwsExceptionsFromIncorrectOrderRequest() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("queryType", "P");
        mapParameters.put("order", "10");
        mapParameters.put("date", "2021-05-15");

        when(partRepository.findAllParts()).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por nombre parametro incorrecto")
    void throwsExceptionsFromKeyIncorrectOnRequest() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("error", "P");

        when(partRepository.findAllParts()).thenReturn(PartFixture.getAllPartsList());

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por falta de datos en response")
    void throwsExceptionsOnEmptyListResponse() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();

        List<PartDTOResponse>list = new ArrayList<>();
        when(partRepository.findAllParts()).thenReturn(list);

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsList(mapParameters), "ERROR");
    }


    @Test
    @DisplayName("Devuelve una parte actualizada en DB")
    void updatePart() throws Exception {

        when(partRepository.findPartByIDOrCode(Long.toString(PartFixture.getNewPartDTO().getIdpart()), Integer.toString(PartFixture.getNewPartDTO().getPartcode()))).thenReturn(PartFixture.getPartDTO());
        when(partRepository.findPartIdPartPrice(Objects.toString(10, null))).thenReturn(10);
        when(stockCMRepository.findByIdPart(PartFixture.getNewPartDTO().getIdpart())).thenReturn(PartFixture.getStockDTO());
        when(partPriceRepository.findByIdPart(0L)).thenReturn(PartPriceFixture.getPartPriceDto());

        NewPartDTOResponse part1 =partService.newPart(PartFixture.getNewPartDTO());
        NewPartDTOResponse part2 =PartFixture.getUpdatedPart();
        Assertions.assertEquals(partService.newPart(PartFixture.getNewPartDTO()),PartFixture.getUpdatedPart());
    }

    @Test
    @DisplayName("Devuelve una excepcion por searchType incorrecta")
    void throwsExceptionsFromIncorrectSearchTypeRequest() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("searchType", "5");
        mapParameters.put("date", "2021-05-15");

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsWithOrder(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por fecha incorrecta")
    void throwsExceptionsFromRequestIncorrectDate() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "10-10-2020");
        mapParameters.put("searchType", "2");

        Assertions.assertThrows(FechaExceptions.class, () -> partService.getPartsWithOrder(mapParameters), "ERROR");
    }


    @Test
    @DisplayName("Devuelve una excepcion por fecha inexistente")
    void throwsExceptionsFromRequestWithoutDate() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("searchType", "5");

        Assertions.assertThrows(ApiException.class, () -> partService.getPartsWithOrder(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Devuelve una excepcion por fecha incorrecta")
    void throwsExceptionsFromIncorrectDateRequestInGetOffer() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "10-10-2020");

        Assertions.assertThrows(FechaExceptions.class, () -> partService.getPartsToOffer(mapParameters), "ERROR");
    }

    @Test
    @DisplayName("Test obtencion de partes con ordenes posteriores a la fecha recibida por parámetro.")
    void getPartsWithOrderAfterDate() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "2020-10-10");
        mapParameters.put("searchType", "2");

        when(partRepository.getPartsWithOrderAfter(any())).thenReturn(PartFixture.getPartsWithOrder());

        Assertions.assertEquals(partService.getPartsWithOrder(mapParameters), PartFixture.getPartsWithOrderListResponse());
    }

    @Test
    @DisplayName("Test obtencion de partes con ordenes previas a la fecha recibida por parámetro.")
    void getPartsWithOrderBeforeDate() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "2020-10-10");
        mapParameters.put("searchType", "1");

        when(partRepository.getPartsWithOrderBefore(any())).thenReturn(PartFixture.getPartsWithOrder());

        Assertions.assertEquals(partService.getPartsWithOrder(mapParameters), PartFixture.getPartsWithOrderListResponse());
    }

    @Test
    @DisplayName("Obtiene las ofertas recomendadas: aquellas partes que no fueron solicitadas a partir de la fecha.")
    void getOffer() throws Exception {
        Map<String, String> mapParameters = new HashMap<>();
        mapParameters.put("date", "2020-10-10");

        when(partRepository.getParts()).thenReturn(PartFixture.getPartsWithOrder());
        when(partRepository.getPartsWithOrderAfter(any())).thenReturn(PartFixture.getResponseWithoutParts());

        PartsListResponseDTO result = new PartsListResponseDTO();
        result.setParts(PartFixture.getPartsWithOrder());
        Assertions.assertEquals(partService.getPartsToOffer(mapParameters), result);
    }


}

