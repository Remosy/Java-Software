package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Ammo extends Sprite{

    public static final float ammoSize = (1.0f / 125.0f); //Radius
    public Direction direction; //Direction the ammo travels in

    public Ammo(Point point, Direction direction) {
        this.point = new Point(point);
        this.direction = direction;
        if (this.direction == Direction.N) {
            this.point.y -= 0.5f/11/1.5;
        } else if (this.direction == Direction.S) {
            this.point.y += 0.5f/11/1.5;
        } else if (this.direction == Direction.E) {
            this.point.x += 0.5f/11;
        } else if (this.direction == Direction.W) {
            this.point.x -= 0.5f/11;
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        float xcoordinate = this.point.x * width;
        float ycoordinate = this.point.y * height;
        float radius = width * ammoSize;

        canvas.drawCircle(xcoordinate, ycoordinate, radius, paint);
    }

    public boolean damaged(ArrayList<Ammo> ammos) { // detects whether this ammo has collided with
        for (Ammo ammo : ammos) {                   // any ammo in the ArrayList
            if (ammo.point.Distance(point) < (0.2f/11)) {
                ammos.remove(ammo);
                return true;
            }
        }
        return false;
    }

}
