package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetails extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);

        textView = findViewById(R.id.ArticleTitleDetails);
        imageView = findViewById(R.id.imageArticleDetails);
        back = findViewById(R.id.buttonBackArticleDetails);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("title"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("image");
            imageView.setImageResource(resId);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleDetails.this, HealthArticlesActivity.class));
            }
        });
    }
}