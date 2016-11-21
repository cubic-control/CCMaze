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
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	public static int fsm = 1;
	
	public static Main instance;

	public static void main(String[] args) {
		System.out.println("[System]:Initializing Game");
		GameWindow frame = new GameWindow("CCMaze", 960, 720);
		frame.setFullscreen(fsm);
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
