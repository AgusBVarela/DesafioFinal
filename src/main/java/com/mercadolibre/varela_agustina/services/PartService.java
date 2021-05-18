package com.mercadolibre.varela_agustina.services;

import com.mercadolibre.varela_agustina.dtos.requests.PartDTORequest;
import com.mercadolibre.varela_agustina.dtos.responses.NewPartDTOResponse;
import com.mercadolibre.varela_agustina.dtos.responses.PartsListResponseDTO;

import java.util.Map;

public interface PartService {
    PartsListResponseDTO getPartsList(Map<String, String> params) throws Exception;

    PartsListResponseDTO getPartsToOffer(Map<String, String> params) throws Exception;

    NewPartDTOResponse newPart(PartDTORequest partDTO) throws Exception;

    PartsListResponseDTO getPartsWithOrder(Map<String, String> params) throws Exception;

}
