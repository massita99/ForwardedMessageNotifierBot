package com.massita.query.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@Getter
public class Message {

    @Id
    private String notifyToMessageId;

    private String chatId;

    @Setter
    LocalDateTime notifyTime;

    public Message(String notifyToMessageId, String chatId) {
        this.chatId = chatId;
        this.notifyToMessageId = notifyToMessageId;
    }




}
