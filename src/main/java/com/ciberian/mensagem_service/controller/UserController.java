package com.ciberian.mensagem_service.controller;

import com.ciberian.mensagem_service.dto.UserInsertDTO;
import com.ciberian.mensagem_service.dto.UserResponseDTO;
import com.ciberian.mensagem_service.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        UserResponseDTO dto = userDetailsService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAll(Pageable pageable){
        Page<UserResponseDTO> dtos = userDetailsService.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody @Valid UserInsertDTO userInsertDTO) {
        UserResponseDTO dto = userDetailsService.insert(userInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
