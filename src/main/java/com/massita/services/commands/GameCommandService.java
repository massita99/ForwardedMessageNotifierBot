package com.massita.services.commands;

import com.massita.coreapi.CreateGameCommand;
import com.massita.coreapi.ProcessEventCommand;
import com.massita.coreapi.ReturnResultEventCommand;
import com.massita.coreapi.StartEventCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class GameCommandService {

    private final CommandGateway commandGateway;

    public GameCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public Future<String> createGame(String chatId) {
        Future<String> result = commandGateway.send(new CreateGameCommand(chatId));
        return result;
    }

    public Future<String> startEvent(String chatId, String description, List<String> answers, String photo) {
        Future<String> result = commandGateway.send(new StartEventCommand(chatId, description, answers, photo));
        return result;
    }


    public Future<String> processEvent(String chatId, String answer) {
        Future<String> result = commandGateway.send(new ProcessEventCommand(chatId, answer));
        return result;
    }

    public Future<String> returnEventResult(String chatId, String description, String currentStats) {
        Future<String> result = commandGateway.send(new ReturnResultEventCommand(chatId, description, currentStats));
        return result;
    }
}
