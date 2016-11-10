package cubic_control.cc_game.Gen;

import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.GameOperatingSystem.loadImageFrom;
import cubic_control.cc_game.Gen.Block;
import cubic_control.cc_game.Gen.TileManager;
import cubic_control.cc_game.Player.Player;
import cubic_control.resources.assets.Assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Map {
    TileManager tiles = new TileManager();
    Player player = new Player(this);
    private String mapName;
    
    public static Map instance;
    
    public Map(String mapName){
    	this.mapName = mapName;
    }

    public void init() {
    	System.out.println("[System]:Initializing Map");
        this.player.init();
        BufferedImage map = null;
        try {
            map = loadImageFrom.LoadImageFrom(Assets.class, (String)mapName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int x = 0;
        while (x < 100) {
            int y = 0;
            while (y < 100) {
                int col = map.getRGB(x, y);
                switch (col & 16777215) {
                    case 10066329: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.STONE));
                        break;
                    }
                    case 8355711: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.STONE_WALL).isSolid(true));
                        break;
                    }
                    case 9127187: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.DIRT_1));
                        break;
                    }
                    case 1761306: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.GRASS_1));
                        break;
                    }
                    case 12425571: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.OAK_PLANKS));
                        break;
                    }
                    case 16777215: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.SNOW));
                        break;
                    }
                    case 255: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.WATER).isSolid(true));
                        break;
                    }
                    case 11711154: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.STONE_BRICKS).isSolid(true));
                        break;
                    }
                    case 6697728: {
                        TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.OAK_LOG).isSolid(true));
                    }
                }
                ++y;
            }
            ++x;
        }
    }

    public void tick(double deltaTime) {
        this.tiles.tick(deltaTime);
        this.player.tick(deltaTime);
    }

    public void render(Graphics2D g) {
        this.tiles.render(g);
        this.player.render(g);
    }
}