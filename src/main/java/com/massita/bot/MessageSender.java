package com.massita.bot;

import com.massita.coreapi.MessageAboutNotifiedEvent;
import com.massita.coreapi.MessageSentToScheduleEvent;
import org.axonframework.eventhandling.EventHandler;

/**
 * {@link MessageSender} declare handlers that invoked on event that
 * created when Message should be send to chat
 */
public interface MessageSender {

    @EventHandler
    void on(MessageSentToScheduleEvent event) throws Exception;

    @EventHandler
    void on(MessageAboutNotifiedEvent event) throws Exception;

}
