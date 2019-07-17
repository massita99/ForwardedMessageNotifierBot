package com.massita.query.messages;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAllByNotifyTimeBetween(LocalDateTime start, LocalDateTime end);

}
