package com.webperside.bulksendexample.tasks;

import com.webperside.bulksendexample.service.MessageSenderRunner;
import com.webperside.bulksendexample.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final MessageSenderRunner messageSenderRunner;
    private final MessageService messageService;

    @Scheduled(initialDelay = 10000, fixedDelay = 2000)
    public void runBulkSmsSendingRunner(){
        messageSenderRunner.executeMessageSender();
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 2000)
    public void runBulkSmsSaveRunner(){
        messageService.saveMessage();
    }
}
