package com.massita.bot;

import com.massita.coreapi.MessageAboutNotifiedEvent;
import com.massita.coreapi.MessageScheduledEvent;
import com.massita.coreapi.MessageSentToScheduleEvent;
import org.springframework.stereotype.Controller;

@Controller
public class MockMessageSender implements MessageSender {

    @Override
    public void on(MessageSentToScheduleEvent event) throws Exception {
    }

    @Override
    public void on(MessageScheduledEvent event) throws Exception {

    }

    @Override
    public void on(MessageAboutNotifiedEvent event) throws Exception {
    }

}
