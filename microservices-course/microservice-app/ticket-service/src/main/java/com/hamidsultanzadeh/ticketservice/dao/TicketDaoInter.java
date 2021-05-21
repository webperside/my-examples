package com.hamidsultanzadeh.ticketservice.dao;

import com.hamidsultanzadeh.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDaoInter extends JpaRepository<Ticket,String> {

}
