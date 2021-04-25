package edu.montclair.vasquezm15.studentsqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity { //HomeActivity where you can add your code
    private Button logout; //Adding a variable to Button which is logout.
    private Button waitlist;
    private Button students;
    SharedPreferences preferences; //SharedPreference is what i used to share a database within the localhost of the android phone to store the login details
    SharedPreferences.Editor preferencesEditor; //The SharedPreference editor within the variable assigned to preferenceEditor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //View within the activity_home
        getSupportActionBar().hide(); //this is to the hide the title of the top of the app
        logout = findViewById(R.id.logout);// assigned logout to the button logout
        waitlist = findViewById(R.id.waitlist);
        students = findViewById(R.id.students);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //while the button logout is clicks and listens
                Intent intent = new Intent(HomeActivity.this, MainActivity.class); //created an Intent for from once clicked logout if will go back the main activity
                startActivity(intent); //While the intent is started and will redirect to the main activity
                finish(); // to end it so you can't go back to view the previous page on the android device
                Toast.makeText(HomeActivity.this, "Successful Logged-out!", Toast.LENGTH_SHORT).show(); //once logged out the toast will appear on the main activity
            }
        });
        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        waitlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, students.class);
                startActivity(intent);
            }
        });

    }
}
