package com.webperside.bulksendexample.service;

import com.webperside.bulksendexample.enums.MessageStatus;
import com.webperside.bulksendexample.models.Message;
import com.webperside.bulksendexample.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.concurrent.RejectedExecutionException;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageSenderRunner {

    private final MessageRepository messageRepository;
    private final MessageAsyncService messageAsyncService;

    public void executeMessageSender(){
        LinkedList<Message> messages = messageRepository.findFirst1ByStatus(MessageStatus.PENDING);
//        log.info("executeMessageSender : list.size() = " + messages.size());

        while (!messages.isEmpty()){
            Message message = null;
            try{
                message = messages.removeFirst();

                message.setStatus(MessageStatus.IN_PROGRESS);
                messageRepository.save(message);
                log.info("Process Start : {}", message);
                messageAsyncService.sendMessageAsync(message);
                log.info("Process Finish : {}", message);
            } catch (RejectedExecutionException e){
                // An executor will throw RejectedExecutionException if it has been shut
                // down when you try to submit a new job for it to run
                log.error("Exception: {}, Message: {}", e.getClass().getSimpleName(), e.getMessage());
                messages.addFirst(message);
            } catch (Exception e){
                log.error("Exception: {}, Message: {}", e.getClass().getSimpleName(), e.getMessage());
                if(message != null){
                    message.setStatus(MessageStatus.FAILED);
                    messageRepository.save(message);
                }
            }

        }

    }

}
