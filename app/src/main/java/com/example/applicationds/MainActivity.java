package com.example.applicationds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String selectedTopicName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout smq = findViewById(R.id.smqlayout);
        final LinearLayout leadership = findViewById(R.id.leadershiplayout);
        final Button startBtn = findViewById(R.id.startquizbtn);
        smq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopicName = "smq";
                smq.setBackgroundResource(R.drawable.round_back_white_stroke10);
                leadership.setBackgroundResource(R.drawable.round_back_white10);

            }
        });
        leadership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopicName = "leadership";
                leadership.setBackgroundResource(R.drawable.round_back_white_stroke10);
                smq.setBackgroundResource(R.drawable.round_back_white10);

            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTopicName.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please select topic", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                    intent.putExtra("selectedTopic", selectedTopicName);
                    startActivity(intent);
                }
            }
        });
    }
}