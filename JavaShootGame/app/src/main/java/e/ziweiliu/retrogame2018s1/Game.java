package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.provider.MediaStore;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Game {

    public Tank tank1;
    public Tank tank2;
    public Blocks blocks;
    public PowerUps powerUps;
    boolean gameEnd = false;
    int powerUpBuff = 0;

    private boolean tank1destroyed;
    private boolean tank2destroyed;

    public Game() {
        tank2 = new Tank((1.0f / 11), (2.0f / 11), Direction.S);
        tank1 = new Tank((1.0f / 11)*10, (float) ((2.0f / 11) + ((1.0f / 11)/1.5 * 10)), Direction.N);
        blocks = Blocks.randomGrid(10,10); //10x10 spaced units
        powerUps = PowerUps.randomGrid(10,10,/*context,*/ blocks); // generates a powerup in random location on grid

        tank1destroyed = false;
        tank2destroyed = false;
    }

    public void draw(Canvas canvas, Paint paint) { //Draw the entire game
       if (!(player2Wins() || player1Wins())) {
           paint.setColor(Color.BLUE);
           tank1.draw(canvas, paint);
           paint.setColor(Color.RED);
           tank2.draw(canvas, paint);
           blocks.draw(canvas, paint);
           powerUps.draw(canvas, paint);
           if (tank1.powerUpBuff != 0) {
               powerUpBuff = tank1.powerUpBuff;
           } else if (tank2.powerUpBuff != 0) {
               powerUpBuff = tank2.powerUpBuff;
           } else {
               powerUpBuff = 0;
           }
           if (powerUps.size() == 0 && powerUpBuff == 0) {
               powerUps = PowerUps.randomGrid(10, 10,/*context,*/ blocks);
           }
       } else { gameEnd = true;
       }
    }

    public void step() { // steps entire game forward
        tank1.ammos.step();
        tank2.ammos.step();
        blocks.blockBreak(tank1.ammos);
        blocks.blockBreak(tank2.ammos);//Remove damaged blocks
        tank1.ammos.ammoBreak(tank2.ammos);
        powerUps.powerUpCollected(tank1);
        powerUps.powerUpCollected(tank2);
        for(Ammo ammo : tank1.ammos) { //Tank1 hits Tank2
            if (tank2.damaged(ammo)) {
                tank2destroyed = true;
            }
        }
        for(Ammo ammo : tank2.ammos) { //Tank2 hits Tank1
            if (tank1.damaged(ammo)) {
                tank1destroyed = true;
            }
        }
    }

    public void fireAmmo (Tank tank) { // makes tank fire ammo, reset tank reload timer
        if (tank.poweredUp || ((tank.ammos.size() < 1) && (tank.reload == 0))) {
            tank.ammos.add(new Ammo(tank.point, tank.direction));
            tank.reload = 20;
        }
    }

    public boolean player1Wins() {
        return tank2destroyed;
    } // detecting win conditions

    public boolean player2Wins() {
        return tank1destroyed;
    }
}
