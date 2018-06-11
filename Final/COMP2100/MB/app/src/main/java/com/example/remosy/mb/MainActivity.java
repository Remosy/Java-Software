package com.example.remosy.mb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
erg
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        final TextView answer = (TextView) findViewById(R.id.answer);
        final EditText question = (EditText) findViewById(R.id.question);
        final String database[] = new String[]{"HaHaHa","All Right","WoW...","IDK...","Good for you"};
        Answers answers = findViewById(R.id.ans);
        bt.setOnClickListener(answers);
       /* bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question.length()>1){
                    Random r = new Random();
                    answer.setText(database[r.nextInt(4)]);
                }
            }
        });*/
    }
}
