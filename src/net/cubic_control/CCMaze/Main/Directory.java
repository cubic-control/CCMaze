package net.cubic_control.CCMaze.Main;

import java.io.File;

public class Directory {
	public static void createDirectories() {
		try{
			String MAIN = System.getenv("APPDATA") + "//CCMaze";
			String MUSIC =MAIN+"/assets/sounds/music";
			String SFX =MAIN+"/assets/sounds/sfx";
			String MAPS =MAIN+"/assets/textures/maps";
			String SPRITES =MAIN+"/assets/textures/spritesheets";

			boolean success = (new File(MAIN)).mkdir();
			if (success) {
				System.out.println("[System][INFO]:Main directory made");
			}  
			
			success = (new File(MUSIC)).mkdirs();
			success = (new File(SFX)).mkdirs();
			success = (new File(MAPS)).mkdirs();
			success = (new File(SPRITES)).mkdirs();
			if (success) {
				System.out.println("[System][INFO]:Side directories made");
			}

		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
}
