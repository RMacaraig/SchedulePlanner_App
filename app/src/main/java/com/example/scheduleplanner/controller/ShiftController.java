package com.example.scheduleplanner.controller;

import com.example.scheduleplanner.ShiftSingleton;
import com.example.scheduleplanner.model.Shift;

import java.util.ArrayList;

public class ShiftController {

    public void insertShift(Shift newShift){

        ShiftSingleton.getInstance().addShift(newShift);
    }

    public ArrayList<Shift> fetchAllShifts(){
        return ShiftSingleton.getInstance().getShifts();
    }
}
