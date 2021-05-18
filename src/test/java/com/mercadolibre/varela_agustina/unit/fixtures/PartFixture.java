package com.mercadolibre.varela_agustina.unit.fixtures;

import com.mercadolibre.varela_agustina.dtos.PartDTO;
import com.mercadolibre.varela_agustina.dtos.PartPriceDTO;
import com.mercadolibre.varela_agustina.dtos.requests.PartDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.NewPartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartsListResponseDTO;
import com.mercadolibre.varela_agustina.dtos.responses.StockDTO;
import com.mercadolibre.varela_agustina.model.Provider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PartFixture {
    public static List<PartDTOResponse> getAllPartsList(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2020-08-13", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 33068.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49062.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Jimmy", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse1);
        list.add(partDTOResponse2);
        list.add(partDTOResponse3);

        return list;
    }

    public static PartsListResponseDTO getPartListResponseDto(){
        PartsListResponseDTO partsListResponseDTO = new PartsListResponseDTO();
        partsListResponseDTO.setParts(PartFixture.getAllPartsList());
        return partsListResponseDTO;
    }

    public static List<PartDTOResponse> getAllPartsListDescripASC(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dateTime1 = LocalDate.parse("2020-8-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-2-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2020-8-13", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 33068.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49062.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Jimmy", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse3);
        list.add(partDTOResponse1);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsListDescripDESC(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-8-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-2-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2020-8-13", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 33068.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49062.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Jimmy", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse3);
        list.add(partDTOResponse1);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsListDateASC(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-8-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-2-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2020-8-13", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 33068.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49062.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Jimmy", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse3);
        list.add(partDTOResponse1);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsPriceModifiedBetweenDateList(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse2);
        list.add(partDTOResponse1);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsPriceModifiedBetweenDateListOrderByDesc(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse1);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsPriceModifiedBetweenDateListOrderByDateAsc(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Grand Prix", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse2);
        list.add(partDTOResponse1);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsModifiedBetweenDateList(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2021-01-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Jimmy", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Grand Prix", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse2);
        list.add(partDTOResponse3);
        list.add(partDTOResponse1);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsModifiedBetweenDateListOrderByDesc(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2021-01-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Jimmy", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Grand Prix", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse1);
        list.add(partDTOResponse3);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getAllPartsModifiedBetweenDateListOrderByDateASC(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);
        LocalDate dateTime2 = LocalDate.parse("2021-02-30", formatter);
        LocalDate dateTime3 = LocalDate.parse("2021-01-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Jimmy", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime2);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Grand Prix", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime3);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse1);
        list.add(partDTOResponse3);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getPartsWithOrder(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse("2020-08-30", formatter);

        PartDTOResponse partDTOResponse1 = new PartDTOResponse(Long.parseLong("1"), "Jimmy", "Nissan", Long.parseLong("68"), "switch", 400112.5, 798.16, 57, 74, 64, 64, dateTime1);
        PartDTOResponse partDTOResponse2 = new PartDTOResponse(Long.parseLong("2"), "Anibal", "Jaguar", Long.parseLong("87"), "switch", 49222.21, 94380.83, 29, 17, 74, 14, dateTime1);
        PartDTOResponse partDTOResponse3 = new PartDTOResponse(Long.parseLong("3"), "Grand Prix", "Jaguar", Long.parseLong("87"), "maestro", 22341.76, 33067.64, 29, 17, 74, 14, dateTime1);

        List<PartDTOResponse> list = new ArrayList<>();
        list.add(partDTOResponse1);
        list.add(partDTOResponse3);
        list.add(partDTOResponse2);

        return list;
    }

    public static List<PartDTOResponse> getResponseWithoutParts(){
       List<PartDTOResponse> list = new ArrayList<>();
        return list;
    }
    public static PartsListResponseDTO getPartsWithOrderListResponse(){
        PartsListResponseDTO partsListResponseDTO = new PartsListResponseDTO();
        partsListResponseDTO.setParts(PartFixture.getPartsWithOrder());
        return partsListResponseDTO;
    }



    public static PartDTO getPartDTO(){
        Provider provider = new Provider();
        provider.setId_provider(1L);
        return new PartDTO( "Descripcion", 40.0, 40.0, 41412229,90.0, 40.0, provider, 200);
    }
    public static PartDTORequest getNewPartDTO(){
        Provider provider = new Provider();
        provider.setId_provider(1L);
        PartPriceDTO partPriceDTO = new PartPriceDTO((long)1,10.0,10.5,7.0);
        PartDTORequest partDTORequest = new PartDTORequest(21312222, 40.0, 40.0,40.0, 41412229,(long) 1,"Pedrito",  100,partPriceDTO);
        partDTORequest.setIdpart(0);
        return partDTORequest;
    }

    public static PartDTORequest getUpdatedPartDTO(){
        Provider provider = new Provider();
        provider.setId_provider(1L);
        //return new PartDTO(10, "Descripcion", 40.0, 40.0, 41412229,90.0, 40.0, provider, 200);
        PartPriceDTO partPriceDTO = new PartPriceDTO((long)1,10.0,10.5,7.0);
        return new PartDTORequest( 21312222, 40.0, 40.0,40.0, 41412229,(long) 1,"Pedrito",  200,partPriceDTO);
    }

    public static NewPartDTOResponse getNewPart(){
        Integer code = 201;
        String message = "Created";
        return new NewPartDTOResponse(code, message, PartFixture.getNewPartDTO());
    }

    public static NewPartDTOResponse getUpdatedPart(){
        Integer code = 202;
        String message = "Modify";
        return new NewPartDTOResponse(code, message, PartFixture.getUpdatedPartDTO());
    }

    public static StockDTO getStockDTO(){
        return new StockDTO(4L, 100L);
    }

}
