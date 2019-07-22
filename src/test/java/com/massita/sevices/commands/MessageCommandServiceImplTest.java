package com.massita.sevices.commands;

import com.massita.coreapi.NotifyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * {@link MessageCommandService} methods tests
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MessageCommandServiceImplTest {

    @Autowired
    MessageCommandService messageCommandService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void saveMessage() throws InterruptedException, ExecutionException {
        Future<String> resultWaiter = messageCommandService.createMessage("1", "1");
        resultWaiter.get();
    }

    @Test
    public void scheduleMessage() throws InterruptedException, ExecutionException {
        messageCommandService.createMessage("2", "1");
        Future<Void> resultWaiter = messageCommandService.scheduleMessage("2", NotifyType.TOMORROW);
        resultWaiter.get();
    }

    @Test
    public void errorScheduleMessageWhenNotCreated() throws InterruptedException, ExecutionException {
        Assertions.assertThrows(ExecutionException.class, () -> {
            Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("3");
            resultWaiter.get();
        });
    }

    @Test
    public void errorScheduleMessageWhenNotScheduled() throws InterruptedException, ExecutionException {
        messageCommandService.createMessage("4", "1");
        Assertions.assertThrows(ExecutionException.class, () -> {
            Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("4");
            resultWaiter.get();

        });

    }

}