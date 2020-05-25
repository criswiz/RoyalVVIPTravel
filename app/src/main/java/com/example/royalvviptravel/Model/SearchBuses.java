package com.example.royalvviptravel.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SearchBuses {
    private String destination;
    private String from;
    private String date;

    //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    public SearchBuses() {
        this("Kumasi", "Accra", "12/12/2019");
    }

    public SearchBuses(String destination, String from, String date) {
        this.destination = destination;
        this.from = from;
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
