package com.mercadolibre.varela_agustina.unit.fixtures;

import com.mercadolibre.varela_agustina.dtos.PartPriceDTO;

public class PartPriceFixture {
    public static PartPriceDTO getPartPriceDto(){
        PartPriceDTO partPriceDTO = new PartPriceDTO(0L, 12.0, 15.0, 18.0);
        return partPriceDTO;
    }
}
