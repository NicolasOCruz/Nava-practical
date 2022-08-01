package com.example.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @NotEmpty(message = "Email é obrigatório")
    @Email
    private String email;

    public CustomerDTO() {

    }

    public CustomerDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
