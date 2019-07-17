package com.massita.sevices.commands;

import com.massita.coreapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final CommandGateway commandGateway;

    public MessageCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public Future<String> createMessage(String messageId, String chatId) {
        Future<String> result = commandGateway.send(new CreateMessageCommand(messageId, chatId));
        commandGateway.send(new ToScheduleMessageCommand(messageId));
        return result;
    }

    @Override
    public Future<Void> scheduleMessage(String messageId, NotifyType time) {
        return commandGateway.send(new ScheduleMessageCommand(messageId, time));
    }

    @Override
    public Future<Void> notifyAboutMessage(String messageId) {
        return commandGateway.send(new NotifyAboutMessageCommand(messageId));
    }
}
