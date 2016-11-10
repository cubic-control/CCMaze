package cubic_control.resources.assets;

import java.awt.image.BufferedImage;

import cubic_control.GameOperatingSystem.SpriteSheet;
import cubic_control.GameOperatingSystem.loadImageFrom;

public class Assets {
	SpriteSheet blocks = new SpriteSheet();
	SpriteSheet Players = new SpriteSheet();
	SpriteSheet GUI = new SpriteSheet();
	
	//Layer 1
	public static BufferedImage Stone;
	public static BufferedImage dirt_1;
	public static BufferedImage grass_1;
	public static BufferedImage oak_planks;
	public static BufferedImage snow;
	//Layer 2
	public static BufferedImage Stone_Wall;
	public static BufferedImage water;
	public static BufferedImage Stone_Bricks;
	public static BufferedImage Oak_Log;
	//GUI
		//Mouse
	public static BufferedImage getMouse_pressed;
	public static BufferedImage getMouse_unpressed;
		//Button
	public static BufferedImage getButton_notover;
	public static BufferedImage getButton_heldover;
	//Players
		//Idle
			//Character 1
	public static BufferedImage spr_char1_idle1;
	public static BufferedImage spr_char1_idle2;
			//Character 2
	public static BufferedImage spr_char2_idle1;
	public static BufferedImage spr_char2_idle2;
			//Character 3
	public static BufferedImage spr_char3_idle1;
	public static BufferedImage spr_char3_idle2;
			//Character 4
	public static BufferedImage spr_char4_idle1;
	public static BufferedImage spr_char4_idle2;
			//Character 5
	public static BufferedImage spr_char5_idle1;
	public static BufferedImage spr_char5_idle2;
			//Character 6
	public static BufferedImage spr_char6_idle1;
	public static BufferedImage spr_char6_idle2;
		//Walking Up
			//Character 1
	public static BufferedImage spr_char1_walk_up1;
	public static BufferedImage spr_char1_walk_up2;
	public static BufferedImage spr_char1_walk_up3;
	public static BufferedImage spr_char1_walk_up4;
			//Character 2
	public static BufferedImage spr_char2_walk_up1;
	public static BufferedImage spr_char2_walk_up2;
	public static BufferedImage spr_char2_walk_up3;
	public static BufferedImage spr_char2_walk_up4;
		//Walking Down
			//Character 1
	public static BufferedImage spr_char1_walk_down1;
	public static BufferedImage spr_char1_walk_down2;
	public static BufferedImage spr_char1_walk_down3;
	public static BufferedImage spr_char1_walk_down4;
			//Character 2
	public static BufferedImage spr_char2_walk_down1;
	public static BufferedImage spr_char2_walk_down2;
	public static BufferedImage spr_char2_walk_down3;
	public static BufferedImage spr_char2_walk_down4;
		//Walking Left
			//Character 1
	public static BufferedImage spr_char1_walk_left1;
	public static BufferedImage spr_char1_walk_left2;
	public static BufferedImage spr_char1_walk_left3;
	public static BufferedImage spr_char1_walk_left4;
			//Character 2
	public static BufferedImage spr_char2_walk_left1;
	public static BufferedImage spr_char2_walk_left2;
	public static BufferedImage spr_char2_walk_left3;
	public static BufferedImage spr_char2_walk_left4;
		//Walking Right
			//Character 1
	public static BufferedImage spr_char1_walk_right1;
	public static BufferedImage spr_char1_walk_right2;
	public static BufferedImage spr_char1_walk_right3;
	public static BufferedImage spr_char1_walk_right4;
			//Character 2
	public static BufferedImage spr_char2_walk_right1;
	public static BufferedImage spr_char2_walk_right2;
	public static BufferedImage spr_char2_walk_right3;
	public static BufferedImage spr_char2_walk_right4;
	
