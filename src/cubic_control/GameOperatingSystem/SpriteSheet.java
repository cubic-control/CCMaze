package cubic_control.GameOperatingSystem;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage spriteSheet;

	public SpriteSheet() {
		
	}
	
	public void setSpritesheet(BufferedImage spritesheet) {
		this.spriteSheet = spritesheet;
	}
	
	public BufferedImage getTile(int xTile, int yTile, int width, int height){
		BufferedImage sprite = spriteSheet.getSubimage(xTile, yTile, width, height);
		return sprite;
	}

}
