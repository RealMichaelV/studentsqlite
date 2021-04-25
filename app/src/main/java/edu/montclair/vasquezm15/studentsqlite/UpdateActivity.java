package edu.montclair.vasquezm15.studentsqlite; //package name
import androidx.appcompat.app.ActionBar; //import action bar
import androidx.appcompat.app.AlertDialog; // import alert dialog
import androidx.appcompat.app.AppCompatActivity; // import app components
import android.content.DialogInterface; // import dialog interface
import android.os.Bundle; // import app bundle
import android.util.Log; // import app logs
import android.view.View; // import app views
import android.widget.Button; // import buttons
import android.widget.EditText; // import edit text
import android.widget.Toast; // import toast


public class UpdateActivity extends AppCompatActivity {

    EditText name_input,  course_input, number_input, grade_input, grad_input; //assigning edit text to variables
    Button UpdateButton,DeleteButton; // assigning button to a variable

    String id,name,course,identity,grade,grad; // assigning string variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2); //assigning variables to ids
        course_input = findViewById(R.id.course_input2); //assigning variables to ids
        number_input = findViewById(R.id.number_input2); //assigning variables to ids
        grade_input = findViewById(R.id.grade_input2); //assigning variables to ids
        grad_input = findViewById(R.id.grad_input2); //assigning variables to ids
        UpdateButton = findViewById(R.id.UpdateButton); //assigning variables to ids
        DeleteButton = findViewById(R.id.deletebutton); //assigning variables to ids

        //This will call the data from the intent
        getAndSetIntentData();

        //setting the action bar with the title name which is school education
        ActionBar waitlist = getSupportActionBar();
        if (waitlist != null) {
            waitlist.setTitle(name);
        }

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // on click lister while clicked for update within the students of the waiting list
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this); //calling the database to update the new information within the students
                name = name_input.getText().toString().trim();
                course = course_input.getText().toString().trim();
                identity = number_input.getText().toString().trim();
                grade = grad_input.getText().toString().trim();
                grad = grad_input.getText().toString().trim();
                myDB.updateData(id, name, course, identity, grade, grad);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && // this intent will get the data from the other activity
                getIntent().hasExtra("course") && getIntent().hasExtra("identity") &&
        getIntent().hasExtra("grade") && getIntent().hasExtra("grad")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            course = getIntent().getStringExtra("course");
            identity = getIntent().getStringExtra("identity");
            grade = getIntent().getStringExtra("grade");
            grad = getIntent().getStringExtra("grad");




            name_input.setText(name);
            course_input.setText(course);
            number_input.setText(identity);
            grade_input.setText(grade);
            grad_input.setText(grad);
            Log.d("SavedData", name+" "+course+" "+identity+" "+grade+" "+grad);
        }else{
            Toast.makeText(this, "No Students in the Waiting List.", Toast.LENGTH_SHORT).show(); //this will check within the intent if the data is saved or not
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Do you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this); // while you try to edit a students information or update it will have an option if
                // you would like to delete the students information
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // option for no for while deleting students information from the waiting list
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
