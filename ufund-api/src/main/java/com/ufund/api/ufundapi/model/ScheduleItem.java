package com.ufund.api.ufundapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ScheduleItemDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleItem {
    private Need need;
    private Date dateTime;

    // Constructor
    public ScheduleItem(Need need, Date dateTime) {
        this.need = need;
        this.dateTime = dateTime;
    }

    // Getters and Setters
    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ScheduleItem{need=" + need + ", dateTime=" + dateTime + "}";
    }
}
