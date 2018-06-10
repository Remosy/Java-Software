package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class PowerUp extends Sprite {

    Point point;
    public static final float powerUpSize = (1.0f / 60.0f);
    int cycle = 100;

    PowerUp( float x, float y){
        this.point = new Point(x,y);
    }

    public void draw(Canvas canvas, Paint paint) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        float xc = point.x * width;
        float yc = point.y * height;
        float r = width * powerUpSize;
        float radius = r + (Math.abs(cycle)*(1.0f/5f));

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(125 + (int) Math.abs(cycle * 1.30), 0, 255));
        canvas.drawCircle(xc, yc, radius, paint);
        if (cycle < -99) { // cycle used for colour and size animation
            cycle = 100;
        } else {
            cycle -= 1;
        }
    }

    public boolean collected(Tank tank) { // detect whether tank has collided with powerup
            if (tank.point.Distance(point) < (0.5f/11)) {
                tank.poweredUp = true;
                tank.powerUpBuff = 320;
                return true;
            } else {
                return false;
            }
        }
}
