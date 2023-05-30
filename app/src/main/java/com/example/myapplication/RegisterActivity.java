package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edPassword, edConfirm, edEmail;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.regUsername);
        edPassword = findViewById(R.id.regPassword);
        edConfirm = findViewById(R.id.confirmPassword);
        edEmail = findViewById(R.id.email);
        btn = findViewById(R.id.registerButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String email = edEmail.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || password.length() == 0 || email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "please input the username, password, and email", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(confirm)) {
                    Toast.makeText(getApplicationContext(), "passwords mismatch, please check and re-enter your passwords", Toast.LENGTH_SHORT).show();

                } else if (!checkPassword(password)) {
                    Toast.makeText(getApplicationContext(), "passwords must be more than 8 characters, with number, letter and symbol", Toast.LENGTH_SHORT).show();
                } else {
                    db.register(username, email, password);
                    Toast.makeText(getApplicationContext(), "register success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                }
            }
        });


    }

    public boolean checkPassword(String password) {
        int length = password.length();
        int f1= 0, f2 = 0, f3 = 0;
        if (length < 8) return false;

        for (int i = 0; i < length; i++) {

            char ch = password.charAt(i);
            if (Character.isLetter(ch)) f1 = 1;
            if (Character.isDigit(ch)) f2 = 1;
            if (ch >= 33 && ch <= 46 || ch == 64) f3 = 1;
            if (f1 * f2 * f3 != 0) return true;
        }
        return false;
    }
}