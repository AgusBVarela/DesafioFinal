package com.mercadolibre.varela_agustina.exceptions;

import lombok.Data;

@Data
public class FechaExceptions extends Exception{
    public static final String FECHA_FORMATO_NO_CORRECTO = "FECHA_FORMATO_NO_CORRECTO";
    public static final String FECHA_FORMATO_NO_CORRECTO_MSG = "Formato de fecha debe ser aaaa-mm-dd";
    private String code;


    public FechaExceptions(String code, String message)
    {
        super(message);
        this.code = code;
    }

    public FechaExceptions() {
        super("FECHA_FORMATO_NO_CORRECTO");
        this.code = this.FECHA_FORMATO_NO_CORRECTO;
    }
}
