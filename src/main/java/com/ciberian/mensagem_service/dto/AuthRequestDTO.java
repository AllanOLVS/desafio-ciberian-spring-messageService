package com.ciberian.mensagem_service.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO {

    @NotBlank(message = "Username é obrigatório")
    private String username;
    @NotBlank(message = "Password é obrigatório")
    private String password;

    public AuthRequestDTO() {
    }

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
