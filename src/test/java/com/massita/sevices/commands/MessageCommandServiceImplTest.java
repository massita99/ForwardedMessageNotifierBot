package com.massita.sevices.commands;

import com.massita.coreapi.NotifyType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * {@link MessageCommandService} methods tests
 */

@RunWith(SpringRunner.class)
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

    @Test(expected = ExecutionException.class)
    public void errorScheduleMessageWhenNotCreated() throws InterruptedException, ExecutionException {
        Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("3");
        resultWaiter.get();
    }

    @Test(expected = ExecutionException.class)
    public void errorScheduleMessageWhenNotScheduled() throws InterruptedException, ExecutionException {
        messageCommandService.createMessage("4", "1");
        Future<Void> resultWaiter = messageCommandService.notifyAboutMessage("4");
        resultWaiter.get();
    }

}