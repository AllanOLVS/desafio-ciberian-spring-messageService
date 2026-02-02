package com.ciberian.mensagem_service.dto;

import com.ciberian.mensagem_service.entities.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    @NotBlank(message = "Telefone de origem é obrigatório")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone de origem inválido")
    private String phoneOrigin;
    @NotBlank(message = "Telefone de destino é obrigatório")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone de destino inválido")
    private String phoneDestination;
    @NotBlank(message = "Mensagem é obrigatória")
    @Size(max = 1000, message = "Mensagem deve ter no máximo 1000 caracteres")
    private String message;
    private LocalDateTime dateTime;

    public MessageDTO() {
    }

    public MessageDTO(Message entity) {
        this.id = entity.getId();
        this.phoneOrigin = entity.getPhoneOrigin();
        this.phoneDestination = entity.getPhoneDestination();
        this.message = entity.getMessage();
        this.dateTime = entity.getDateTime();
    }

    public MessageDTO(Long id, String phoneOrigin, String phoneDestination, String message, LocalDateTime dateTime) {
        this.id = id;
        this.phoneOrigin = phoneOrigin;
        this.phoneDestination = phoneDestination;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneOrigin() {
        return phoneOrigin;
    }

    public void setPhoneOrigin(String phoneOrigin) {
        this.phoneOrigin = phoneOrigin;
    }

    public String getPhoneDestination() {
        return phoneDestination;
    }

    public void setPhoneDestination(String phoneDestination) {
        this.phoneDestination = phoneDestination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
