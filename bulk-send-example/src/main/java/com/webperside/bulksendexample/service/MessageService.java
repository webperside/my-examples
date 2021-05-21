package com.webperside.bulksendexample.service;

import com.webperside.bulksendexample.enums.MessageStatus;
import com.webperside.bulksendexample.models.Message;
import com.webperside.bulksendexample.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {

    private final MessageRepository messageRepository;

    @SneakyThrows
    public void sendMessage(Message message){
        message.setStatus(MessageStatus.SUCCESS);
        messageRepository.save(message);
        log.info("Waiting 5 seconds...");
        Thread.sleep(5000);
        log.info("Finished... {}",message);

    }

    public void saveMessage(){
        Message message = new Message();
        message.setNumber(String.valueOf((int)(Math.random() * 100000)));
        message.setText(UUID.randomUUID().toString().substring(0,15));
        message.setStatus(MessageStatus.PENDING);
        messageRepository.save(message);
    }
}
