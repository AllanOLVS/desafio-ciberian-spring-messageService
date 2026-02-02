package com.ciberian.mensagem_service.dto;

import com.ciberian.mensagem_service.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInsertDTO {

    private Long id;
    @NotBlank
    @Size(min = 6)
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;

    public UserInsertDTO(){

    }

    public UserInsertDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserInsertDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
