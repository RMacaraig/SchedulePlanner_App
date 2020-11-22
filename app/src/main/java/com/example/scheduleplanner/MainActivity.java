package com.example.scheduleplanner;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.User;

public class MainActivity extends AppCompatActivity {

    UserController userController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userController = new UserController();
        this.fetchUserInfo();
    }


    private void fetchUserInfo() {

        Intent thisIntent = this.getIntent();
        String email = thisIntent.getStringExtra("EXTRA_EMAIL");
        //Log.d("MainActivity", userController.fetchUserByEmail(email).toString());

        User currentUser = userController.fetchUserByEmail(email);
        Log.d("MainActivity", currentUser.toString());
        Log.d("MainActivity", "Firstname : " + currentUser.getFirstName());
    }

}
