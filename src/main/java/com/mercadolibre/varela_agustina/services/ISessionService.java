package com.mercadolibre.varela_agustina.services;


import com.mercadolibre.varela_agustina.dtos.responses.AccountResponseDTO;
import javassist.NotFoundException;

public interface ISessionService {

    /**
     * Realiza la validación del usuario y contraseña ingresado.
     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
     *
     * @param username
     * @param password
     * @return
     * @throws NotFoundException
     */
    AccountResponseDTO login(String username, String password) throws NotFoundException;
}
