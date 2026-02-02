package com.ciberian.mensagem_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Telefone de origem é obrigatório")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone de origem inválido")
    @Column(name = "phone_origin", length = 20, nullable = false)
    private String phoneOrigin;
    @NotBlank(message = "Telefone de destino é obrigatório")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone de destino inválido")
    @Column(name = "phone_destination", length = 20, nullable = false)
    private String phoneDestination;
    @NotBlank(message = "Mensagem é obrigatória")
    @Size(max = 1000, message = "Mensagem deve ter no máximo 1000 caracteres")
    @Column(name = "message", length = 1000, nullable = false)
    private String message;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    public void prePersist() {
        if (this.dateTime == null) {
            this.dateTime = LocalDateTime.now();
        }
    }

    public Message() {
    }

    public Message(Long id, String phoneOrigin, String phoneDestination, String message, LocalDateTime dateTime) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
