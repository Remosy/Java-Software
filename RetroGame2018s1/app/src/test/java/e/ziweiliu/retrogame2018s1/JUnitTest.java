package e.ziweiliu.retrogame2018s1;

import org.junit.Test;

import static org.junit.Assert.*;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class JUnitTest {
    @Test

    public void blocksMargin() throws Exception { // Checks whether the blocks are within the margin of the map
        Blocks blocks = new Blocks();
        blocks.randomGrid(10,10);
        for (Block block : blocks) {
            assertTrue((block.point.x >= 1.0f / 11) && (block.point.x <= (1.0f - 1.0f / 11 / 2.0f)));
            assertTrue((block.point.y >= 2.0f / 11) && (block.point.y <= ((10 - 1.0f) * 1.0f / 11)));
        }
    }

    @Test
    public void ammoMargin() throws Exception { // Checks whether Ammo are removed when reaches outside of the map's margins
        Ammos ammos = new Ammos();
        ammos.add(new Ammo(new Point(20, 20), Direction.N));
        ammos.step();
        assertTrue(ammos.size() == 0);
    }

    @Test
    public void ammoFire() throws Exception { // Checks whether the function that the fire button calls will fire an ammo and checks that the ammo's direction is the same as the tank's direction as it fires the ammo
        Game game = new Game();
        Tank tank = new Tank((1.0f / 11)*10, (float) ((2.0f / 11) + ((1.0f / 11)/1.5 * 10)), Direction.N);
        game.fireAmmo(tank);
        assertTrue(tank.ammos.size() <= 1);
        assertTrue(tank.ammos.get(0).direction == tank.direction);
    }

    @Test
    public void ammoLimit() throws Exception { // Checks whether it is possible to fire twice without collecting the PowerUp
        Game game = new Game();
        Tank tank = new Tank((1.0f / 11)*10, (float) ((2.0f / 11) + ((1.0f / 11)/1.5 * 10)), Direction.N);
        game.fireAmmo(tank);
        game.fireAmmo(tank);
        assertTrue(tank.ammos.size() <= 1);
    }

    @Test
    public void poweredUpAmmoLimit() throws Exception { // Checks whether it is possible to fire more than once when the PowerUp is collected
        Game game = new Game();
        Tank tank = new Tank((1.0f / 11)*10, (float) ((2.0f / 11) + ((1.0f / 11)/1.5 * 10)), Direction.N);
        tank.poweredUp = true; // Sets tank to be poweredUp by collecting PowerUp
        tank.powerUpBuff = 320;
        game.fireAmmo(tank);
        game.fireAmmo(tank);
        assertTrue(tank.ammos.size() > 1);
    }

    @Test
    public void tankUpdate() throws Exception { // Checks if a tank is able to move and change directions
        Tank tank1 = new Tank((1.0f / 11), (2.0f / 11), Direction.S);
        Tank tank2 = new Tank((1.0f / 11)*10, (float) ((2.0f / 11) + ((1.0f / 11)/1.5 * 10)), Direction.N);
        Blocks blocks = new Blocks();
        tank1.update(Direction.S, blocks, tank2);
        assertTrue(tank1.direction == Direction.S);
        assertTrue(tank1.point.x == (1.0f / 11));
        assertTrue(tank1.point.y == ((2.0f / 11) + (1.0f / 11) / 1.5));
        tank1.update(Direction.E, blocks, tank2);
        assertTrue(tank1.direction == Direction.E);
        assertTrue(tank1.point.x == (1.0f / 11) + (1.0f / 11));
        assertTrue(tank1.point.y == ((2.0f / 11) + (1.0f / 11) / 1.5));
        tank1.update(Direction.N, blocks, tank2);
        assertTrue(tank1.direction == Direction.N);
        assertTrue(tank1.point.x == (1.0f / 11) + (1.0f / 11));
        assertTrue(tank1.point.y == (2.0f / 11));
        tank1.update(Direction.W, blocks, tank2);
        assertTrue(tank1.direction == Direction.W);
        assertTrue(tank1.point.x == (1.0f / 11));
        assertTrue(tank1.point.y == (2.0f / 11));
    }

    @Test
    public void blockHealth() throws Exception { // Checks whether an Ammo will colide with a block, reducing it's block health and removing it if the block's health reaches 0
        Blocks blocks = new Blocks();
        Block block = new Block(1, 1);
        block.health = 3;
        blocks.add(block);
        Ammos ammos = new Ammos();
        ammos.add(new Ammo(new Point(1, 1), Direction.N));
        blocks.blockBreak(ammos);
        assertTrue(block.health == 2);
        ammos.add(new Ammo(new Point(1, 1), Direction.N));
        blocks.blockBreak(ammos);
        assertTrue(block.health == 1);
        ammos.add(new Ammo(new Point(1, 1), Direction.N));
        blocks.blockBreak(ammos);
        assertTrue(blocks.size() == 0);
    }

    @Test
    public void tankCollision() throws Exception { // Checks whether the direction changes even though the Tank's path is obstructed. Also Confirms that Tank can't go through Blocks or another Tank as well as off the map's margins
        Tank tank1 = new Tank((1.0f / 11), (float) ((2.0f / 11) + ((1.0f / 11) / 1.5)), Direction.N); // below tank2
        Tank tank2 = new Tank((1.0f / 11), (2.0f / 11), Direction.S); // located top left corner
        Blocks blocks = new Blocks();
        blocks.add(new Block((1.0f / 11) + (1.0f / 11), (2.0f / 11))); // right of tank2
        tank2.update(Direction.E, blocks, tank1);
        assertTrue(tank2.direction == Direction.E);
        assertTrue(tank2.point.x == (1.0f / 11));
        assertTrue(tank2.point.y == ((2.0f / 11)));
        tank2.update(Direction.S, blocks, tank1);
        assertTrue(tank2.direction == Direction.S);
        assertTrue(tank2.point.x == (1.0f / 11));
        assertTrue(tank2.point.y == ((2.0f / 11)));
        tank2.update(Direction.W, blocks, tank1);
        assertTrue(tank2.direction == Direction.W);
        assertTrue(tank2.point.x == (1.0f / 11));
        assertTrue(tank2.point.y == ((2.0f / 11)));
    } // tank1 should not be able to move because it's boxed in by a block and tank2.

    @Test
    public void ammoCollision() throws Exception { // Checks whether ammos will be removed when collided with another ammo
        Ammos ammos1 = new Ammos();
        Ammos ammos2 = new Ammos();
        ammos1.add(new Ammo(new Point((1.0f / 11), (2.0f / 11)), Direction.N));
        ammos2.add(new Ammo(new Point((1.0f / 11), (2.0f / 11)), Direction.N));
        ammos1.ammoBreak(ammos2);
        ammos2.ammoBreak(ammos1);
        ammos1.step();
        ammos2.step();
        assertTrue(ammos1.size() == 0);
        assertTrue(ammos2.size() == 0);
    }

    @Test
    public void tankHit() throws Exception { // Checks whether a tank can sense when it has been hit by an ammo
        Tank tank = new Tank((1.0f / 11), (2.0f / 11), Direction.N);
        tank.ammos.add(new Ammo(tank.point, Direction.N));
        assertTrue(tank.damaged(tank.ammos.get(0)));
    }

    @Test
    public void powerUpCollect() throws Exception { // Checks whether a tank can collect a powerUp
        Tank tank = new Tank((1.0f / 11), (2.0f / 11), Direction.N);
        PowerUps powerUps = new PowerUps();
        PowerUp powerUp = new PowerUp(tank.point.x,tank.point.y);
        powerUps.add(powerUp);
        assertTrue(powerUp.collected(tank));
        powerUps.powerUpCollected(tank);
        assertTrue(powerUps.size() == 0);
    }

    @Test
    public void gameSample() throws Exception { // Integration testing that simulates an entire game
        Game game = new Game();
        Tank tank2 = new Tank((1.0f / 11), (2.0f / 11), Direction.E); // Tank 2 placed at the top left corner
        Tank tank1 = new Tank((1.0f / 11)*10, (2.0f / 11), Direction.W); // Tank 1 placed at the top right corner
        Blocks blocks = new Blocks();
        Block block = new Block((1.0f / 11)*9, (2.0f / 11)); // a Block added infront of Tank 1
        block.health = 2;
        blocks.add(block);
        tank1.update(Direction.W, blocks, tank2); // Tank 1 attempts to move towards Tank 2 but can't because the path is obstructed by a Block
        assertTrue(tank1.direction == Direction.W);
        assertTrue(tank1.point.x == (1.0f / 11)*10);
        assertTrue(tank1.point.y == (2.0f / 11));
        tank1.ammos.add(new Ammo(tank1.point, Direction.W));
        blocks.blockBreak(tank1.ammos);
        assertTrue(block.health == 1); // Block takes a hit, but still remains with a single health point
        tank1.update(Direction.W, blocks, tank2); // Tank 1 attempts to move again towards Tank 2 but can't because the Block in the way has not been destroyed
        assertTrue(tank1.direction == Direction.W);
        assertTrue(tank1.point.x == (1.0f / 11)*10);
        assertTrue(tank1.point.y == (2.0f / 11));
        tank1.ammos.add(new Ammo(tank1.point, Direction.W));
        blocks.blockBreak(tank1.ammos);
        assertTrue(blocks.size() == 0); // Block takes a hit, but it will be removed as it is left with no health points
        tank1.update(Direction.W, blocks, tank2); // Tank 1 attempts to move again towards Tank 2 and should be able to do so without a Block in front of it
        assertTrue(tank1.direction == Direction.W);
        assertTrue(tank1.point.x == (1.0f / 11)*9);
        assertTrue(tank1.point.y == (2.0f / 11));
        tank1.ammos.add(new Ammo(new Point((1.0f / 11)*5, (2.0f / 11)), Direction.W)); // Both Tanks fire an ammo at each other
        tank2.ammos.add(new Ammo(new Point((1.0f / 11)*5, (2.0f / 11)), Direction.W));
        tank1.ammos.ammoBreak(tank2.ammos);
        tank2.ammos.ammoBreak(tank1.ammos);
        tank1.ammos.step();
        tank2.ammos.step();
        assertTrue(tank1.ammos.size() == 0); //Both bullets meet at the middle and destroy each other
        assertTrue(tank2.ammos.size() == 0);
        PowerUps powerUps = new PowerUps();
        PowerUp powerUp = new PowerUp(((1.0f / 11)*8),(2.0f / 11)); //PowerUp located in front of Tank 1
        powerUps.add(powerUp);
        tank1.update(Direction.W, blocks, tank2); // Tank 1 attempts to move once again towards a PowerUp and should be able to do so
        assertTrue(tank1.direction == Direction.W);
        assertTrue(tank1.point.x == (1.0f / 11)*8);
        assertTrue(tank1.point.y == (2.0f / 11));
        assertTrue(powerUp.collected(tank1)); // Tank 1 collects the PowerUp on contact and should PowerUp should be removed from the map
        powerUps.powerUpCollected(tank1);
        assertTrue(powerUps.size() == 0);
        tank1.ammos.add(new Ammo(new Point( (float) ((1.0f / 11) * 0.5), (2.0f / 11)), Direction.E)); // Tank 1 shoots Tank 2
        assertTrue(tank2.damaged(tank1.ammos.get(0))); // Tank 2 gets shot
    }

}