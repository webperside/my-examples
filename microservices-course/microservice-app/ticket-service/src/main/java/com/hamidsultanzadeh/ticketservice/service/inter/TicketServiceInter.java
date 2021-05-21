package com.hamidsultanzadeh.ticketservice.service.inter;

import com.hamidsultanzadeh.ticketservice.dto.TicketDTO;
import com.hamidsultanzadeh.ticketservice.entity.Ticket;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketServiceInter {

    TicketDTO save(TicketDTO ticketDTO);

    TicketDTO update(TicketDTO ticketDTO);

    List<TicketDTO> findAll(Pageable pageable);

    TicketDTO findById(String id);

}
