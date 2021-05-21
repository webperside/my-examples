package com.webperside.bulksendexample.models;

import com.webperside.bulksendexample.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "number")
    private String number;

    @Column(name = "status", columnDefinition = "tinyint")
    private MessageStatus status;

}
