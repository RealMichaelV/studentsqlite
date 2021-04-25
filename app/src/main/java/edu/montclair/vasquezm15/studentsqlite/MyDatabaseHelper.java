package edu.montclair.vasquezm15.studentsqlite; //package name
import android.content.ContentValues; // import content values
import android.content.Context; // import context
import android.database.Cursor; // import cursor of the database
import android.database.sqlite.SQLiteDatabase; //import sqlite database
import android.database.sqlite.SQLiteOpenHelper; // sqlite helper imported
import android.widget.Toast; // import toast
import androidx.annotation.Nullable; // import annotation
class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context; //private has context variable name assigned
    private static final String DATABASE_NAME = "students1.db"; //database name
    private static final int DATABASE_VERSION = 1; // database version

    private static final String TABLE_NAME = "my_waitinglist"; // table name
    private static final String COLUMN_ID = "_id"; // id
    private static final String COLUMN_NAME = "student_name"; // student name
    private static final String COLUMN_COURSE = "student_course"; // student course to the waiting list
    private static final String COLUMN_IDENTITY = "student_identify"; // students id number
    private static final String COLUMN_GRADE = "student_grade"; // student grade if he/she is a freshman or sophmore and etc...
    private static final String COLUMN_GRAD = "student_grad"; // student graduation estimation

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //designing the database sql
                COLUMN_NAME + " TEXT, " +
                COLUMN_COURSE + " INTEGER, " +
                COLUMN_IDENTITY + " INTEGER, " +
                COLUMN_GRADE + " INTEGER, " +
                COLUMN_GRAD + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void AddStudent(String name, String course, int identity, String grade, int grad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_COURSE,course);
        cv.put(COLUMN_IDENTITY,identity);
        cv.put(COLUMN_GRADE,grade);
        cv.put(COLUMN_GRAD,grad);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show(); // while adding a new student to the database if will say fail if it didnt go through
        }else {
            Toast.makeText(context, "Added Successfully...", Toast.LENGTH_SHORT).show(); // once the student is added to the waiting list it will say added successfully
        }
    }

    Cursor readAllData(){ // reads the data from the database
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String course, String identity, String grade, String grad){ // update the data once updating the students from the waiting list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_COURSE,course);
        cv.put(COLUMN_IDENTITY,identity);
        cv.put(COLUMN_GRADE,grade);
        cv.put(COLUMN_GRAD,grad);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id}); //upadating to the database
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show(); // if not successfully will say failed
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show(); // once successful updated will toast print that the data was updated successfully
        }

    }

    void deleteOneRow(String row_id){ //delete the data from the database
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show(); // if failed will print
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show(); //if successfully deleted will print
        }
    }

    void deleteAllData(){ //to delete the whole data from the database of the students in the waiting list
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}