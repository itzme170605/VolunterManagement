package com.ufund.api.ufundapi.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Helper extends User{

    //contact info
    private String email;       //required
    private String phoneNumber; //optional
    
    //location
    private String location;

    //needs Basket
    private ArrayList<Need> basket;

    private List<ScheduleItem> schedule;
    
    @JsonProperty
    private int donated;



    public Helper(String userName, String password) {
        super(userName, password);

        email = "";
        phoneNumber = "";
        basket = new ArrayList<>();
        schedule = new ArrayList<>();
    }

    public Helper() {
        super("","");
        email = "";
        phoneNumber = "";
        basket = new ArrayList<>();
        schedule = new ArrayList<>();

    }
        public int getDonated() {
        return donated;
    }

    public void setDonated(int donated) {
        this.donated = donated;
    }

    public List<ScheduleItem> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItem> schedule) {
        this.schedule = schedule;
    }

    public void addNeedToBasket(Need need) {
        basket.add(need);
    }

    public void removeNeedFromBasket(Need need) {
        basket.remove(need);
    }

    public ArrayList<Need> getBasket()
    {
        return basket;
    }

    public void setBasket(ArrayList<Need> newBasket)
    {
        basket = newBasket;
    }

    @Override
    public boolean isManager() {
        return false;
    }

    public void checkout(){
        for(int i = 0; i < this.basket.size(); i++){
            this.schedule.add(new ScheduleItem(this.basket.get(i), null));
        }
        this.basket.clear();
    }
}
