package com.example.giftfinder;

import java.util.Date;

public class Event {
    private String eventName;
    private Date eventDate;
    private String giftIdea;

    public Event(String eventName, Date eventDate, String giftIdea) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.giftIdea = giftIdea;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getGiftIdea() {
        return giftIdea;
    }

    public void setGiftIdea(String giftIdea) {
        this.giftIdea = giftIdea;
    }
}
