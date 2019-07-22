package com.massita.bot;


import com.massita.coreapi.MessageAboutNotifiedEvent;
import com.massita.coreapi.MessageScheduledEvent;
import com.massita.coreapi.MessageSentToScheduleEvent;
import com.massita.coreapi.NotifyType;
import com.massita.sevices.commands.MessageCommandService;
import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.Future;

/**
 * Test checks that {@link MessageSender} Controller react on specific events
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageSenderTest {

    @Autowired
    MessageCommandService messageCommandService;

    @SpyBean
    MockMessageSender messageSender;

    @Test
    public void testSentToScheduleEvent() throws Exception {
        Future<String> resultWaiter = messageCommandService.createMessage("1", "1");
        resultWaiter.get();

        Awaitility.await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() ->
                        Mockito.verify(messageSender, Mockito.atLeast(1)).on(ArgumentMatchers.any(MessageSentToScheduleEvent.class)));

    }

    @Test
    public void testMessageAboutNotifiedEvent() throws Exception {
        messageCommandService.createMessage("2", "1");
        messageCommandService.scheduleMessage("2", NotifyType.TOMORROW);
        Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("2");
        resultWaiter.get();

        Awaitility.await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() ->
                        Mockito.verify(messageSender, Mockito.atLeast(1)).on(ArgumentMatchers.any(MessageAboutNotifiedEvent.class)));

    }

    @Test
    public void testMessageScheduledEvent() throws Exception {
        messageCommandService.createMessage("3", "1");
        Future<Void> resultWaiter = messageCommandService.scheduleMessage("3", NotifyType.TOMORROW);
        resultWaiter.get();

        Awaitility.await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() ->
                        Mockito.verify(messageSender, Mockito.atLeast(1)).on(ArgumentMatchers.any(MessageScheduledEvent.class)));

    }

}
