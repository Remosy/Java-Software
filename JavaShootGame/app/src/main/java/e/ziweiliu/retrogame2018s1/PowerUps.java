package e.ziweiliu.retrogame2018s1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class PowerUps extends ArrayList<PowerUp> {

    public static PowerUps randomGrid(int columns, int rows, Blocks blocks) { // place powerup in random location on map

        Random random = new Random();
        PowerUps powerUps = new PowerUps();
        float margin = 1.0f / (columns + 1);

        float x,y;
        while (powerUps.size() < 1) {
            y = (float) ((2 * margin) + (margin / 1.5) + ((margin / 1.5) * random.nextInt(8)));
            x = ((2 * margin) + (margin * random.nextInt(7)));
            for (Block block : blocks) {
                if (block.point.Distance(new Point(x, y)) > margin/2) {
                    powerUps.add(new PowerUp(x, y)); // Power up will appear in the center 3x4 grid area
                }
            }
        }
        return powerUps;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (PowerUp powerUp : this) powerUp.draw(canvas, paint);
    }

    public void powerUpCollected(Tank tank) { //Removes powerUp when it is collected
        Iterator<PowerUp> powerUpIterator = this.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();
            if (powerUp.collected(tank)) powerUpIterator.remove();
        }
    }

}
