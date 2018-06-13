package com.example.remosy.mb;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by remosy on 13/5/18.
 */

public class Magic extends View implements View.OnClickListener {
    Paint gray_paintbrush_stroke;
    Canvas canvas;
    String S = "Welcome";
    public Magic(Context context) {
        super(context);init();
    }

    public Magic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }

    public void init(){
        gray_paintbrush_stroke = new Paint();
        gray_paintbrush_stroke.setColor(Color.WHITE);
        gray_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        gray_paintbrush_stroke.setStrokeWidth(5);
        gray_paintbrush_stroke.setTextSize(150);
    }

    @Override
    public void onDraw(Canvas canvas){
        this.canvas = canvas;
        canvas.drawText(S, 500, 600, gray_paintbrush_stroke);
        invalidate();
    }

    @Override
    public void onClick(View v) {
        final String database[] = new String[]{"HaHaHa","All Right","WoW...","IDK...","Good for you"};
        final EditText question = (EditText) getRootView().findViewById(R.id.question);
        if(question.length()>1){
            Random r = new Random();
            S = database[r.nextInt(4)];
        }
        //this.canvas.drawText("Dt", 500, 600, gray_paintbrush_stroke);
        //invalidate();
        System.out.println("uyiuyiu");
    }
}
