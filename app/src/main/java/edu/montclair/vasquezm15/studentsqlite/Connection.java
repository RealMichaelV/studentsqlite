package edu.montclair.vasquezm15.studentsqlite; //package name
import android.app.Activity; // import activity
import android.content.Context; // import context
import android.content.Intent; // import intent
import android.os.Build; // import build
import android.view.LayoutInflater; // import layout inflater
import android.view.View; // import views
import android.view.ViewGroup; // import view group
import android.view.animation.Animation; // import animation
import android.view.animation.AnimationUtils; // import animation utils
import android.widget.LinearLayout; // import linear layout
import android.widget.TextView;
import androidx.annotation.NonNull; // import annotation
import androidx.annotation.RequiresApi; // import api of annotation
import androidx.recyclerview.widget.RecyclerView; // import recycler view
import java.util.ArrayList; // import array list

public class Connection extends RecyclerView.Adapter<Connection.MyViewHolder> {

    private Context students; //assigned to students
    private Activity activity; // assigned to activity
    private ArrayList student_id, student_name, student_course, student_number,student_grade, student_grad; // assigning arraylist

    Connection(Activity activity, Context students, ArrayList student_id, ArrayList student_name, ArrayList student_course, ArrayList student_number, ArrayList student_grade, ArrayList student_grad){
        this.activity = activity; // assigning variables
        this.students = students; // assigning variables
        this.student_id = student_id; // assigning variables
        this.student_name = student_name; // assigning variables
        this.student_course = student_course; // assigning variables
        this.student_number = student_number; // assigning variables
        this.student_grad = student_grad; // assigning variables
        this.student_grade = student_grade; // assigning variables
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(students); // layout inflater of the context
        View view = inflater.inflate(R.layout.organize_app, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) { //positions the data of the students information within the waiting list
        holder.student_id_txt.setText(String.valueOf(student_id.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.student_course_txt.setText(String.valueOf(student_course.get(position)));
        holder.student_number_txt.setText(String.valueOf(student_number.get(position)));
        holder.student_grade_txt.setText(String.valueOf(student_grad.get(position)));
        holder.student_grad_txt.setText(String.valueOf(student_grade.get(position)));
        holder.layoutofstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // the intent within updating and recycler view to display the data
                Intent intent = new Intent(students, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(student_id.get(position)));
                intent.putExtra("name", String.valueOf(student_name.get(position)));
                intent.putExtra("course", String.valueOf(student_course.get(position)));
                intent.putExtra("identity", String.valueOf(student_number.get(position)));
                intent.putExtra("grade", String.valueOf(student_grade.get(position)));
                intent.putExtra("grad", String.valueOf(student_grad.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_id_txt, student_name_txt,student_course_txt, student_number_txt, student_grade_txt, student_grad_txt; // assigning variables to textview
        LinearLayout layoutofstudents; // assigning variables of linearlayout of the design.

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt); // items view that are in the xml that are assigned to display
            student_name_txt = itemView.findViewById(R.id.student_name_txt); // items view that are in the xml that are assigned to display
            student_course_txt = itemView.findViewById(R.id.student_course); // items view that are in the xml that are assigned to display
            student_number_txt = itemView.findViewById(R.id.student_number_txt); // items view that are in the xml that are assigned to display
            student_grade_txt = itemView.findViewById(R.id.student_grade_txt); // items view that are in the xml that are assigned to display
            student_grad_txt = itemView.findViewById(R.id.student_grad_txt); // items view that are in the xml that are assigned to display
            layoutofstudents = itemView.findViewById(R.id.layoutofstudents); // // items view that are in the xml that are assigned to display of the main layout
            Animation MyAnimation = AnimationUtils.loadAnimation(students, R.anim.myanimation); // animation of display the data of the rececyler view
            layoutofstudents.setAnimation(MyAnimation);
        }

    }

}
