package com.example.firstroad.utils;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MyToast {

    public static void MyToast(AppCompatActivity activity, String toasts){
        Toast.makeText(activity, toasts, Toast.LENGTH_SHORT).show();
    }

}
