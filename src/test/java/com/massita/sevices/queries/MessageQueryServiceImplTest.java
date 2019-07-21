package com.massita.sevices.queries;

import com.massita.coreapi.NotifyType;
import com.massita.query.messages.Message;
import com.massita.sevices.commands.MessageCommandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageQueryServiceImplTest {

    @Autowired
    MessageCommandService messageCommandService;

    @Autowired
    MessageQueryService messageQueryService;

    @Test
    public void scheduledMessageSavedTest() throws ExecutionException, InterruptedException {
        messageCommandService.createMessage("1", "1");
        messageCommandService.scheduleMessage("1", NotifyType.TOMORROW);
        messageCommandService.createMessage("2", "1");
        Future<Void> resultWaiter = messageCommandService.scheduleMessage("2", NotifyType.NEXT_WEEK);
        resultWaiter.get();

        Future<List<Message>> tomorrowMessages = messageQueryService.listMessagesBetween(
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1),
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(2)

        );

        Assertions.assertEquals(1, tomorrowMessages.get().size());
    }

}