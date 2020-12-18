package com.example.scheduleplanner;

import android.util.Log;

import com.example.scheduleplanner.model.Shift;
import com.example.scheduleplanner.model.User;

import java.util.ArrayList;

public class ShiftSingleton {
    //lazy initialization
    //did not declare object yet
    private static ShiftSingleton shiftSingleton;

    //list of users
    //empty list
    private ArrayList<Shift> shiftList;

    //method with same name as class to initialize Array list
    //only once when the very first object of the class is created
    private ShiftSingleton(){
        shiftList = new ArrayList<Shift>();
    }



    //one method accessible to anyone
    public static ShiftSingleton getInstance(){

        //if memory is allocated, otherwise return what you have
        if (shiftSingleton == null){
            shiftSingleton = new ShiftSingleton();
        }
        return shiftSingleton;
    }

    //method to add new user into ArrayList
    public void addShift (Shift shift){
        shiftList.add(shift);

        Log.d("UserSingleton", "New shift added");

    }

    public ArrayList<Shift> getShifts() {
        return shiftList;
    }


}
