package com.example.scheduleplanner.model;

import java.util.Date;

public class Shift {

    User user;

    String time;

//    Time time;
//
    public Shift(User user, String time ) {
        this.user = user;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }
}
