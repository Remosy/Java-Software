package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import java.util.Iterator;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Block extends Sprite{

    public static final float blockSize = (1.0f / 25.0f);
    public Point point;
    public int health; // amount of hits before block breaks
    public Block(float x, float y) {
        this.point = new Point(x,y);
        this.health = (int) (1 + Math.random() * 3); // assign random health when instantiated
    }

    public void draw(Canvas canvas, Paint paint) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        float xcoordinate = point.x * width;
        float ycoordinate = point.y * height;
        float radius = width * blockSize;

        paint.setStyle(Paint.Style.FILL); // change block colour depending on health
        if (health == 3) {
            paint.setColor(Color.DKGRAY);
        } else if (health == 2) {
            paint.setColor(Color.GRAY);
        } else if (health == 1) {
            paint.setColor(Color.LTGRAY);
        }

        Rect rec = new Rect((int) (xcoordinate - radius),(int) (ycoordinate - radius),(int) (xcoordinate + radius),(int) (ycoordinate + radius));
        canvas.drawRect(rec, paint);
        paint.setStyle(Paint.Style.STROKE);
    }

    public boolean damaged(Ammos ammos) { // check if block has been damaged by an ammo
        Iterator<Ammo> ammosIterator = ammos.iterator();
        while (ammosIterator.hasNext()) {
            Ammo ammo = ammosIterator.next();
            if (ammo.point.Distance(point) < (0.5f/11)) {
                if (health == 1) {
                    ammosIterator.remove();
                    return true;
                } else {
                    ammosIterator.remove();
                    health -= 1;
                }

            }
        }
        return false;
    }

}
