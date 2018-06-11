package com.example.remosy.mb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by remosy on 13/5/18.
 */

public class Magic2 extends SurfaceView implements View.OnClickListener, Runnable{

    Handler timer;
    Canvas canvas;
    Paint gray_paintbrush_stroke;
    Path square;
    Bitmap ans1;
    Bitmap ans2;
    Bitmap ans3;
    Bitmap ans4;
    int ans1_x = -1;
    int ans1_y = -1;
    int ans2_x = -1;
    int ans2_y = -1;
    int ans3_x = -1;
    int ans3_y = -1;
    int ans4_x = -1;
    int ans4_y = -1;

    int height;
    int width;
    int on_edge = 1;
    Thread thread;
    SurfaceHolder surfaceHolder;
    final String database[] = new String[]{"Yes","All Right","No","IDK"};
    public Magic2(Context context) {
        super(context);
        init();
    }

    public Magic2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        gray_paintbrush_stroke = new Paint();
        gray_paintbrush_stroke.setColor(Color.argb(80, 128, 128, 128));
        gray_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        gray_paintbrush_stroke.setStrokeWidth(30);
        timer = new Handler();
        canvas = new Canvas();
        on_edge = 1;
        surfaceHolder = getHolder();
        ans1 = BitmapFactory.decodeResource(getResources(),R.drawable.button1);
        ans2 = BitmapFactory.decodeResource(getResources(),R.drawable.button2);
        ans3 = BitmapFactory.decodeResource(getResources(),R.drawable.button3);
        ans4 = BitmapFactory.decodeResource(getResources(),R.drawable.button4);

    }

   @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.height = canvas.getHeight();
        this.width = canvas.getWidth();
        if(on_edge==1) {
            if (this.ans2_x == -1) {
                this.ans1_x = width - width / 6 - (ans1.getWidth() / 2);
                this.ans1_y = height / 8 - (ans1.getHeight() / 2);

                this.ans2_x = width - width / 6 - (ans2.getWidth() / 2);
                this.ans2_y = height / 8 - (ans2.getHeight() / 2);

                this.ans3_x = width - width / 6 - (ans3.getWidth() / 2);
                this.ans3_y = height / 8 - (ans3.getHeight() / 2);

                this.ans4_x = width - width / 6 - (ans4.getWidth() / 2);
                this.ans4_y = height / 8 - (ans4.getHeight() / 2);
                on_edge = 0;
            }
            animation1(10);
            animation2(20);
            animation3(5);
            animation4(15);
            drawSquare(width / 6, height / 8, width - width / 6, height - height / 6);
            canvas.drawBitmap(ans1, ans1_x, ans1_y, null);
            canvas.drawBitmap(ans2, ans2_x, ans2_y, null);
            canvas.drawBitmap(ans3, ans3_x, ans3_y, null);
            canvas.drawBitmap(ans4, ans4_x, ans4_y, null);
            System.out.println(on_edge);
            if(on_edge==0) {
                //invalidate();
            }

    }}


    public void pause(){
        on_edge = 0;
        while(true){
            try {
                thread.join();
                break;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        thread=null;
    }

    public void resume(){
        on_edge = 1;
        thread = new Thread(this);
        thread.start();
    }

    private void drawSquare(int x1, int y1, int x2, int y2){
        square = new Path();
        square.moveTo(x1,y1);
        square.lineTo(x2,y1);
        square.moveTo(x2,y1);
        square.lineTo(x2,y2);
        square.moveTo(x2,y2);
        square.lineTo(x1,y2);
        square.moveTo(x1,y2);
        square.lineTo(x1,y1);

        this.canvas.drawPath(square,gray_paintbrush_stroke);
    }

    private void animation1(int step){

        if((ans1_y <height-height/6-(ans1.getHeight()/2)) && (ans1_x ==width-width/6-(ans1.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goDown");
            ans1_y += step;
        }
        //System.out.println(ans1_y);
        //System.out.println(height-height/6-(ans1.getHeight()/2));
        if((ans1_y-6 == height-height/6-(ans1.getHeight()/2)) && (ans1_x > width/6-(ans1.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goLeft");
            ans1_x -=step;
        }

        if((ans1_y > height/8-(ans1.getHeight()/2)) && (ans1_x == width/6-(ans1.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goUps");
            ans1_y -= step;
        }

        if((ans1_y == height/8-(ans1.getHeight()/2)) && (ans1_x <width-width/6-(ans1.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@x_goRight");
            ans1_x += step;
        }

    }
    private void animation2(int step){

        //System.out.println(ans1_y);
        //System.out.println(height-height/6-(ans1.getHeight()/2));
        if((ans2_y <height-height/6-(ans2.getHeight()/2)) && (ans2_x ==width-width/6-(ans2.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goDown");
            ans2_y += step;
        }
        //System.out.println(ans2_y);
        //System.out.println(height-height/6-(ans2.getHeight()/2));
        if((ans2_y-16 == height-height/6-(ans2.getHeight()/2)) && (ans2_x > width/6-(ans2.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goLeft");
            ans2_x -=step;
        }

        if((ans2_y > height/8-(ans2.getHeight()/2)) && (ans2_x == width/6-(ans2.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goUps");
            ans2_y -= step;
        }

        if((ans2_y == height/8-(ans3.getHeight()/2)) && (ans2_x <width-width/6-(ans2.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@x_goRight");
            ans2_x += step;
        }

    }

    private void animation3(int step){

        if((ans3_y <height-height/6-(ans3.getHeight()/2)) && (ans3_x ==width-width/6-(ans3.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goDown");
            ans3_y += step;
        }
        //System.out.println(ans3_y);
        //System.out.println(height-height/6-(ans3.getHeight()/2));
        if((ans3_y-1 == height-height/6-(ans3.getHeight()/2)) && (ans3_x > width/6-(ans3.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goLeft");
            ans3_x -=step;
        }

        if((ans3_y > height/8-(ans3.getHeight()/2)) && (ans3_x == width/6-(ans3.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goUps");
            ans3_y -= step;
        }

        if((ans3_y == height/8-(ans3.getHeight()/2)) && (ans3_x <width-width/6-(ans3.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@x_goRight");
            ans3_x += step;
        }

    }

    private void animation4(int step){

        if((ans4_y <height-height/6-(ans4.getHeight()/2)) && (ans4_x ==width-width/6-(ans4.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goDown");
            ans4_y += step;
        }
        //System.out.println(ans4_y);
        //System.out.println(height-height/6-(ans4.getHeight()/2));
        if((ans4_y-6 == height-height/6-(ans4.getHeight()/2)) && (ans4_x > width/6-(ans4.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goLeft");
            ans4_x -=step;
        }

        if((ans4_y > height/8-(ans4.getHeight()/2)) && (ans4_x == width/6-(ans4.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@y_goUps");
            ans4_y -= step;
        }

        if((ans4_y == height/8-(ans4.getHeight()/2)) && (ans4_x <width-width/6-(ans4.getWidth()/2))){
            //System.out.println("@@@@@@@@@@@@@@@x_goRight");
            ans4_x += step;
        }

    }

    @Override
    public void onClick(View v) {
      /*  if(on_edge == 1){
            on_edge=0;
            setWillNotDraw(false);
            postInvalidate();
        }
        EditText question = (EditText) getRootView().findViewById(R.id.question);
        if(question.length()>1){
            on_edge = 1;
            System.out.println("@@@@@@@@@@@@@");
            TextView textView = (TextView) getRootView().findViewById(R.id.textView2);
            Random r = new Random();
           textView.setText(database[r.nextInt(3)]);
        }*/
    }


    @Override
    public void run() {

    }
}
