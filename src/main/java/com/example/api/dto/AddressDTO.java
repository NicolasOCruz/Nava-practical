package com.example.api.dto;

import javax.validation.constraints.NotEmpty;

public class AddressDTO {

    private String street;

    @NotEmpty(message = "Número é obrigatório")
    private String number;

    private String complement;

    private String district;

    @NotEmpty(message = "CEP é obrigatório")
    private String cep;

    public AddressDTO() {

    }

    public AddressDTO(String street, String number, String complement, String district, String cep) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
