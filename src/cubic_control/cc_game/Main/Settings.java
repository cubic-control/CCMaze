package cubic_control.cc_game.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Settings {
	public File optionsFile;
	
	//defaults
	public int playerID = 1;
	public boolean isFullscreen = false;
	public int resolutionWidth = 960;
	public int resolutionHeight = 720;
	
	public Settings(){
		optionsFile = new File(System.getenv("APPDATA") + "//CCMaze/options.txt");
		
		if(!optionsFile.exists()){
			if(!createOptions()) throw new RuntimeException("[System][ERROR]:Failed creating Options file!");
		}
		loadOptions();
	}
	
	private void loadOptions() {
		try {
			if(!optionsFile.exists())return;
			
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new FileReader(optionsFile));
			
			for(String s = ""; (s = bufferedReader.readLine()) != null;){
				String split[] = s.split(":");
				
				if(split[0].equals("[playerID]")){
					playerID = Integer.parseInt(split[1]);
				}else if(split[0].equals("[isFullscreen]")){
					isFullscreen = Boolean.parseBoolean(split[1]);
				}else if(split[0].equals("[resolutionWidth]")){
					resolutionWidth = Integer.parseInt(split[1]);
				}else if(split[0].equals("[resolutionHeight]")){
					resolutionHeight = Integer.parseInt(split[1]);
				}
			}
		} catch (Exception e) {
			System.out.println("[System][ERROR]:Failed loading Options file!");
			e.printStackTrace();
		}
	}
	
	private boolean createOptions() {
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(optionsFile));
			printWriter.println("[playerID]:" + playerID);
			printWriter.println("[isFullscreen]:" + isFullscreen);
			printWriter.println("[resolutionWidth]:" + resolutionWidth);
			printWriter.println("[resolutionHeight]:" + resolutionHeight);
			printWriter.close();
			return true;
		} catch (IOException e) {
			System.out.println("[System][ERROR]:Failed saving Options file!");
			e.printStackTrace();
			return false;
		}
	}

}
