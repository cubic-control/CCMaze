package cubic_control.cc_game.Gen;

import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.cc_game.Gen.Block;
import cubic_control.resources.assets.Assets;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Block extends Rectangle {
	
    Vector2F pos = new Vector2F();
    private int BlockSize = 48;
    private BlockType blocktype;
    private BufferedImage block;
    private boolean isSolid;
    private boolean isAlive;

    public Block(Vector2F pos, BlockType blocktype) {
        this.pos = pos;
        this.isAlive = true;
        this.blocktype = blocktype;
        this.init();
    }

    public Block isSolid(boolean isSolid) {
        this.isSolid = isSolid;
        return this;
    }
	
	public void init(){
		switch(blocktype){
		//Layer 1
		case STONE:
			block = Assets.Stone;
			break;
		case DIRT_1:
			block = Assets.dirt_1;
			break;
		case GRASS_1:
			block = Assets.grass_1;
			break;
		case OAK_PLANKS:
			block = Assets.oak_planks;
			break;
		case SNOW:
			block = Assets.snow;
			break;
		//Layer 2
		case STONE_WALL:
			block = Assets.Stone_Wall;
			break;
		case WATER:
			block = Assets.water;
			break;
		case STONE_BRICKS:
			block = Assets.Stone_Bricks;
			break;
		case OAK_LOG:
			block = Assets.Oak_Log;
			break;
		}
	}
	
	public void tick(double deltaTime) {
        if (this.isAlive) {
            this.setBounds((int)this.pos.xPos, (int)this.pos.yPos, this.BlockSize, this.BlockSize);
        }
    }

    public void render(Graphics2D g) {
        if (this.isAlive) {
            g.drawImage(this.block, (int)this.pos.getWorldLocation().xPos, (int)this.pos.getWorldLocation().yPos, this.BlockSize, this.BlockSize, null);
            if (this.isSolid) {
                g.drawRect((int)this.pos.getWorldLocation().xPos, (int)this.pos.getWorldLocation().yPos, this.BlockSize, this.BlockSize);
            }
        }
    }
	
	public enum BlockType{
		//Layer 1
		STONE,
		DIRT_1,
		GRASS_1,
		OAK_PLANKS,
		SNOW,
		//Layer 2
		STONE_WALL,
		WATER,
		STONE_BRICKS,
		OAK_LOG,
	}

	public boolean isSolid() {
        return this.isSolid;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
