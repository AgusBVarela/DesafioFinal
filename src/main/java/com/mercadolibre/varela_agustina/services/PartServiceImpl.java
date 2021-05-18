package com.mercadolibre.varela_agustina.services;

import com.mercadolibre.varela_agustina.dtos.PartDTO;
import com.mercadolibre.varela_agustina.dtos.PartPriceDTO;
import com.mercadolibre.varela_agustina.dtos.requests.PartDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.NewPartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartsListResponseDTO;
import com.mercadolibre.varela_agustina.dtos.responses.StockDTO;
import com.mercadolibre.varela_agustina.exceptions.FechaExceptions;
import com.mercadolibre.varela_agustina.repository.PartPriceRepository;
import com.mercadolibre.varela_agustina.repository.PartRecordRepository;
import com.mercadolibre.varela_agustina.repository.PartRepository;
import com.mercadolibre.varela_agustina.repository.StockCMRepository;
import com.mercadolibre.varela_agustina.util.ValidateDateUtils;
import com.mercadolibre.varela_agustina.util.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PartServiceImpl implements PartService {
    public String[] availableParams = {"queryType", "date", "order"};


    public final PartRepository partRepository;
    public final StockCMRepository stockCMRepository;
    public final PartPriceRepository partPriceRepository;
    public final PartRecordRepository partRecordRepository;

    public PartServiceImpl(PartRepository partRepository, StockCMRepository stockCMRepository, PartPriceRepository partPriceRepository, PartRecordRepository partRecordRepository) {
        this.partRepository = partRepository;
        this.stockCMRepository = stockCMRepository;
        this.partPriceRepository = partPriceRepository;
        this.partRecordRepository = partRecordRepository;
    }

    @Override
    public PartsListResponseDTO getPartsList(Map<String, String> params) throws Exception {
        List<PartDTOResponse> listaADevolver = new ArrayList<>();
        PartsListResponseDTO partsListResponseDTO = new PartsListResponseDTO();

        if (params.size() != 0) {

            listaADevolver = busquedaPorFiltros(params);

        } else {
            listaADevolver = partRepository.findAllParts();
        }

        ValidateUtil.validatePartResult(listaADevolver);

        partsListResponseDTO.setParts(listaADevolver);

        return partsListResponseDTO;
    }

    private List<PartDTOResponse> busquedaPorFiltros(Map<String, String> params) throws FechaExceptions {
        List<PartDTOResponse> listaADevolver = new ArrayList<>();

        ValidateUtil.validateAndGetFilterParams(params, availableParams);
        ValidateUtil.validateFilterParams(params);

        LocalDate fecha = devolverFecha(params.get("date"));

        String order = devolverOrder(params);

        String queryType = params.get("queryType").toUpperCase();

        return busqueda(queryType, fecha, order);
    }

    private List<PartDTOResponse> busqueda(String queryType, LocalDate fecha, String order){
        List<PartDTOResponse> listaADevolver;

        if (queryType.equals("C") && fecha == null) {
            listaADevolver = buscarPorC(order);
        } else {
            if (queryType.equals("C")) {
                listaADevolver = buscarPorCYFecha(order, fecha);
            } else {
                if (queryType.equals("V")) {
                    listaADevolver = buscarPorV(order, fecha);
                } else {
                    listaADevolver = buscarPorP(order, fecha);
                }
            }
        }
        return listaADevolver;
    }

    private LocalDate devolverFecha(String fecha) throws FechaExceptions {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = null;

        if (fecha != null) {
            if (ValidateDateUtils.validarFechasFormato(fecha)) {
                dateTime = LocalDate.parse(fecha, formatter);
            } else {
                throw new FechaExceptions();
            }
        }
        
        return dateTime;
    }

    private String devolverOrder(Map<String, String> params){

        String order = "1";
        if (params.containsKey("order")){
            order = params.get("order");
        }
        return order;
    }

    private List<PartDTOResponse> buscarPorC(String order){
        List<PartDTOResponse> listaADevolver = new ArrayList<>();
        switch (order) {
            case "1":
                listaADevolver = partRepository.findPartsAndOrderAsc();
                break;
            case "2":
                listaADevolver = partRepository.findPartsAndOrderDesc();
                break;
            case "3":
                listaADevolver = partRepository.findPartsAndOrderDate();
                break;
        }
        
        return listaADevolver;
    }

    private List<PartDTOResponse> buscarPorCYFecha(String order, LocalDate fecha){
        List<PartDTOResponse> listaADevolver = new ArrayList<>();

        switch (order) {
            case "1":
                listaADevolver = partRepository.findBetweenDatePartsAndOrderAsc(fecha);
                break;
            case "2":
                listaADevolver = partRepository.findBetweenDatePartsAndOrderDesc(fecha);
                break;
            case "3":
                listaADevolver = partRepository.findBetweenDatePartsAndOrderDate(fecha);
                break;
        }

        return listaADevolver;
    }

    private List<PartDTOResponse> buscarPorV(String order, LocalDate fecha){
        List<PartDTOResponse> listaADevolver = new ArrayList<>();

        switch (order) {
            case "1":
                listaADevolver = partRepository.findFilterVariablePrice(fecha);
                break;
            case "2":
                listaADevolver = partRepository.findFilterVariablePriceOrderDesc(fecha);
                break;
            case "3":
                listaADevolver = partRepository.findFilterVariablePriceOrderByDate(fecha);
                break;
        }

        return listaADevolver;
    }

    private List<PartDTOResponse> buscarPorP(String order, LocalDate fecha){
        List<PartDTOResponse> listaADevolver = new ArrayList<>();

        switch (order) {
            case "1":
                listaADevolver = partRepository.findFilterDatePartsAndOrderAsc(fecha);
                break;
            case "2":
                listaADevolver = partRepository.findFilterDatePartsAndOrderDesc(fecha);
                break;
            case "3":
                listaADevolver = partRepository.findFilterDatePartsAndOrderDate(fecha);
                break;
        }

        return listaADevolver;
    }

    @Override
    @Transactional
    public NewPartDTOResponse newPart(PartDTORequest partDTO) throws Exception {
        NewPartDTOResponse newPart = new NewPartDTOResponse();
        PartDTO partFromDB = partRepository.findPartByIDOrCode(Long.toString(partDTO.getIdpart()), Integer.toString(partDTO.getPartcode()));
        boolean modified = false;
        if (partFromDB == null) {
            Integer idPartPrice;
            idPartPrice = partPriceRepository.getIdInsertPartPrice();
            partPriceRepository.insertPartPrice((long) idPartPrice, partDTO.getPartprice().getPrice(), partDTO.getPartprice().getSale_price(), partDTO.getPartprice().getUrgent_price());
            partRepository.insertPart(partDTO.getPartcode(), partDTO.getDescription(), partDTO.getLongdimension(), partDTO.getTalldimension(), partDTO.getNetweight(), partDTO.getWidthdimension(), partDTO.getProvider(), idPartPrice);
            partFromDB = partRepository.findPartByIDOrCode(Long.toString(partDTO.getIdpart()), Integer.toString(partDTO.getPartcode()));
            stockCMRepository.insertStockCM(partDTO.getQuantity(), partFromDB.getIdpart());
            newPart.setCode(201);
            newPart.setMessage("Created");
        } else {
            StockDTO stockDto = stockCMRepository.findByIdPart(partFromDB.getIdpart());
            Integer cantidad = Math.toIntExact(stockDto.getQuantity() + partDTO.getQuantity());
            stockCMRepository.updateQuantityPart(Math.toIntExact(stockDto.getId_stock_cm()), cantidad);
            PartPriceDTO partPrice = partPriceRepository.findByIdPart(partFromDB.getIdpart());
            if (!partPrice.getPrice().equals(partDTO.getPartprice().getPrice()) || !partPrice.getSale_price().equals(partDTO.getPartprice().getSale_price()) || !partPrice.getUrgent_price().equals(partDTO.getPartprice().getUrgent_price())) {
                partPriceRepository.updatePartPrice(partFromDB.getIdpart(), partDTO.getPartprice().getPrice(), partDTO.getPartprice().getSale_price(), partDTO.getPartprice().getUrgent_price());
                modified = true;
            }
            long idPartRecord = partRecordRepository.getIdInsertPartRecord();
            partRecordRepository.insertPartRecord(idPartRecord, partDTO.getDescription(), partDTO.getLongdimension(), partDTO.getNetweight(), partDTO.getPartprice().getPrice(), partDTO.getPartprice().getSale_price()
                    , partDTO.getTalldimension(), modified, partDTO.getPartprice().getUrgent_price(), partDTO.getWidthdimension(), partFromDB.getIdpart());
            partDTO.setQuantity(cantidad);
            newPart.setCode(202);
            newPart.setMessage("Modify");
        }
        partDTO.setIdpart(partFromDB.getIdpart());
        newPart.setPart(partDTO);
        return newPart;
    }

    @Override
    public PartsListResponseDTO getPartsWithOrder(Map<String, String> params) throws Exception {
        /*Encargada de devolver todas las partes que poseen orden posteriores, igual, o previo a la fecha ingresada por parámetro.*/
        ValidateUtil.validateParamPartsWithOffer(params);
        LocalDate date = devolverFecha(params.get("date"));

        List<PartDTOResponse> result = new ArrayList<>();
        switch(params.get("searchType")) {
            case "1":
                result = partRepository.getPartsWithOrderBefore(date);
                break;
            case "2":
                result = partRepository.getPartsWithOrderAfter(date);
                break;
        }

        PartsListResponseDTO partListResponse = new PartsListResponseDTO(result);
        return partListResponse;
    }


    public PartsListResponseDTO getPartsToOffer(Map<String, String> params) throws Exception{
       /*Encargado de devolver todas las partes que no tuvieron oferta a partir de la fecha establecida. */

        ValidateUtil.validateParamToOffer(params);
        LocalDate date = devolverFecha(params.get("date"));

        List<PartDTOResponse> allParts = partRepository.getParts();
        List<PartDTOResponse> beforeParts = partRepository.getPartsWithOrderAfter(date);
        allParts.removeAll(beforeParts);

        PartsListResponseDTO allPartsResponse = new PartsListResponseDTO(allParts);
        return allPartsResponse;
    }
}


