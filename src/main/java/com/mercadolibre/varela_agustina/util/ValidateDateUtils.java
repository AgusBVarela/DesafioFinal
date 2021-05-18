package com.mercadolibre.varela_agustina.util;

public class ValidateDateUtils {

    public static boolean validarFechasFormato(String fechaFrom){
        String[] partesFechaFrom = fechaFrom.split("-");
        return  ValidateDateUtils.validarFechaFormato(partesFechaFrom);
    }

    private static boolean validarFechaFormato(String[] partesFecha){
        return validarCantidadPartes(partesFecha.length) && validarAnio(partesFecha[0]) && validarMes(partesFecha[1]) && validarDia(partesFecha[2]);
    }

    private static boolean validarCantidadPartes(int longitud){
        return longitud == 3;
    }
    private static boolean validarDia(String dia) {
        boolean condicion;
        if (dia.length() != 2)
            condicion = false;
        else {
            try {
                int dd = Integer.parseInt(dia);
                condicion = (dd > 0 && dd < 31);
            }
            catch(NumberFormatException e) {
                condicion = false;
            }
        }
        return condicion;
    }

    private static boolean validarMes(String mes) {
        boolean condicion;
        if (mes.length() != 2)
            condicion = false;
        else {
            try {
                int mm = Integer.parseInt(mes);
                condicion = (mm > 0 && mm < 13);
            }catch(NumberFormatException e) {
                condicion = false;
            }
        }

        return condicion;
    }

    private static boolean validarAnio(String anio) {
        boolean condicion;
        if (anio.length() != 4)
            condicion = false;
        else {
            try {
                int aaaa = Integer.parseInt(anio);
                condicion = (aaaa != 0);
            } catch(NumberFormatException e) {
                condicion = false;
            }
        }
        return condicion;
    }

}
