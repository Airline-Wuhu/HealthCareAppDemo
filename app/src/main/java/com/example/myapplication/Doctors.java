package com.example.myapplication;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Doctors {
    Map<String, List<Doctor>> doctors = new HashMap<>();
    String[] specialty = {"Family Physicians", "Dietitian", "Dentist", "Surgeon", "Cardiologist"};

    public Doctors() {
        for (String s: specialty) {
            doctors.put(s, new LinkedList<>());
            for (int i = 0; i < 5; i++) {
                doctors.get(s).add(new Doctor(s + " doctor: " + String.valueOf(i), s + "address: " + String.valueOf(i), i, String.valueOf(999999999 - i)));
            }
        }
    }

    public Map<String, List<Doctor>> getDoctors() {
        return doctors;
    }
}
