package cubic_control.cc_game.Gen;

import cubic_control.cc_game.Gen.Block;
import cubic_control.cc_game.Player.Player;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class TileManager {
    public static ArrayList<Block> blocks = new ArrayList<Block>();

    public void tick(double deltaTime) {
        for (Block block : blocks) {
            block.tick(deltaTime);
            if (Player.render.intersects((Rectangle)block)) {
                block.setAlive(true);
                continue;
            }
            block.setAlive(false);
        }
    }

    public void render(Graphics2D g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }
}