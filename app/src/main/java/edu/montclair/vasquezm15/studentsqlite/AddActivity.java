package edu.montclair.vasquezm15.studentsqlite; //package
import androidx.appcompat.app.AppCompatActivity; //components of android
import android.os.Bundle; //bundle
import android.view.View; // views to import
import android.widget.Button; //buttons to import
import android.widget.EditText; // edit text import
public class AddActivity extends AppCompatActivity {

    EditText name_input, course_input, identity_input,grade_input,grad_input; //assigning edit text variables
    Button add_button; // assigning button name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input); //assigning ids to the variable
        course_input = findViewById(R.id.course_input); //assigning ids to the variable
        identity_input = findViewById(R.id.number_input); //assigning ids to the variable
        grade_input = findViewById(R.id.grade_input); //assigning ids to the variable
        grad_input = findViewById(R.id.grad_input); //assigning ids to the variable
        add_button = findViewById(R.id.add_button); //assigning ids to the variable
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // add buttons listens onclick listener
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this); //reads my database of SQLite and able to add new students to the waiting list
                myDB.AddStudent(name_input.getText().toString().trim(), // add first/last to the database
                        course_input.getText().toString().trim(), // course name to be added to the database
                        Integer.valueOf(identity_input.getText().toString().trim()), // student id number to the database
                        grade_input.getText().toString().trim(), // what grade the student is if he/she is freshman and etc...
                        Integer.valueOf(grad_input.getText().toString().trim())); // the student graduation estimation added to the database
            }
        });
    }
}
