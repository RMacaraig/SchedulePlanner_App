package com.example.scheduleplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.scheduleplanner.controller.ShiftController;
import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.Shift;
import com.example.scheduleplanner.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AddShiftActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner userSelectorSpinner, timeSpinner, amPmSpinner;
    Button addShift;
    UserController userController;
    ShiftController shiftController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        userController = new UserController();
        shiftController = new ShiftController();


        this.referWidgets();
        populateSpinner();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_add_user:
                this.doCreateShift();
                break;
        }
    }

    private void referWidgets(){
        userSelectorSpinner = findViewById(R.id.spinner_user);
        timeSpinner = findViewById(R.id.spinner_time);
        amPmSpinner = findViewById(R.id.spinner_am_pm);

        addShift = findViewById(R.id.btn_add_user);
        addShift.setOnClickListener(this);


    }

    private void populateSpinner() {
        ArrayList<String> spinnerItems = new ArrayList<String>();

        for (User user: userController.fetchAllUsers()){
            spinnerItems.add(user.getFullName());
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSelectorSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>(
                Arrays.asList("12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")));
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        ArrayAdapter<String> amPmAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, new ArrayList<String>(
                Arrays.asList("AM", "PM")));
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amPmSpinner.setAdapter(amPmAdapter);
    }

    private void doCreateShift() {
        // Fetch the user from the list of users based on which name was selected
        String userFullName = userSelectorSpinner.getSelectedItem().toString();
        String time = timeSpinner.getSelectedItem().toString();
        String amPm = amPmSpinner.getSelectedItem().toString();

        User user = userController.fetchByFullName(userFullName);
        String fullTimeFormat = time + ":00 " + amPm;


        shiftController.insertShift(new Shift(user, fullTimeFormat));
        Intent intent = new Intent();
        intent.putExtra("ADD_SHIFT_FINISHED", true);
        setResult(RESULT_OK, intent);
        finish();
    }
}