package com.hamidsultanzadeh.notificationservice;

import com.hamidsultanzadeh.client.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class NotificationDistributionService {

    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification){
        System.out.print("Notification : ");
        System.out.println(ticketNotification);
    }
}