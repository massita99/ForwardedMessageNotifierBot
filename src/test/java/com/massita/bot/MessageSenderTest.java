package com.massita.bot;


import com.massita.coreapi.MessageAboutNotifiedEvent;
import com.massita.coreapi.MessageSentToScheduleEvent;
import com.massita.coreapi.NotifyType;
import com.massita.sevices.commands.MessageCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * Test checks that {@link MessageSender} Controller react on specific events
 */
@RunWith(SpringRunner.class)
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
        //Just to be sure that message Delivered
        Thread.sleep(10);
        Mockito.verify(messageSender, Mockito.atLeast(1)).on(ArgumentMatchers.any(MessageSentToScheduleEvent.class));

    }

    @Test
    public void testMessageAboutNotifiedEvent() throws Exception {
        messageCommandService.createMessage("2", "1");
        messageCommandService.scheduleMessage("2", NotifyType.TOMORROW);
        Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("2");
        resultWaiter.get();

        //Just to be sure that message Delivered
        Thread.sleep(10);
        Mockito.verify(messageSender, Mockito.atLeast(1)).on(ArgumentMatchers.any(MessageAboutNotifiedEvent.class));

    }

}
