package com.mercadolibre.varela_agustina.util;

public class Util {

    // Retorna una cadena de longitud n, compuesta por s
    // y precedida de tantos caracteres c como sea necesario
    // para completar la longitud mencionada
    // Ejemplo lpad("5",3,'0') ==> "005"
    public static String lpad(String s, int n, char c)
    {
        String valor = String.format("%"+n+"s",s).replace(' ' ,c);
        return valor;
    }


    /*
    public static String formatNumber(String s)
    {
        String valor = s.substring(0, 4) +'-' + s.substring(4,8) + '-' + s.substring(8,s.length());
        return valor;
    }
       TODO SE DEJA?
    */
}
