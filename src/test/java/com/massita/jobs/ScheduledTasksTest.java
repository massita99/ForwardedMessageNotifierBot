package com.massita.jobs;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * Test for notification scheduler check that task is scheduled by specific CRON expression
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
public class ScheduledTasksTest {

    @SpyBean
    ScheduledTasks scheduledTasks;

    @Test
    public void notifyListeners() {
        Awaitility.await()
                .atMost(Duration.FIVE_SECONDS)
                .untilAsserted(() -> Mockito.verify(scheduledTasks, Mockito.atLeast(5)).notifyListeners());
    }

}