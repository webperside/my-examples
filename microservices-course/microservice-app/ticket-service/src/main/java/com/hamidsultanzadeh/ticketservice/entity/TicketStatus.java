package com.hamidsultanzadeh.ticketservice.entity;

public enum TicketStatus {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed");

    private String label;

    TicketStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
