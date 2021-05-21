package com.hamidsultanzadeh.ticketservice.service.impl;

import com.hamidsultanzadeh.client.messaging.TicketNotification;
import com.hamidsultanzadeh.ticketservice.entity.Ticket;
import com.hamidsultanzadeh.ticketservice.service.inter.TicketNotificationServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class TicketNotificationServiceImpl implements TicketNotificationServiceInter {

    private final Source source;

    @Override
    public void pushToQueue(Ticket ticket) {
        TicketNotification ticketNotification = TicketNotification.builder()
                .ticketDescription(ticket.getDescription())
                .ticketId(ticket.getId())
                .accountId(ticket.getAssignee())
                .build();

        source.output().send(MessageBuilder.withPayload(ticketNotification).build());


    }
}
