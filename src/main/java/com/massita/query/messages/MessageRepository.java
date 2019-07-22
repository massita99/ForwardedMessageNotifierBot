package com.massita.query.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findAllByNotifyTimeBetween(LocalDateTime start, LocalDateTime end);

}
