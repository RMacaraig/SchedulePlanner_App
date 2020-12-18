package com.example.scheduleplanner;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.scheduleplanner.controller.ShiftController;
import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.Shift;
import com.example.scheduleplanner.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserController userController;
    ShiftController shiftController;
    ListView shiftListView;
    Button btnEdit;
    Button btnAdd;
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userController = new UserController();
        shiftController = new ShiftController();
        this.fetchUserInfo();
        this.referWidgets();

        this.populateListView();

    }

    private void referWidgets(){
        btnEdit = findViewById(R.id.btn_edit);
        btnAdd = findViewById(R.id.btn_add);
        shiftListView = findViewById(R.id.list_shifts);
        btnLogout = findViewById(R.id.btn_logout);

        btnAdd.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    private void populateListView() {
        ArrayList<Shift> listOfShifts = shiftController.fetchAllShifts();

        ArrayList<String> shiftList = new ArrayList<>();

        for (Shift shift: listOfShifts) {
            shiftList.add(shift.getTime() + " - " + shift.getUser().getFullName());
        }


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, shiftList);
        shiftListView.setAdapter(listViewAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_edit:
               this.doEdit();
                break;
            case R.id.btn_add:
               this.doAdd();
                break;
            case R.id.btn_logout:
                this.doLogout();
                break;
        }
    }

    private void fetchUserInfo() {

        Intent thisIntent = this.getIntent();
        String email = thisIntent.getStringExtra("EXTRA_EMAIL");
        //Log.d("MainActivity", userController.fetchUserByEmail(email).toString());

        User currentUser = userController.fetchUserByEmail(email);
        Log.d("MainActivity", currentUser.toString());
        Log.d("MainActivity", "Firstname : " + currentUser.getFirstName());
    }


    private void doEdit(){
//        Intent signupIntent = new Intent(this, SignUpActivity.class);
//        startActivity(signupIntent);
    }

    private void doLogout(){

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void doAdd(){
        Intent addShiftIntent= new Intent(this, AddShiftActivity.class);
        startActivityForResult(addShiftIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                boolean finished = data.getBooleanExtra("ADD_SHIFT_FINISHED", true);
                if (finished) {
                    this.populateListView();
                }
            }
        }
    }
}
