package com.massita.commandmodel;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests for main business logic
 */
public class ForwardedMessageAggregateUnitTest {

    private FixtureConfiguration<GameAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(GameAggregate.class);
    }




}