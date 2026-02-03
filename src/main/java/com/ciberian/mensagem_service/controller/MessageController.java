package com.ciberian.mensagem_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ciberian.mensagem_service.dto.MessageDTO;
import com.ciberian.mensagem_service.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
@Tag(name = "Mensagens", description = "Endpoints de gerenciamento de mensagens")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @Operation(summary = "Listar todas")
    public ResponseEntity<List<MessageDTO>> findAll() {
        List<MessageDTO> list = messageService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ResponseEntity<MessageDTO> findById(@PathVariable Long id) {
        MessageDTO dto = messageService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/origem/{telefone}")
    @Operation(summary = "Buscar por origem", description = "Retorna mensagens pelo telefone de origem")
    public ResponseEntity<List<MessageDTO>> findByPhoneOrigin(@PathVariable String telefone) {
        List<MessageDTO> list = messageService.findByPhoneOrigin(telefone);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/destino/{telefone}")
    @Operation(summary = "Buscar por destino", description = "Retorna mensagens pelo telefone de destino")
    public ResponseEntity<List<MessageDTO>> findByPhoneDestination(@PathVariable String telefone) {
        List<MessageDTO> list = messageService.findByPhoneDestination(telefone);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar por período", description = "Retorna mensagens em um intervalo de datas")
    public ResponseEntity<List<MessageDTO>> findByPeriod(
            @Parameter(description = "Data/hora de início", schema = @Schema(type = "string", format = "date-time", example = "2024-01-01T00:00:00")) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @Parameter(description = "Data/hora de fim", schema = @Schema(type = "string", format = "date-time", example = "2024-12-31T23:59:59")) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<MessageDTO> list = messageService.findByPeriod(inicio, fim);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @Operation(summary = "Criar mensagem", description = "Cria uma nova mensagem")
    public ResponseEntity<MessageDTO> insert(@RequestBody @Valid MessageDTO dto) {
        dto = messageService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar mensagem", description = "Atualiza uma mensagem existente")
    public ResponseEntity<MessageDTO> update(@PathVariable Long id, @RequestBody @Valid MessageDTO dto) {
        dto = messageService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar mensagem")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
