package com.ciberian.mensagem_service.repository;

import com.ciberian.mensagem_service.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByPhoneOrigin(String phoneOrigin);

    List<Message> findByPhoneDestination(String phoneDestination);

    List<Message> findByDateTimeBetween(LocalDateTime inicio, LocalDateTime fim);

}
