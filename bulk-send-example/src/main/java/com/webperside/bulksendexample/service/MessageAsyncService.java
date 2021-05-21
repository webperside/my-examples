package com.webperside.bulksendexample.service;

import com.webperside.bulksendexample.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageAsyncService {

    private final MessageService messageService;

    @Async("messageSenderTaskExecutor")
    public void sendMessageAsync(Message message){
        messageService.sendMessage(message);
    }

}
