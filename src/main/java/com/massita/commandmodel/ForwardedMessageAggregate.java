package com.massita.commandmodel;

import com.massita.coreapi.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static com.massita.commandmodel.Status.*;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
public class ForwardedMessageAggregate {

    @AggregateIdentifier
    private String messageId;

    private String chatId;

    private Status status;

    @CommandHandler
    public ForwardedMessageAggregate(CreateMessageCommand command) {
        apply(new MessageCreatedEvent(command.getMessageId(), command.getChatId()));
    }

    @CommandHandler
    public void handle(ToScheduleMessageCommand command) {
        apply(new MessageSentToScheduleEvent(command.getMessageId(), this.getChatId()));
    }

    @CommandHandler
    public void handle(ScheduleMessageCommand command) {
        apply(new MessageScheduledEvent(command.getMessageId(), this.getChatId(), command.getTime()));
    }

    @CommandHandler
    public void handle(NotifyAboutMessageCommand command) {
        if (this.status != SCHEDULED) {
            throw new IllegalStateException("Cannot send not scheduled message");
        }
        apply(new MessageAboutNotifiedEvent(command.getMessageId(), this.getChatId()));
    }

    @EventSourcingHandler
    protected void on(MessageCreatedEvent event) {
        this.messageId = event.getMessageId();
        this.chatId = event.getChatId();
        this.status = NEW;

    }

    @EventSourcingHandler
    protected void on(MessageScheduledEvent event) {
        this.status = SCHEDULED;
    }

    @EventSourcingHandler
    protected void on(MessageAboutNotifiedEvent event) {
        this.status = DELIVERED;
    }





}
