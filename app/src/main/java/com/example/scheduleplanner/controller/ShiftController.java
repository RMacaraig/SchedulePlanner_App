package com.example.scheduleplanner.controller;

import com.example.scheduleplanner.UserSingleton;
import com.example.scheduleplanner.model.Shift;
import com.example.scheduleplanner.model.User;

public class ShiftController {

    public void insertShift(Shift newShift){

        UserSingleton.getInstance().addUser(newShift);
    }
}
