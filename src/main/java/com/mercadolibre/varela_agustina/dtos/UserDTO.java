package com.mercadolibre.varela_agustina.dtos;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Validated
public class UserDTO {
    @NotEmpty(message = "'username' no puede ser nulo o puede estar en blanco")
    private String username;

    @NotEmpty(message = "'password' no puede ser nulo o puede estar en blanco")
    private String password;

    @NotEmpty(message = "'role' no puede ser nulo o puede estar en blanco")
    private String role;

    @NotEmpty(message = "'country' no puede ser nulo o puede estar en blanco")
    private String country;

    @NotEmpty(message = "'phone' no puede ser nulo o puede estar en blanco")
    private String phone;

    @NotEmpty(message = "'address' no puede ser nulo o puede estar en blanco")
    private String address;

    @Positive(message = "'id_dealer' no puede ser nulo o negativo")
    private Long id_dealer;

    public Long getId_dealer() {
        return id_dealer;
    }

    public void setId_dealer(Long id_dealer) {
        this.id_dealer = id_dealer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
