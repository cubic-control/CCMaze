package cubic_control.cc_game.Gen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.GameOperatingSystem.loadImageFrom;
import cubic_control.cc_game.Entity.EntityPlayer;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Managers.AudioManager;

public class Map {
    TileManager tiles = new TileManager();
    EntityPlayer player = new EntityPlayer();
    //EntityZombie zombie = new EntityZombie();
    private String mapName;
    private AudioManager audio;
    
    public static Map instance;
    
    public Map(String mapName, String audioName){
    	this.mapName = mapName;
    	
    	audio = new AudioManager(audioName, true);
    }

    public void init() {
    	System.out.println("[System]:Initializing Map");
        this.player.init();
        //this.zombie.init();
        GameStateManager.audio.stop();
        BufferedImage map = null;
        try {
            map = loadImageFrom.LoadImageFrom("/assets/textures/maps/" + (String)mapName);
            audio.play();
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
                        break;
                    }
                    case 1644825: {
                    	TileManager.blocks.add(new Block(new Vector2F((float)(x * 48), (float)(y * 48)), Block.BlockType.DarkStone));
                    	break;
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
        //this.zombie.tick(deltaTime);
    }

    public void render(Graphics2D g) {
        this.tiles.render(g);
        this.player.render(g);
        //this.zombie.render(g);
    }
}