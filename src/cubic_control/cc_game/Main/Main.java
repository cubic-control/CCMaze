package cubic_control.cc_game.Main;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import cubic_control.GameOperatingSystem.GameWindow;
import cubic_control.cc_game.GameLoop.GameLoop;
import cubic_control.cc_game.Managers.InputManager;
import cubic_control.cc_game.Managers.Mousemanager;

public class Main {
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width;
	public static int height;
	
	public static Main instance;
	public static Settings settings;

	public static void main(String[] args) {
		System.out.println("[System][INFO]:Initializing Game");
		Directory.createDirectories();
		settings = new Settings();
		GameWindow frame = new GameWindow("CCMaze", settings.resolutionWidth, settings.resolutionHeight);
		if(settings.isFullscreen){
			frame.setFullscreen(1);
			width = gd.getDisplayMode().getWidth();
			height = gd.getDisplayMode().getHeight();
		}else{
			frame.setFullscreen(0);
			width = settings.resolutionWidth;
			height = settings.resolutionHeight;
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(""), new Point(0, 0), "Cursor");
		frame.setCursor(cursor);
		
		frame.addMouseListener(new Mousemanager());
		frame.addMouseMotionListener(new Mousemanager());
		frame.addMouseWheelListener(new Mousemanager());
		
		frame.add(new GameLoop(width, height));
		frame.addKeyListener(new InputManager());
		frame.setVisible(true);
	}

}
