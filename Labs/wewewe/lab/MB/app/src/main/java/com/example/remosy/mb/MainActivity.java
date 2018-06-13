package com.example.remosy.mb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Magic magic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        magic = (Magic)findViewById(R.id.ans);
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(magic);
        final TextView answer = (TextView) findViewById(R.id.textView2);
        final EditText question = (EditText) findViewById(R.id.question);
        final String database[] = new String[]{"HaHaHa","All Right","WoW...","IDK...","Good for you"};

        //bt.setOnClickListener(answers);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView iv = findViewById(R.id.imageView);
                final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotation);
                iv.startAnimation(an);
                an.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //magic.canvas. drawText("iuyoiuy",150,200,magic.gray_paintbrush_stroke);;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        magic.onClick(magic);
                    }
                }, 2000);

               /*if(question.length()>1){
                    Random r = new Random();
                    answer.setText(database[r.nextInt(4)]);
                }
                while (true){
                    magic.invalidate();
                }*/
            }
        });
    }

}
