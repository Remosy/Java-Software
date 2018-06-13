package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Ammos extends ArrayList<Ammo> {

    public static final float ammoSpeed = 0.006f; //Increments per step

    public void step() { // moves ammo depending on direction
        for (Ammo ammo : this) {
            switch (ammo.direction) {
                case N:
                    ammo.point.y -= ammoSpeed/1.5;//(1.0f / 11) / 1.5;
                    break;
                case S:
                    ammo.point.y += ammoSpeed/1.5;//(1.0f / 11) / 1.5;
                    break;
                case E:
                    ammo.point.x += ammoSpeed;//(1.0f / 11);
                    break;
                case W:
                    ammo.point.x -= ammoSpeed;//(1.0f / 11);
                    break;
            }
        }
        Iterator<Ammo> ammoIterator = this.iterator();
        while (ammoIterator.hasNext()) {
            Ammo ammo = ammoIterator.next();
            if (((ammo.point.y) < ((1.0f/11)+(1.0f/11)/1.5)) // check if ammo has crossed edge
                    || ((ammo.point.y) > (9*(1.0f/11))) // The bottom border limit
                    || ((ammo.point.x) > (1.0f - (1.0f/11)/2.0f))
                    || ((ammo.point.x) < (0.5f/11))) {
                ammoIterator.remove();
            }
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Ammo ammo : this) ammo.draw(canvas, paint);
    }

    public void ammoBreak(Ammos ammos) { // check if the ammo in this Ammos object has collided with ammo in another Ammos object
        Iterator<Ammo> ammoIterator = this.iterator();
        while (ammoIterator.hasNext()) {
            Ammo ammo = ammoIterator.next();
            if (ammo.damaged(ammos)) ammoIterator.remove();
        }
    }

}
