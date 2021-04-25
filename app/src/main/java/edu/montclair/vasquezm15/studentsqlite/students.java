package edu.montclair.vasquezm15.studentsqlite; //package name
import androidx.annotation.Nullable; // import annotation
import androidx.appcompat.app.AlertDialog; //import alertdialog
import androidx.appcompat.app.AppCompatActivity; // app components added
import androidx.recyclerview.widget.LinearLayoutManager; //recyclerview imported
import androidx.recyclerview.widget.RecyclerView; //recyclerview imported
import android.content.DialogInterface; // dialoginteface imported
import android.content.Intent; //intent imported
import android.database.Cursor; //cursor imported within SQLite of the database
import android.os.Bundle; //app bundle imported
import android.view.Menu; //menu view imported
import android.view.MenuInflater; //menuinflater imported
import android.view.MenuItem; //menuitem imported
import android.view.View; //views imported
import android.widget.ImageView; //imageview imported
import android.widget.TextView; //textview imported

import com.google.android.material.floatingactionbutton.FloatingActionButton; //imported floating action button

import java.util.ArrayList;

public class students extends AppCompatActivity {

    RecyclerView recyclerView; //assigning recycler view
    FloatingActionButton add_button; //assigning the floating action button
    ImageView empty; //assigning imageview to empty if no students are added to the waiting list
    TextView none; //assigning TextView

    MyDatabaseHelper myDB; // assigning SQLite database to myDB
    ArrayList<String> student_id, student_name, student_course, student_identity,student_grade,student_grad; //assigning the string in the arraylist
    Connection StudConnection; //assigning variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        recyclerView = findViewById(R.id.recyclerView); //assigning variable to id
        add_button = findViewById(R.id.add_button); //assigning variable to id
        empty = findViewById(R.id.emptystudentsdata); //assigning variable to id
        none = findViewById(R.id.nodatafound); //assigning variable to id
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(students.this, AddActivity.class);
                startActivity(intent); // Onclick listener listens while clicked to pass from students to add students to waiting list activity
            }
        });

        myDB = new MyDatabaseHelper(students.this); // list the store data of students information from the database
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_course = new ArrayList<>();
        student_identity = new ArrayList<>();
        student_grade = new ArrayList<>();
        student_grad = new ArrayList<>();

        storeDataInArrays(); // information that was stored of the array

        StudConnection = new Connection(students.this,this, student_id, student_name,student_course, student_identity,
                student_grade, student_grad);
        recyclerView.setAdapter(StudConnection);
        recyclerView.setLayoutManager(new LinearLayoutManager(students.this)); //recyclerview of the students data will display

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty.setVisibility(View.VISIBLE); //checking if the data is empty of students in the waiting list or not
            none.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){ // while there is data it will display it in the recycler view format
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_course.add(cursor.getString(2));
                student_identity.add(cursor.getString(3));
                student_grade.add(cursor.getString(4));
                student_grad.add(cursor.getString(5));
            }
            empty.setVisibility(View.GONE);
            none.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu); // this menu is used for the top of the application if you want to clear all data from the waiting list
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){ //wipes out all the students from the waiting list
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(students.this); //ask if you want to delete all the data with the students of the waiting list
                myDB.deleteAllData(); //delete all the data from the database within the sqlite
                //Refresh Activity
                Intent intent = new Intent(students.this, students.class); //returns back to the waiting list while all the data is deleted
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { //asks if no to delete all the data within the students in the waiting list
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
