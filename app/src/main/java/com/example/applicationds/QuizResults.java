package com.example.applicationds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import org.w3c.dom.Text;

public class QuizResults extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        QuizActivity.quizTimer.cancel();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        final AppCompatButton startNewBtn = findViewById(R.id.startquizbtn2);
        final TextView correctAnswers = findViewById(R.id.correctanswers);
        correctAnswers.setVisibility(View.INVISIBLE);

        final int getCorrectAnswers = getIntent().getIntExtra("correct", 0);

        final LottieAnimationView Lottie = findViewById(R.id.resultAnimation) ;
        final int result= Integer.valueOf(String.valueOf(getCorrectAnswers));
//        final int result=8;
        int start=  (10-result)*60;

        if (result == 0){
            Lottie.setAnimation(R.raw.zero);
            final LottieAnimationView sadLottie = findViewById(R.id.animationView4) ;
            sadLottie.setAnimation(R.raw.sadcry);
            sadLottie.setPadding(0,-100,0,0);
        }
        else{
            Lottie.setAnimation(R.raw.counter);
            Lottie.setMinAndMaxFrame(start, start+59);
        }

        Lottie.setRepeatCount(0);
        Lottie.setSpeed(0.5F);



        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this, MainActivity.class));
                finish();
            }
        });

        dbSMQ dbs = new dbSMQ(this);
        dbL dbl = new dbL(this);


        final Button scoreButton = findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res;
                if (MainActivity.selectedTopicName.equals("SMQ")){
                    res= dbs.showDatabase();
                }else {
                    res =dbl.showDatabase();
                }

                if (res.getCount() ==0){
                    Toast.makeText(QuizResults.this, "famchay", Toast.LENGTH_SHORT).show();
                    return;
                }
                int topFive=1;
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext() && topFive<=5  ){
                    buffer.append("═══╰"+topFive+"╮═══\n" +
                            "Name: "+res.getString(1)+"\n" +
                            "Score: "+res.getString(2)+"\n"
                    );
                    topFive++;
                }
                TextView scoreboard = new TextView(QuizResults.this);
                scoreboard.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                scoreboard.setText(buffer.toString());
                scoreboard.setGravity(Gravity.CENTER);
                scoreboard.setTextSize(20);

                TextView title = new TextView(QuizResults.this);
                title.setText("Leaderoard");
                title.setTextColor(getResources().getColor(R.color.gradientMain) );
                title.setGravity(Gravity.CENTER_HORIZONTAL);
                title.setTextColor(getColor(R.color.main));
                title.setTextSize(50);

                AlertDialog.Builder builder= new AlertDialog.Builder( QuizResults.this);
                builder.setCancelable(true);
                builder.setCustomTitle(title);

                builder.setView(scoreboard);

                builder.show();
            }
        });


        final AppCompatImageView saveBtn = findViewById(R.id.saveToDB);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView usertag=findViewById(R.id.username);
                final String username= String.valueOf(usertag.getText());
                String buttonErrorMessage= "Bellehi Ekteb Esmek";
                if (username.length() == 0){
                    Toast.makeText(QuizResults.this, buttonErrorMessage , Toast.LENGTH_SHORT).show();
                }else{
                    if(MainActivity.selectedTopicName.equals("SMQ"))
                        dbs.insertDatabase(username, String.valueOf(result));
                    else
                        dbl.insertDatabase(username, String.valueOf(result));


                    usertag.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    usertag.setText("");

                    saveBtn.setBackgroundResource (R.drawable.save_to_database_button_disabled);
                    saveBtn.setEnabled(false);
                }
            }
        });
    }


}