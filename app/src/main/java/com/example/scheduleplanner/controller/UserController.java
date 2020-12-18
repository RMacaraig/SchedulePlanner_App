package com.example.scheduleplanner.controller;

import com.example.scheduleplanner.UserSingleton;
import com.example.scheduleplanner.model.User;
import java.util.ArrayList;

public class UserController {

    public void insertUser(User newUser){

        UserSingleton.getInstance().addUser(newUser);
    }

    public User fetchUserByEmail(String email){
        return UserSingleton.getInstance().getUserByEmail(email);

    }

    public User fetchByFullName(String fullName)  {
        return UserSingleton.getInstance().getUserByFullName(fullName);
    }

    public ArrayList<User> fetchAllUsers(){
        return UserSingleton.getInstance().getUserList();
    }

    public boolean validateUser(String email, String password){
        return UserSingleton.getInstance().validateUser(email, password);
    }

}
