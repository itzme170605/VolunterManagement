/*
 * 
 */
package com.ufund.api.ufundapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore nrecognized fields during deserialization
public class Need {

    // Package private for tests
    static final String STRING_FORMAT = "Need [id=%d, name=%s, facility=%s, description=%s, datetime=%s, active=%b, required=%d, current=%d, volunteers=(%s)]";
    
    private int id;
    private String name;
    private String facility;
    private String description;
    private String datetime; // stores dattime values in DDMMYY HHMMSS
    private boolean active;
    private int required; //required number of volunteers
    private ArrayList<String> volunteers;
    private int volunteerCount;


    public Need() {}

    public Need(int id, String name, String facility, String description, String datetime, int required, boolean active){
        this.id = id;
        this.name = name;
        this.facility = facility;
        this.description = description;
        this.datetime = datetime;
        this.active = active;
        this.required = required;
        this.volunteers = new ArrayList<>();
        this.volunteerCount = 0;
    }


    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getFacility() { return this.facility; }
    public void setFacility(String facility) { this.facility = facility; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getDatetime() { return this.datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    public boolean isActive() { return this.active; } // For boolean fields, use 'is' prefix
    public void setStatus(boolean status) { this.active = status; }

    public int getRequired() { return this.required; }
    public void setRequired(int required) { this.required = required; }

    public ArrayList<String> getVolunteers() { return this.volunteers; }
    public void addVolunteer(User volunteer) { 
        volunteers.add(volunteer.getUserName()); 
        this.volunteerCount++; 
    }
    public void removeVolunteer(String username) { 
        for (int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).equals(username)) {
                volunteers.remove(i);
            }
        }
        this.volunteerCount--;
    }

    public int getvolunteerCount(){
        return this.volunteerCount;
    }

    public String volunteersToString() {
        String volunteerList = "";
        if(!(volunteers == null)) {
            for (String volunteer : volunteers) {
                volunteerList += volunteer + ", ";
            }
        }
        return volunteerList;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name, facility, description, datetime, active, required, volunteerCount, volunteersToString());
    }
}
