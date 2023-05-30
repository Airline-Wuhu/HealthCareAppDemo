package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthArticlesActivity extends AppCompatActivity {
    private String[][] articles = {
            {"Article 1 : blabla", "", "", "", "details"},
            {"Article 2 : blabla", "", "", "","details"},
            {"Article 3 : blabla", "", "", "", "details"},
            {"Article 4 : blabla", "", "", "","details"},
            {"Article 5 : blabla", "", "", "","details"}
    };
    private int[] images = {
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health3,
        R.drawable.health5
    };
    Map<String, String> item;
    List list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        lst = findViewById(R.id.ListViewArticle);
        btnBack = findViewById(R.id.buttonBackArticle);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        ArrayList list = new ArrayList();
        for (int i = 0; i < articles.length;i++) {
            item = new HashMap<>();
            item.put("line1", articles[i][0]);
            item.put("line2", articles[i][1]);
            item.put("line3", articles[i][2]);
            item.put("line4", articles[i][3]);
            item.put("line5", "Links: " + articles[i][4]);
            list.add(item);

        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1", "line2", "line3", "line4", "line5"}, new int[] {R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        lst.setAdapter(simpleAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticleDetails.class);
                it.putExtra("title", articles[position][0]);
                it.putExtra("image", images[position]);
                startActivity(it);
            }
        });
    }
}