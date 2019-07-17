package com.massita.sevices.queries;

import com.massita.query.messages.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

/** {@link MessageQueryService} declare methods to query info about Messages*/
public interface MessageQueryService {

    /**
     * Return list of Messages that scheduled to notify between dates that pass in params
     * @return List of scheduled Messages
     * */
    Future<List<Message>> listMessagesBetween(LocalDateTime start, LocalDateTime finish);

    /**
     * Return list of Messages that scheduled to notify today
     * @return List of scheduled Messages for today
     */
    Future<List<Message>> listMessagesToday();
}
