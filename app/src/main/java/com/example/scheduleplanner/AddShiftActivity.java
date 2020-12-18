package com.example.scheduleplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddShiftActivity extends AppCompatActivity {

    Spinner userSelectorSpinner;
    UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        userController = new UserController();

        this.referWidgets();
        populateSpinner();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_add_user:
                this.doEdit();
                break;
        }
    }

    private void referWidgets(){
        userSelectorSpinner = findViewById(R.id.spinner_user);

    }

    private void populateSpinner() {
        ArrayList<String> spinnerItems = new ArrayList<String>();

        for (User user: userController.fetchAllUsers()){
            spinnerItems.add(user.getFirstName() + " " + user.getLastName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, spinnerItems);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSelectorSpinner.setAdapter(arrayAdapter);
    }

    private void createShift() {
        String user = userSelectorSpinner.getSelectedItem().toString();
    }
}