package com.massita.query.messages;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Test for specific JPA methods
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void findAllByNotifyTimeBetweenTest() {

        Message todayMessage = new Message("1", "1");
        todayMessage.setNotifyTime(LocalDateTime.now());
        Message todayMessageSecond = new Message("2", "2");
        todayMessageSecond.setNotifyTime(LocalDateTime.now());
        Message tomorrowMessage = new Message("3", "3");
        tomorrowMessage.setNotifyTime(LocalDateTime.now().plusDays(1));

        messageRepository.save(todayMessage);
        messageRepository.save(todayMessageSecond);
        messageRepository.save(tomorrowMessage);

        List<Message> todayMessages = messageRepository.findAllByNotifyTimeBetween(
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS),
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1)
        );

        Assert.assertEquals(2, todayMessages.size());
    }

}