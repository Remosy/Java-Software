package com.example.remosy.mb;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by remosy on 9/5/18.
 */

class Answers extends View implements View.OnClickListener{
    final EditText question = (EditText) findViewById(R.id.question);
    final String database[] = new String[]{"HaHaHa","All Right","WoW...","IDK...","Good for you"};
    final TextView answer = (TextView) findViewById(R.id.answer);
    public Answers(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onClick(View v) {
        if(question.length()>1){
            Random r = new Random();
            answer.setText(database[r.nextInt(4)]);
        }
    }
}
