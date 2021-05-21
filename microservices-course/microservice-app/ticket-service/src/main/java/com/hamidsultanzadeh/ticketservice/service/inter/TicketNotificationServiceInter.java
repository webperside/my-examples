package com.hamidsultanzadeh.ticketservice.service.inter;

import com.hamidsultanzadeh.ticketservice.entity.Ticket;

public interface TicketNotificationServiceInter {

    void pushToQueue(Ticket ticket);
}
