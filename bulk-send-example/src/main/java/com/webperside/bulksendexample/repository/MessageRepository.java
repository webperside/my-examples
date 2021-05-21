package com.webperside.bulksendexample.repository;

import com.webperside.bulksendexample.enums.MessageStatus;
import com.webperside.bulksendexample.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    LinkedList<Message> findFirst1ByStatus(MessageStatus messageStatus);

}
