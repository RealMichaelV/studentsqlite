package edu.montclair.vasquezm15.studentsqlite; //package name

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    Handler handler; //assigned a variable to the Handler which is handler
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); //the view within the activity_splash

        getSupportActionBar().hide(); //hide the title of the top of the app

        handler = new Handler(); //saying to get a new Handler
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { // PostDelayed within the Handler
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);//Once the Splash screen shows up it will redirect to the Main Activity
                startActivity(intent); //The intent will start right away
                finish(); // once started it will finish and end
            }
        }, 3000); //delayed by 3,000 milliseconds
    }
}