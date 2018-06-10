package e.ziweiliu.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Blocks extends ArrayList<Block> {

    public static Blocks randomGrid(int columns, int rows) { // generate a random grid of blocks
        Random random = new Random();                        // inspired by gridAliens() method by
        Blocks blocks = new Blocks();                        // Eric McCreath in SpaceInvaders

        float margin = 1.0f / (columns + 1);

        for (float y = 2*margin; y < (rows - 1.0f) * margin; y += margin/1.5) { //0.5f and margin/1.5
            for (float x = margin; x < (1.0f - margin / 2.0f); x += margin) { //1.0f
                int i = random.nextInt(10);
                if ((x != (1.0f / 11) || y != (2.0f / 11))) {
                    if (x != (1.0f / 11)+ (margin*9) || y != (2.0f / 11) + (margin/1.5 * 10)) {
                        if (i <= 3) {
                            blocks.add(new Block(x, y));
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Block block : this) block.draw(canvas, paint);
    }

    public void blockBreak(Ammos ammos) { //Removes blocks if they have 0 health
        Iterator<Block> blockIterator = this.iterator();
        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            if (block.damaged(ammos)) blockIterator.remove();
        }
    }

}
