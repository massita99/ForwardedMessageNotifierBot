package com.massita.query.messages;


import com.massita.coreapi.DatePeriodMessagesQuery;
import com.massita.coreapi.MessageCreatedEvent;
import com.massita.coreapi.MessageScheduledEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageProjection {

    private final MessageRepository messageRepository;

    public MessageProjection(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @EventHandler
    public void on(MessageCreatedEvent event) {
        messageRepository.save(new Message(event.getMessageId(), event.getChatId()));
    }

    @EventHandler
    public void on(MessageScheduledEvent event) {
        Message message = messageRepository.findById(event.getMessageId()).get();
        message.setNotifyTime(event.getTime().getNotifyTime());
        messageRepository.save(message);

    }

    @QueryHandler
    public List<Message> on(DatePeriodMessagesQuery query) {
        return messageRepository.findAllByNotifyTimeBetween(query.getStart(), query.getFinish());
    }
}
