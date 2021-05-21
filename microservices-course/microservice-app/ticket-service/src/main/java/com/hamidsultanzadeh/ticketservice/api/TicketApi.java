package com.hamidsultanzadeh.ticketservice.api;

import com.hamidsultanzadeh.ticketservice.dto.TicketDTO;
import com.hamidsultanzadeh.ticketservice.service.inter.TicketServiceInter;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketApi {

    private final TicketServiceInter ticketServiceInter;

    public TicketApi(TicketServiceInter ticketServiceInter) {
        this.ticketServiceInter = ticketServiceInter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(ticketServiceInter.findById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDTO> save(@RequestBody TicketDTO ticketDTO){
        return ResponseEntity.ok(ticketServiceInter.save(ticketDTO));
    }

    @PutMapping
    public ResponseEntity<TicketDTO> update(@RequestBody TicketDTO ticketDTO){
        return ResponseEntity.ok(ticketServiceInter.update(ticketDTO));
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> findAll(@RequestParam(name = "page") Integer page){
        return ResponseEntity.ok(ticketServiceInter.findAll(PageRequest.of(--page,10)));
    }
}
