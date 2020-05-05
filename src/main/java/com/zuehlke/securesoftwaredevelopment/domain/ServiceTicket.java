package com.zuehlke.securesoftwaredevelopment.domain;

import java.util.UUID;

public class ServiceTicket {
    UUID ticketNumber;
    String time;
    Integer id;

    public UUID getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(UUID ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
