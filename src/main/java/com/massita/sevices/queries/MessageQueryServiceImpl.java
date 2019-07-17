package com.massita.sevices.queries;

import com.massita.coreapi.DatePeriodMessagesQuery;
import com.massita.query.messages.Message;
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final QueryGateway gateway;

    public MessageQueryServiceImpl(QueryGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Future<List<Message>> listMessagesBetween(LocalDateTime start, LocalDateTime finish) {
        return gateway.query(new DatePeriodMessagesQuery(start, finish), new MultipleInstancesResponseType<>(Message.class));
    }

    @Override
    public Future<List<Message>> listMessagesToday() {
        return listMessagesBetween(
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS),
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1)

        );
    }
}
