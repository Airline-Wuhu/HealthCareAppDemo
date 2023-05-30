package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DoctorDetailsActivity extends AppCompatActivity {

    Doctors doctors = new Doctors();
    TextView textView;
    Button bt;
    List<Doctor> doctor_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        textView = findViewById(R.id.DDTitle);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textView.setText(title);

        doctor_details = doctors.getDoctors().get(title);
        ArrayList list = new ArrayList();
        for (Doctor D: doctor_details) {
            Map<String, String>item = new HashMap<>();
            item.put("line1", D.getName());
            item.put("line2", D.getAddress());
            item.put("line3", String.valueOf(D.getExp()));
            item.put("line4", D.getPhone());
            item.put("line5", "");
            list.add(item);

        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1", "line2", "line3", "line4", "line5"}, new int[] {R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        ListView lst = findViewById(R.id.ListViewDD);
        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("tex1", title);
                it.putExtra("text2", doctor_details.get(position).getName());
                it.putExtra("text3", doctor_details.get(position).getAddress());
                it.putExtra("text4", doctor_details.get(position).getPhone());
                startActivity(it);
            }
        });


        bt = findViewById(R.id.buttonDDBack);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });


    }
}