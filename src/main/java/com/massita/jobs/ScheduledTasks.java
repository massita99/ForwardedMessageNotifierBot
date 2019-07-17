package com.massita.jobs;

import com.massita.query.messages.Message;
import com.massita.sevices.commands.MessageCommandService;
import com.massita.sevices.queries.MessageQueryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    MessageQueryService messageQueryService;

    MessageCommandService messageCommandService;

    public ScheduledTasks(MessageQueryService messageQueryService, MessageCommandService messageCommandService) {
        this.messageQueryService = messageQueryService;
        this.messageCommandService = messageCommandService;
    }

    @Scheduled(cron = "${cron.notify_schedule}")
    public void notifyListeners() throws ExecutionException, InterruptedException {
        List<Message> todayMessagesToNotify = messageQueryService.listMessagesToday().get();
        todayMessagesToNotify.forEach(message -> messageCommandService.notifyAboutMessage(message.getNotifyToMessageId()));
    }
}
