package com.hamidsultanzadeh.ticketservice.dto;

import com.hamidsultanzadeh.ticketservice.entity.PriorityType;
import com.hamidsultanzadeh.ticketservice.entity.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {

    private String id;
    private String description;
    private String notes;
    private String assignee;
    private Date ticketDate;
    private String priorityTypeName;
    private String ticketStatusName;
}
