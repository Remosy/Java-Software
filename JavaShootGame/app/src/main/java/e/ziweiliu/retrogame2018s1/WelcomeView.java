package e.ziweiliu.retrogame2018s1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;

import android.os.Handler;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class WelcomeView extends View {

    Handler timer;
    Canvas canvas;
    Paint gray_paintbrush_stroke;
    Path square;
    Bitmap tank;
    int tank_x = -1;
    int tank_y = -1;
    int height;
    int width;
    int on_edge;
    public WelcomeView(Context context) {
        super(context);
        init();
    }

    public WelcomeView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WelcomeView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        gray_paintbrush_stroke = new Paint();
        gray_paintbrush_stroke.setColor(Color.argb(80, 128, 128, 128));
        gray_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        gray_paintbrush_stroke.setStrokeWidth(30);
        timer = new Handler();
        tank = BitmapFactory.decodeResource(getResources(),R.drawable.tank);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.height = canvas.getHeight();
        this.width = canvas.getWidth();
        if(this.tank_x == -1) {
            this.tank_x = width-width/6-(tank.getWidth()/2);
            this.tank_y = height / 2-(tank.getHeight()/2);
            on_edge = 0;
        }
        animation(5);
        drawSquare(width/6,height/2,width-width/6,height-height/6);
        canvas.drawBitmap(tank,tank_x,tank_y,null);
        invalidate();
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

    private void animation(int step){ // tank animation on menu

        if((tank_y <height-height/6-(tank.getHeight()/2)) && (tank_x ==width-width/6-(tank.getWidth()/2))){
            tank_y += step;
        }

        if((tank_y == height-height/6-(tank.getHeight()/2)) && (tank_x > width/6-(tank.getWidth()/2))){
            tank_x-=step;
        }

        if((tank_y > height/2-(tank.getHeight()/2)) && (tank_x == width/6-(tank.getWidth()/2))){
            tank_y -= step;
        }

        if((tank_y == height/2-(tank.getHeight()/2)) && (tank_x <width-width/6-(tank.getWidth()/2))){
            tank_x += step;
        }

    }
}
