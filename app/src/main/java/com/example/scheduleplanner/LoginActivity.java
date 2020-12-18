package com.example.scheduleplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scheduleplanner.controller.ShiftController;
import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.Shift;
import com.example.scheduleplanner.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail;
    EditText edtPassword;
    Button btnSignUp;
    Button btnSignIn;
    UserController userController;
    ShiftController shiftController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userController = new UserController();
        shiftController = new ShiftController();

        // Populate with default data

        User shiftUser = new User("Amy", "Brown", "ab@email.com", "password");

        userController.insertUser(new User("Jim", "Doe", "jd@email.com", "password"));
        userController.insertUser(new User("Alice", "James", "alice.james@email.com", "password"));
        userController.insertUser(new User("Michael", "Webster", "mdw@email.com", "password"));
        userController.insertUser(shiftUser);

        // Add a default shift to the page
        shiftController.insertShift(new Shift(shiftUser, "10:00 AM"));

        this.referWidgets();

    }

    /* HI THIS IS AJ */
    private void referWidgets(){
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);

        btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signin:
                //signin
                this.doSignIn();
                break;
            case R.id.btn_signup:
                //signup
                this.doSignUp();
                break;
        }
    }

    private void doSignIn(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        //TODO code for login
        if (userController.validateUser(email, password)){
           // Toast.makeText(getApplicationContext(), "Welcome to the app", Toast.LENGTH_LONG).show();
          //  Intent mainIntent = new Intent(this, MainActivity.class);
           // startActivity(mainIntent);
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra("EXTRA_EMAIL", email);
            startActivity(mainIntent);
        }else{
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
        }



    }

    private void doSignUp(){
        //open next screen for signup
        Intent signupIntent = new Intent(this, SignUpActivity.class);
        startActivity(signupIntent);
    }

}
