package com.example.scheduleplanner;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserController userController;
    Button btnEdit;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userController = new UserController();
        this.fetchUserInfo();
        this.referWidgets();

    }

    private void referWidgets(){
        btnEdit = findViewById(R.id.btn_edit);
        btnAdd = findViewById(R.id.btn_add);
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

    private void doAdd(){
//        Intent signupIntent = new Intent(this, SignUpActivity.class);
//        startActivity(signupIntent);
    }

}
