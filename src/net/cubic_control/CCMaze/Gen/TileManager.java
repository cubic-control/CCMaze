package net.cubic_control.CCMaze.Gen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import net.cubic_control.CCMaze.Entity.EntityPlayerSP;
import net.cubic_control.CCMaze.Gen.Block;

public class TileManager {
    public static ArrayList<Block> blocks = new ArrayList<Block>();

    public void tick(double deltaTime) {
        for (Block block : blocks) {
            block.tick(deltaTime);
            if (EntityPlayerSP.render.intersects((Rectangle)block)) {
                block.setAlive(true);
                continue;
            }//else if(Entity.render.intersects((Rectangle)block)) {
            	//block.setAlive(true);
            //}
            block.setAlive(false);
        }
    }

    public void render(Graphics2D g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }
}