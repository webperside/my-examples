package com.hamidsultanzadeh.ticketservice.service.impl;

import com.hamidsultanzadeh.client.AccountServiceClient;
import com.hamidsultanzadeh.client.contract.AccountDTO;
import com.hamidsultanzadeh.ticketservice.dao.TicketDaoInter;
import com.hamidsultanzadeh.ticketservice.dao.es.TicketElasticDaoInter;
import com.hamidsultanzadeh.ticketservice.dto.TicketDTO;
import com.hamidsultanzadeh.ticketservice.entity.PriorityType;
import com.hamidsultanzadeh.ticketservice.entity.Ticket;
import com.hamidsultanzadeh.ticketservice.entity.TicketStatus;
import com.hamidsultanzadeh.ticketservice.entity.es.TicketModel;
import com.hamidsultanzadeh.ticketservice.service.inter.TicketNotificationServiceInter;
import com.hamidsultanzadeh.ticketservice.service.inter.TicketServiceInter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // trick for autowire
public class TicketServiceImpl implements TicketServiceInter {

    private final TicketDaoInter ticketDaoInter;
    private final TicketElasticDaoInter ticketElasticDaoInter;
    private final ModelMapper modelMapper;
    private final AccountServiceClient accountServiceClient;
    private final TicketNotificationServiceInter ticketNotificationServiceInter;

    @Override
    @Transactional
    public TicketDTO save(TicketDTO ticketDTO) {

        ResponseEntity<AccountDTO> response = accountServiceClient.get(ticketDTO.getAssignee());

        if(!response.getStatusCode().is2xxSuccessful() || response.getBody() == null){
            return null;
        }

        Ticket ticket = Ticket.builder()
                .notes(ticketDTO.getNotes() == null ? "N/A" : ticketDTO.getNotes() )
                .description(ticketDTO.getDescription() == null ? "N/A" : ticketDTO.getDescription())
                .assignee(ticketDTO.getAssignee())//TODO Account Api validation
                .priorityType(PriorityType.valueOf(ticketDTO.getPriorityTypeName()))
                .ticketStatus(TicketStatus.valueOf(ticketDTO.getTicketStatusName()))
                .ticketDate(ticketDTO.getTicketDate())
                .build();

        ticket = ticketDaoInter.save(ticket);

        TicketModel ticketModel = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
//                .assignee(response.getBody().getNameSurname())
                .assignee(ticket.getAssignee())
                .id(ticket.getId())
                .ticketDate(ticket.getTicketDate())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .priorityType(ticket.getPriorityType().getLabel())
                .build();

        ticketElasticDaoInter.save(ticketModel);

        ticketNotificationServiceInter.pushToQueue(ticket);

        ticketDTO.setId(ticket.getId());
        ticketDTO.setAssignee(response.getBody().getNameSurname());

        return ticketDTO;
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO) {
        Optional<Ticket> ticket = ticketDaoInter.findById(ticketDTO.getId());
        ticket.ifPresent(t ->{
            t.setAssignee(ticketDTO.getAssignee() == null ? t.getAssignee() : ticketDTO.getAssignee());
            t.setDescription(ticketDTO.getDescription() == null ? t.getDescription() : ticketDTO.getDescription());
            t.setNotes(ticketDTO.getNotes() == null ? t.getNotes() : ticketDTO.getNotes());
        });
        return null;
    }

    @Override
    public List<TicketDTO> findAll(Pageable pageable) {
        return ticketDaoInter.findAll(pageable).getContent()
                .stream()
                .map(t->modelMapper.map(t,TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO findById(String id) {
        return ticketDaoInter.findById(id).map(t->modelMapper.map(t,TicketDTO.class)).orElse(null);
    }
}
