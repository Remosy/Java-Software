package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public abstract class Sprite { // Parent class for all in-game entities
    Point point;

    public abstract void draw(Canvas c, Paint p);

}


