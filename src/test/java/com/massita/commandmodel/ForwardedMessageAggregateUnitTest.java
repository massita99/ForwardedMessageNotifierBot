package com.massita.commandmodel;

import com.massita.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for main business logic
 */
public class ForwardedMessageAggregateUnitTest {

    private FixtureConfiguration<ForwardedMessageAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ForwardedMessageAggregate.class);
    }

    @Test
    public void testCreateMessage() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateMessageCommand("1", "1"))
                .expectEvents(new MessageCreatedEvent("1", "1"));
    }

    @Test
    public void testToScheduleMessage() throws Exception {
        fixture.given(new MessageCreatedEvent("1", "1"))
                .when(new ToScheduleMessageCommand("1"))
                .expectEvents(new MessageSentToScheduleEvent("1", "1"));
    }

    @Test
    public void testScheduleMessage() throws Exception {
        fixture.given(new MessageCreatedEvent("1", "1"))
                .when(new ScheduleMessageCommand("1", NotifyType.NEVER))
                .expectEvents(new MessageScheduledEvent("1", "1", NotifyType.NEVER));
    }

    @Test
    public void testNotifyAboutMessage() throws Exception {
        fixture.given(new MessageCreatedEvent("1", "1"),
                new MessageScheduledEvent("1", "1", NotifyType.NEVER))
                .when(new NotifyAboutMessageCommand("1"))
                .expectEvents(new MessageAboutNotifiedEvent("1", "1"));
    }

    @Test
    public void testDoNotNotifyAboutNotScheduledMessage() {
        fixture.given(new MessageCreatedEvent("1", "1"))
                .when(new NotifyAboutMessageCommand("1"))
                .expectException(IllegalStateException.class)
                .expectNoEvents();

    }


}