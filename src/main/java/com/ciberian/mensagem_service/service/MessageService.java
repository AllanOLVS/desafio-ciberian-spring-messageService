package com.ciberian.mensagem_service.service;

import com.ciberian.mensagem_service.dto.MessageDTO;
import com.ciberian.mensagem_service.entities.Message;
import com.ciberian.mensagem_service.repository.MessageRepository;
import com.ciberian.mensagem_service.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        List<Message> list = messageRepository.findAll();
        return list.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        Message entity = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensagem não encontrada"));
        return new MessageDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findByPhoneOrigin(String phoneOrigin) {
        List<Message> list = messageRepository.findByPhoneOrigin(phoneOrigin);
        return list.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findByPhoneDestination(String phoneDestination) {
        List<Message> list = messageRepository.findByPhoneDestination(phoneDestination);
        return list.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findByPeriod(LocalDateTime inicio, LocalDateTime fim) {
        List<Message> list = messageRepository.findByDateTimeBetween(inicio, fim);
        return list.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public MessageDTO insert(MessageDTO dto) {
        Message entity = new Message();
        copyDtoToEntity(dto, entity);
        entity = messageRepository.save(entity);
        return new MessageDTO(entity);
    }

    @Transactional
    public MessageDTO update(Long id, MessageDTO dto) {
        Message entity = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensagem não encontrada"));
        LocalDateTime originalDateTime = entity.getDateTime();
        copyDtoToEntity(dto, entity);
        entity.setDateTime(originalDateTime); // Não altera a data original
        entity = messageRepository.save(entity);
        return new MessageDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mensagem não encontrada");
        }
        messageRepository.deleteById(id);
    }

    private void copyDtoToEntity(MessageDTO dto, Message entity) {
        entity.setPhoneOrigin(dto.getPhoneOrigin());
        entity.setPhoneDestination(dto.getPhoneDestination());
        entity.setMessage(dto.getMessage());
    }

}
