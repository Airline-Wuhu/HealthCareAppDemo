package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2,ed3;
    TextView tv;
    private DatePickerDialog DPD;
    private TimePickerDialog TPD;
    private Button dateButton, timeButton, appointButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.headerAppointment);
        ed1 = findViewById(R.id.nameAppoint);
        ed2 = findViewById(R.id.appointAddress);
        ed3 = findViewById(R.id.appointmentPhone);

        dateButton = findViewById(R.id.selectDateAppoint);
        timeButton = findViewById(R.id.selectTimeAppoint);
        appointButton = findViewById(R.id.appointButton);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String name = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String phone = intent.getStringExtra("text4");

        tv.setText(title);
        ed1.setText(name);
        ed2.setText(address);
        ed3.setText(phone);

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DPD.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPD.show();
            }
        });

        appointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared_prefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = shared_prefs.getString("username", "").toString();
                Database healthcare = new Database(getApplicationContext(), "healthcare", null, 1);
                if (healthcare.checkAppointment(username, title + "=>" + name, address, phone, dateButton.getText().toString(), timeButton.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Appointment Already Created", Toast.LENGTH_SHORT).show();
                } else {
                    healthcare.createAppointment(username, title + "=>" + name, address, phone, dateButton.getText().toString(), timeButton.getText().toString());
                    Toast.makeText(getApplicationContext(), "Appointment Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        DPD = new DatePickerDialog(this, style, dateSetListener, year, month,day);
        DPD.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);

    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeButton.setText(hourOfDay + ":" + minute);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int hr = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        TPD = new TimePickerDialog(this, style, timeSetListener, hr, min, true);

    }


}