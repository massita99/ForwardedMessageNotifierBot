package com.massita.query.messages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Test for specific JPA methods
 */

@ExtendWith(SpringExtension.class)
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

        Assertions.assertEquals(2, todayMessages.size());
    }

}