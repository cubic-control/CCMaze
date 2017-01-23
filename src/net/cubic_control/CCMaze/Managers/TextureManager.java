package net.cubic_control.CCMaze.Managers;

import java.awt.image.BufferedImage;

import cubic_control.GameOperatingSystem.loadImageFrom;

public class TextureManager {
	public static BufferedImage resourceLocation(String category, String textureName){
		BufferedImage image = null;
        try {
        	image = loadImageFrom.LoadImageFrom("/assets/textures/"+category+"/"+textureName+".png");
        }catch (Exception e) {
            e.printStackTrace();
        }
		return image;
	}
}
