package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Tank extends Sprite {

    public static final float tankWidth = (1.0f / 25.0f);
    public Direction direction;
    public Ammos ammos = new Ammos(); // list of ammo generated from this tank
    public boolean poweredUp = false;
    int cycle = 100;
    int reload = 0;
    int powerUpBuff = 0;

    public Tank(float x, float y, Direction d) { point = new Point(x, y);
    direction = d;}

    public void draw(Canvas canvas, Paint paint) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        float xcoordinate = point.x * width;
        float ycoordinate = point.y * height;
        float radius = width * tankWidth / 3 * 2;

        if (poweredUp) {
            paint.setColor(Color.rgb(125 + (int) Math.abs(cycle * 1.30), 0, 255));
        }

        canvas.drawLine(xcoordinate + radius, ycoordinate - radius, xcoordinate + radius, ycoordinate + radius, paint);
        canvas.drawLine(xcoordinate - radius, ycoordinate - radius, xcoordinate - radius, ycoordinate + radius, paint);
        canvas.drawLine(xcoordinate + radius, ycoordinate + radius, xcoordinate - radius, ycoordinate + radius, paint);
        canvas.drawLine(xcoordinate - radius, ycoordinate - radius, xcoordinate + radius, ycoordinate - radius, paint);

        paint.setStyle(Paint.Style.FILL);
        Path turret = new Path();
        Path track1 = new Path();
        Path track2 = new Path();
        Path top = new Path();

        switch (direction) { // change tank direction depending on Direction enum
            case N:

                    turret.moveTo(xcoordinate - radius/8, ycoordinate);
                    turret.lineTo(xcoordinate + radius/8, ycoordinate);
                    turret.lineTo(xcoordinate + radius/8, ycoordinate - radius * 3 / 2);
                    turret.lineTo(xcoordinate - radius/8, ycoordinate - radius * 3 / 2);
                    turret.lineTo(xcoordinate - radius/8, ycoordinate);

                    track1.moveTo(xcoordinate - radius - radius/8, ycoordinate - radius);
                    track1.lineTo(xcoordinate - radius - radius/3, ycoordinate - radius);
                    track1.lineTo(xcoordinate - radius - radius/3, ycoordinate + radius);
                    track1.lineTo(xcoordinate - radius - radius/8, ycoordinate + radius);
                    track1.lineTo(xcoordinate - radius - radius/8, ycoordinate - radius);

                    track2.moveTo(xcoordinate + radius + radius/8, ycoordinate - radius);
                    track2.lineTo(xcoordinate + radius + radius/3, ycoordinate - radius);
                    track2.lineTo(xcoordinate + radius + radius/3, ycoordinate + radius);
                    track2.lineTo(xcoordinate + radius + radius/8, ycoordinate + radius);
                    track2.lineTo(xcoordinate + radius + radius/8, ycoordinate - radius);

                    top.moveTo(xcoordinate - radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate - radius/2);

                    break;

            case S:

                    turret.moveTo(xcoordinate - radius/8, ycoordinate);
                    turret.lineTo(xcoordinate + radius/8, ycoordinate);
                    turret.lineTo(xcoordinate + radius/8, ycoordinate + radius * 3 / 2);
                    turret.lineTo(xcoordinate - radius/8, ycoordinate + radius * 3 / 2);
                    turret.lineTo(xcoordinate - radius/8, ycoordinate);

                    track1.moveTo(xcoordinate - radius - radius/8, ycoordinate - radius);
                    track1.lineTo(xcoordinate - radius - radius/3, ycoordinate - radius);
                    track1.lineTo(xcoordinate - radius - radius/3, ycoordinate + radius);
                    track1.lineTo(xcoordinate - radius - radius/8, ycoordinate + radius);
                    track1.lineTo(xcoordinate - radius - radius/8, ycoordinate - radius);

                    track2.moveTo(xcoordinate + radius + radius/8, ycoordinate - radius);
                    track2.lineTo(xcoordinate + radius + radius/3, ycoordinate - radius);
                    track2.lineTo(xcoordinate + radius + radius/3, ycoordinate + radius);
                    track2.lineTo(xcoordinate + radius + radius/8, ycoordinate + radius);
                    track2.lineTo(xcoordinate + radius + radius/8, ycoordinate - radius);

                    top.moveTo(xcoordinate - radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate - radius/2);

                    break;

            case E:

                    turret.moveTo(xcoordinate, ycoordinate - radius/8);
                    turret.lineTo(xcoordinate, ycoordinate + radius/8);
                    turret.lineTo(xcoordinate + radius * 3 / 2, ycoordinate + radius/8);
                    turret.lineTo(xcoordinate + radius * 3 / 2, ycoordinate - radius/8);
                    turret.lineTo(xcoordinate, ycoordinate - radius/8);

                    track1.moveTo(xcoordinate - radius, ycoordinate - radius - radius/8);
                    track1.lineTo(xcoordinate - radius, ycoordinate - radius - radius/3);
                    track1.lineTo(xcoordinate + radius, ycoordinate - radius - radius/3);
                    track1.lineTo(xcoordinate + radius, ycoordinate - radius - radius/8);
                    track1.lineTo(xcoordinate - radius, ycoordinate - radius - radius/8);

                    track2.moveTo(xcoordinate - radius, ycoordinate + radius + radius/8);
                    track2.lineTo(xcoordinate - radius, ycoordinate + radius + radius/3);
                    track2.lineTo(xcoordinate + radius, ycoordinate + radius + radius/3);
                    track2.lineTo(xcoordinate + radius, ycoordinate + radius + radius/8);
                    track2.lineTo(xcoordinate - radius, ycoordinate + radius + radius/8);

                    top.moveTo(xcoordinate - radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate - radius/2);

                    break;

            case W:

                    turret.moveTo(xcoordinate, ycoordinate - radius/8);
                    turret.lineTo(xcoordinate, ycoordinate + radius/8);
                    turret.lineTo(xcoordinate - radius * 3 / 2, ycoordinate + radius/8);
                    turret.lineTo(xcoordinate - radius * 3 / 2, ycoordinate - radius/8);
                    turret.lineTo(xcoordinate, ycoordinate - radius/8);

                    track1.moveTo(xcoordinate - radius, ycoordinate - radius - radius/8);
                    track1.lineTo(xcoordinate - radius, ycoordinate - radius - radius/3);
                    track1.lineTo(xcoordinate + radius, ycoordinate - radius - radius/3);
                    track1.lineTo(xcoordinate + radius, ycoordinate - radius - radius/8);
                    track1.lineTo(xcoordinate - radius, ycoordinate - radius - radius/8);

                    track2.moveTo(xcoordinate - radius, ycoordinate + radius + radius/8);
                    track2.lineTo(xcoordinate - radius, ycoordinate + radius + radius/3);
                    track2.lineTo(xcoordinate + radius, ycoordinate + radius + radius/3);
                    track2.lineTo(xcoordinate + radius, ycoordinate + radius + radius/8);
                    track2.lineTo(xcoordinate - radius, ycoordinate + radius + radius/8);

                    top.moveTo(xcoordinate - radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate + radius/2);
                    top.lineTo(xcoordinate + radius/2, ycoordinate - radius/2);
                    top.lineTo(xcoordinate - radius/2, ycoordinate - radius/2);

                    break;
        }
        canvas.drawPath(turret,paint);
        canvas.drawPath(track1,paint);
        canvas.drawPath(track2,paint);
        canvas.drawPath(top,paint);
        paint.setStyle(Paint.Style.STROKE);
        if (cycle < -99) { // cycle for animation when tank collects powerup
            cycle = 100;
        } else {
            cycle -= 1;
        }
        if (reload > 0) { // timer for reload to limit fire rate
            reload -=1;
        }
        if (powerUpBuff > 0) { // timer for powerup duration
            powerUpBuff -=1;
        }
        if (powerUpBuff == 0) { // if timer is up, no longer powered up
            poweredUp = false;
        }

        ammos.draw(canvas,paint);
        ammos.step();
    }

    public boolean damaged (Ammo ammo) { // detects collision with ammo
        if (ammo.point.Distance(point) < tankWidth) {
            return true;
        } else {
            return false;
        }
    }

    public boolean moveCollision (Blocks bs, Point p) { // detects collision with blocks
        for (Block b : bs) {
            if (p.Distance(b.point) < 0.5f/11) {
                return true;
            }
        }
        return false;
    }

    public boolean moveCollision (Tank t, Point p) { // detects collision with tanks
        if (p.Distance(t.point) < 0.5f/11) {
            return true;
        }
        return false;
    }

    public void update(Direction d, Blocks bs, Tank t){ // changes tank's position and direction
        switch (d) {
            case N:
                if (moveCollision(bs, new Point (point.x, (float) (point.y - ((1.0f / 11) / 1.5))))) {
                    direction = Direction.N;
                } else {
                    direction = Direction.N;
                    if (point.y - ((1.0f / 11) / 1.5) > ((1.0f / 11)+(1.0f/11)/1.5)
                            && !moveCollision(bs, new Point (point.x, (float) (point.y - ((1.0f / 11) / 1.5))))
                            && !moveCollision(t,new Point (point.x, (float) (point.y - ((1.0f / 11) / 1.5))))) {
                        point.y -= ((1.0f / 11) / 1.5);
                    }
                }
                break;
            case S:
                if (moveCollision(bs, new Point (point.x, (float) (point.y + ((1.0f / 11) / 1.5))))) {
                    direction = Direction.S;
                } else {
                    direction = Direction.S;
                    if (point.y + ((1.0f / 11) / 1.5) < 9 * (1.0f / 11)
                            && !moveCollision(bs, new Point (point.x, (float) (point.y + ((1.0f / 11) / 1.5))))
                            && !moveCollision(t, new Point (point.x, (float) (point.y + ((1.0f / 11) / 1.5))))) {
                        point.y += ((1.0f / 11) / 1.5);
                    }
                }
                break;
            case E:
                if (moveCollision(bs, new Point (point.x + (1.0f / 11), point.y))) {
                    direction = Direction.E;
                } else {
                    direction = Direction.E;
                    if (point.x + (1.0f / 11) < (1.0f - (1.0f / 11) / 2.0f)
                            && !moveCollision(bs, new Point (point.x + (1.0f / 11), point.y))
                            && !moveCollision(t, new Point (point.x + (1.0f / 11), point.y))) {
                        point.x += ((1.0f / 11));
                    }
                }
                break;
            case W:
                if (moveCollision(bs, new Point (point.x - (1.0f / 11), point.y))) {
                    direction = Direction.W;
                } else {
                    direction = Direction.W;
                    if (point.x - (1.0f / 11) > (0.5f / 11)
                            && !moveCollision(bs, new Point (point.x - (1.0f / 11), point.y))
                            && !moveCollision(t, new Point (point.x - (1.0f / 11), point.y))) {
                        point.x -= ((1.0f / 11));
                    }
                }
                break;
        }
    }
}