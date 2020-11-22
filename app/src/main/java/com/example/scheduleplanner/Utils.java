package com.example.scheduleplanner;

import android.text.TextUtils;
import android.util.Patterns;

public class Utils {
    //if email is valid
    public static boolean isValidEmail (String target){
        //is not empty
        //text utils class that performs string data
        //if given variable is empty
        return (!TextUtils.isEmpty(target) &&
                Patterns.EMAIL_ADDRESS.matcher(target).matches());


        //valid first name - first letter must be letter
        //phone number min. 10 digits no letters
    }
}
