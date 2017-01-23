package cubic_control.GameOperatingSystem;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	boolean fse = false;
	int fsm;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public GameWindow(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
	}
	
	private void setfullscreen(){
		switch(fsm){
		case 0:
			setUndecorated(false);
			System.out.println("[System][INFO]:Windowed");
			break;
		case 1:
			setUndecorated(true);
			System.out.println("[System][INFO]:Windowed Borderless");
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			break;
		case 2:
			setUndecorated(true);
			System.out.println("[System][INFO]:Fullscreen");
			device.setFullScreenWindow(this);
			break;
		}
	}
	
	public void setFullscreen(int fsnm){
		fse = true;
		if(fsm <= 2){
			this.fsm = fsnm;
			setfullscreen();
		}else{
			System.err.println("Error "+fsnm+" is not Supported!");
		}
	}
}
