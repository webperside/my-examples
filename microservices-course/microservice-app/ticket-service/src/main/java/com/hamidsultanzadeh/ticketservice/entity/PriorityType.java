package com.hamidsultanzadeh.ticketservice.entity;

public enum PriorityType {
    URGENT("Urgent"),
    LOW("Low"),
    HIGH("High");

    private String label;

    PriorityType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
