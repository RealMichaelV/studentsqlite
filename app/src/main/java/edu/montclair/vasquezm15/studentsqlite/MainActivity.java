package edu.montclair.vasquezm15.studentsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button Login; //button assigned to Login


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //The view within the content of the Activity_main
        getSupportActionBar().hide(); //hide the title from the top
        Login = (Button) findViewById(R.id.login); // Login is a button assigned within the id of login
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        }); //While the login  button is click it will listen within the intent of the activity and will open it and redirect to it
    }
    public void openLoginActivity(){ // created a void for the OpenLoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class); //created a new intent in the main activity  will redirect to the LoginActivity
        startActivity(intent); // will start the activity and will redirect

    }
}