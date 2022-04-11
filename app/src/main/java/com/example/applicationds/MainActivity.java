package com.example.applicationds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
// TODO:
//  add gradient 50%
//  add app loader
//  add sqlite leadeboards implementation
//  app icon
//  na7i el top bar
//  .
public class MainActivity extends AppCompatActivity {
    public static String selectedTopicName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayoutCompat smq = findViewById(R.id.smqlayout);
        final LinearLayout leadership = findViewById(R.id.leadershiplayout);
        final Button startBtn = findViewById(R.id.startquizbtn);

        selectedTopicName="";
        startBtn.setBackgroundResource(R.drawable.call_to_action_disabled);

        smq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopicName = "SMQ";
                smq.setBackgroundResource(R.drawable.smq_selector_box_selected);
                leadership.setBackgroundResource(R.drawable.leadership_selector_box);

                startBtn.setEnabled(true);
                startBtn.setBackgroundResource(R.drawable.call_to_action);

            }
        });
        leadership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopicName = "Leadership";
                leadership.setBackgroundResource(R.drawable.leadership_selector_box_selected);
                smq.setBackgroundResource(R.drawable.smq_selector_box);

                startBtn.setEnabled(true);
                startBtn.setBackgroundResource(R.drawable.call_to_action);
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