	public void init(){
		System.out.println("[System]:Initializing Assets");
		blocks.setSpritesheet(loadImageFrom.LoadImageFrom(Assets.class, "blocks.png"));
		Players.setSpritesheet(loadImageFrom.LoadImageFrom(Assets.class, "Players.png"));
		GUI.setSpritesheet(loadImageFrom.LoadImageFrom(Assets.class, "GUI.png"));
		//Layer 1
		Stone = blocks.getTile(0, 0, 16, 16);
		dirt_1 = blocks.getTile(16, 0, 16, 16);
		grass_1 = blocks.getTile(32, 0, 16, 16);
		oak_planks = blocks.getTile(48, 0, 16, 16);
		snow = blocks.getTile(64, 0, 16, 16);
		//Layer 2
		Stone_Wall = blocks.getTile(0, 16, 16, 16);
		water = blocks.getTile(16, 16, 16, 16);
		Stone_Bricks = blocks.getTile(32, 16, 16, 16);
		Oak_Log = blocks.getTile(48, 16, 16, 16);
		//GUI
			//Mouse
		getMouse_pressed = GUI.getTile(0, 0, 8, 8);
		getMouse_unpressed = GUI.getTile(0, 0, 8, 8);
			//Button
		getButton_notover = GUI.getTile(16, 0, 48, 16);
		getButton_heldover = GUI.getTile(16, 16, 48, 16);
		//Players
			//Idle
				//Character 1
		spr_char1_idle1 = Players.getTile(0, 0, 32, 32);
		spr_char1_idle2 = Players.getTile(32, 0, 32, 32);
				//Character 2
		spr_char2_idle1 = Players.getTile(0, 32, 32, 32);
		spr_char2_idle2 = Players.getTile(32, 32, 32, 32);
				//Character 3
		spr_char3_idle1 = Players.getTile(0, 64, 32, 32);
		spr_char3_idle2 = Players.getTile(32, 64, 32, 32);
				//Character 4
		spr_char4_idle1 = Players.getTile(0, 96, 32, 32);
		spr_char4_idle2 = Players.getTile(32, 96, 32, 32);
				//Character 5
		spr_char5_idle1 = Players.getTile(0, 128, 32, 32);
		spr_char5_idle2 = Players.getTile(32, 128, 32, 32);
				//Character 6
		spr_char6_idle1 = Players.getTile(0, 160, 32, 32);
		spr_char6_idle2 = Players.getTile(32, 160, 32, 32);
			//Walking Up
				//Character 1
		spr_char1_walk_up1 = Players.getTile(64, 0, 32, 32);
		spr_char1_walk_up2 = Players.getTile(96, 0, 32, 32);
		spr_char1_walk_up3 = Players.getTile(128, 0, 32, 32);
		spr_char1_walk_up4 = spr_char1_walk_up2;
				//Character 2
		spr_char2_walk_up1 = Players.getTile(64, 32, 32, 32);
		spr_char2_walk_up2 = Players.getTile(96, 32, 32, 32);
		spr_char2_walk_up3 = Players.getTile(128, 32, 32, 32);
		spr_char2_walk_up4 = spr_char2_walk_up2;
			//Walking Down
				//Character 1
		spr_char1_walk_down1 = Players.getTile(160, 0, 32, 32);
		spr_char1_walk_down2 = Players.getTile(192, 0, 32, 32);
		spr_char1_walk_down3 = Players.getTile(224, 0, 32, 32);
		spr_char1_walk_down4 = spr_char1_walk_down2;
				//Character 2
		spr_char2_walk_down1 = Players.getTile(160, 32, 32, 32);
		spr_char2_walk_down2 = Players.getTile(192, 32, 32, 32);
		spr_char2_walk_down3 = Players.getTile(224, 32, 32, 32);
		spr_char2_walk_down4 = spr_char2_walk_down2;
			//Walking Left
				//Character 1
		spr_char1_walk_left1 = Players.getTile(256, 0, 32, 32);
		spr_char1_walk_left2 = Players.getTile(288, 0, 32, 32);
		spr_char1_walk_left3 = Players.getTile(320, 0, 32, 32);
		spr_char1_walk_left4 = spr_char1_walk_left2;
				//Character 2
		spr_char2_walk_left1 = Players.getTile(256, 32, 32, 32);
		spr_char2_walk_left2 = Players.getTile(288, 32, 32, 32);
		spr_char2_walk_left3 = Players.getTile(320, 32, 32, 32);
		spr_char2_walk_left4 = spr_char2_walk_left2;
			//Walking Right
				//Character 1
		spr_char1_walk_right1 = Players.getTile(352, 0, 32, 32);
		spr_char1_walk_right2 = Players.getTile(384, 0, 32, 32);
		spr_char1_walk_right3 = Players.getTile(416, 0, 32, 32);
		spr_char1_walk_right4 = spr_char1_walk_right2;
				//Character 2
		spr_char2_walk_right1 = Players.getTile(352, 32, 32, 32);
		spr_char2_walk_right2 = Players.getTile(384, 32, 32, 32);
		spr_char2_walk_right3 = Players.getTile(416, 32, 32, 32);
		spr_char2_walk_right4 = spr_char2_walk_right2;
	}
}
