package com.hamidsultanzadeh.ticketservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ticket")
@Entity
@Data
@Builder
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;
    @Column(name = "description",length = 600)
    private String description;
    @Column(name = "notes",length = 4000)
    private String notes;
    @Column(name = "assignee", length = 50)
    private String assignee;
    @Column(name = "ticketDate")
    private Date ticketDate;
    @Column(name = "priority_type")
    @Enumerated
    private PriorityType priorityType;
    @Column(name = "ticket_status")
    @Enumerated
    private TicketStatus ticketStatus;

}
