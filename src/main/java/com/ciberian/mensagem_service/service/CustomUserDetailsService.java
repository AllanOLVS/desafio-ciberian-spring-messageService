package com.ciberian.mensagem_service.service;

import com.ciberian.mensagem_service.dto.UserInsertDTO;
import com.ciberian.mensagem_service.dto.UserResponseDTO;
import com.ciberian.mensagem_service.entities.User;
import com.ciberian.mensagem_service.entities.enums.Role;
import com.ciberian.mensagem_service.repository.UserRepository;
import com.ciberian.mensagem_service.service.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
        return new UserResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(x -> new UserResponseDTO(x));
    }


    @Transactional
    public UserResponseDTO insert(UserInsertDTO userInsertDTO){
        User newUser = new User();
        copyDtoToEntity(newUser, userInsertDTO);
        newUser = userRepository.save(newUser);
        return new UserResponseDTO(newUser);
    }

    public static void copyDtoToEntity(User user, UserInsertDTO dto){
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(Role.ROLE_CLIENT);
    }

}
