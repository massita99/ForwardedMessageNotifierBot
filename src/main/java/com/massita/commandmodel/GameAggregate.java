package com.massita.commandmodel;

import com.massita.coreapi.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static com.massita.commandmodel.Status.IN_PROCESS;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
public class GameAggregate {

    @AggregateIdentifier
    private String chatId;

    private Status status;

    @CommandHandler
    public GameAggregate(CreateGameCommand command) {
        apply(new CreateGameEvent(command.getChatId()));
    }

    @CommandHandler
    public void handle(StartEventCommand command) {
        if (this.status == IN_PROCESS) {
            apply(new StartEventEvent(command.getChatId(), command.getDescription(), command.getActions(), command.getPhoto()));
        }
    }

    @CommandHandler
    public void handle(ProcessEventCommand command) {
        apply(new ProcessEventEvent(command.getChatId(), command.getAnswerText()));
    }

    @CommandHandler
    public void handle(ReturnResultEventCommand command) {
        apply(new ReturnResultEventEvent(command.getChatId(), command.getResultText(), command.getStats()));
    }

    @CommandHandler
    public void handle(UpdateStatsCommand command) {
        apply(new UpdateStatsEvent(command.getChatId(), command.getStats()));
    }

    @CommandHandler
    public void handle(ResetStatsCommand command) {
        apply(new ResetStatsEvent(command.getChatId()));
    }

    @EventSourcingHandler
    protected void on(CreateGameEvent event) {
        this.chatId = event.getChatId();
        this.status = IN_PROCESS;
    }

}
