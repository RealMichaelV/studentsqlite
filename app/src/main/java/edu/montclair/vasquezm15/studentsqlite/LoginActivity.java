package edu.montclair.vasquezm15.studentsqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText user, pass; //edittext assigned user and pass
    Button Loginbutton; //button assgined to Loginbutton
    SharedPreferences preferences; //SharedPreferences assigned to preference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //the view of activity_login
        user = findViewById(R.id.username); //user is assigned within the edittext of username of its id
        pass = findViewById(R.id.password); //pass is assigned within the edittext of password of its id
        Loginbutton = findViewById(R.id.Loginbtn); //Loginbutton is a button assigned within its id of Loginbtn
        getSupportActionBar().hide(); //hide the tittle of the top of the app

        preferences = getSharedPreferences("UserData",0); //preferences will get the data will it receive of the userdata and make it private within the device while its stored

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Loginbutton while click it will listen
                if (user.getText().toString().equals("") || pass.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "No User or Password was entered, Please try again!", Toast.LENGTH_SHORT).show(); // if empty fields a toast will show
                } else {
                    String UsernameValue = user.getText().toString(); //assigned a value within user of the string
                    String PasswordValue = pass.getText().toString(); //assigned a value within pass of its string

                    String RegisteredUsername = preferences.getString("username", ""); //it will get the data of username while entered
                    String RegisteredPassword = preferences.getString("password", ""); // it will get the data while password is entered

                    if (UsernameValue.equals(RegisteredUsername) && PasswordValue.equals(RegisteredPassword)) { //checking if username and password matches
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class); //While matches it will redirect to HomeActivty of the intent
                        Toast.makeText(LoginActivity.this, "Successful Logged-in", Toast.LENGTH_SHORT).show(); //toast will print that you logged in successful
                        startActivity(intent); // the intent will start and redirect to HomeActivity
                    }

                    if (!UsernameValue.equals(RegisteredUsername) && !PasswordValue.equals(RegisteredPassword)){ //while does not match username and password
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show(); //it will print out of the toast invalid credentials
                    }
                    if (!UsernameValue.equals(RegisteredUsername) && PasswordValue.equals(RegisteredPassword)){ //while does not match username and password
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show(); //it will print out of the toast invalid credentials
                    }
                    if (UsernameValue.equals(RegisteredUsername) && !PasswordValue.equals(RegisteredPassword)){ //while does not match username and password
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show(); //it will print out of the toast invalid credentials
                    }
                }
            }
        });
    }
}