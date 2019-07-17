package com.massita.query.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
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
