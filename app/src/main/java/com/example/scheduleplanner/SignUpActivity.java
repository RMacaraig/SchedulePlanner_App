package com.example.scheduleplanner;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scheduleplanner.controller.UserController;
import com.example.scheduleplanner.model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    String firstName;
    String lastName;
    String email;
    String password;
    EditText edtFname;
    EditText edtLname;
    EditText edtEmail;
    EditText edtPswd;
    EditText edtConfirm;
    Button btnSubmit;

    User newUser;
    //create instance of controller
    UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.referWidgets();
        userController = new UserController();
    }

    private void referWidgets(){
        edtFname = findViewById(R.id.edt_firstname);
        edtLname = findViewById(R.id.edt_lastname);
        edtEmail = findViewById(R.id.edt_email);
        edtPswd = findViewById(R.id.edt_password);
        edtConfirm = findViewById(R.id.edt_confirm_password);

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                //submit
                if (this.validateData()){
                    this.getValues();
                    //load data to the model object
                    newUser = new User(firstName, lastName,
                            email, password);
                    Log.d("signActivity", newUser.toString());
                    //Toast.makeText ( "Confirm").toString().show();

                    //create new user object
                    userController.insertUser(newUser);

                    Intent mainIntent = new Intent(this, MainActivity.class);
                    mainIntent.putExtra("EXTRA_EMAIL", email);
                    startActivity(mainIntent);
                }
                break;
        }
    }

    private void getValues(){
        firstName = edtFname.getText().toString();
        lastName = edtLname.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPswd.getText().toString();
    }

    //confirm validation is correct or not
    //validate for entered data for the required constraints
    private boolean validateData(){
        boolean allValidations = true;

        if (edtFname.getText().toString().isEmpty()){
            edtFname.setError("You must enter first name");
            allValidations = false;
        }
        if (edtLname.getText().toString().isEmpty()){
            edtLname.setError("You must enter last name");
            allValidations = false;
        }

        if (edtEmail.getText().toString().isEmpty()){
            edtEmail.setError("Email cannot be empty");
            allValidations = false;

        }else if (!Utils.isValidEmail(edtEmail.getText().toString())){
            edtEmail.setError("Please provide valid email address");
            allValidations = false;
        }
        if (edtPswd.getText().toString().isEmpty()){
            edtPswd.setError("Please enter password");
            allValidations = false;
        }
        if (edtConfirm.getText().toString().isEmpty()){
            edtConfirm.setError("You must confirm password");
            allValidations = false;

        }else if (!edtPswd.getText().toString().equals(edtConfirm.getText().toString())){
            edtConfirm.setError("Both passwords must be the same");
            allValidations = false;
        }

        return allValidations;

    }
}
