package com.ciberian.mensagem_service.dto;

import com.ciberian.mensagem_service.entities.User;
import com.ciberian.mensagem_service.entities.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserResponseDTO {

    private Long id;
    @NotBlank
    @Size(min = 6)
    private String username;
    private Role role;

    public UserResponseDTO(){

    }

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public UserResponseDTO(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
