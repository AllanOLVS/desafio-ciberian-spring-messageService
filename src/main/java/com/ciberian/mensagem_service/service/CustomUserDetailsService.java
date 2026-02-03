package com.ciberian.mensagem_service.service;

import com.ciberian.mensagem_service.dto.UserInsertDTO;
import com.ciberian.mensagem_service.dto.UserResponseDTO;
import com.ciberian.mensagem_service.entities.User;
import com.ciberian.mensagem_service.entities.enums.Role;
import com.ciberian.mensagem_service.repository.UserRepository;
import com.ciberian.mensagem_service.service.exceptions.ResourceNotFoundException;
import com.ciberian.mensagem_service.service.exceptions.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
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
        if (userRepository.existsByUsername(userInsertDTO.getUsername())) {
            throw new IllegalArgumentException("Username já existe");
        }
        User newUser = new User();
        copyDtoToEntity(newUser, userInsertDTO);
        newUser.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
        newUser = userRepository.save(newUser);
        return new UserResponseDTO(newUser);
    }

    public static void copyDtoToEntity(User user, UserInsertDTO dto){
        user.setUsername(dto.getUsername());
        user.setRole(Role.ROLE_USER);
    }

}
