package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.InputManager;
import cubic_control.cc_game.Managers.Mousemanager;

public class ControlsState extends GameState {
	
	GameStateButton upButton;
	GameStateButton downButton;
	GameStateButton leftButton;
	GameStateButton rightButton;
	GameStateButton sprintButton;
	GameStateButton exitButton;
	GameStateButton screenshotButton;
	GameStateButton returnButton;
	Mousemanager mm;
	
	private int WIDTH = Main.width / 4 - 100;

	public ControlsState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing ControlsState");
		mm = new Mousemanager();
		upButton = new GameStateButton(WIDTH, 100, "Up:" + InputManager.keyUP);
		downButton = new GameStateButton(WIDTH, 200, "Down:" + InputManager.keyDOWN);
		leftButton = new GameStateButton(WIDTH, 300, "Left:" + InputManager.keyLEFT);
		rightButton = new GameStateButton(WIDTH, 400, "Right:" + InputManager.keyRIGHT);
		sprintButton = new GameStateButton(WIDTH, 500, "Sprint:" + InputManager.keySPRINT);
		exitButton = new GameStateButton(WIDTH, 600, "Exit:" + InputManager.keyEXIT);
		screenshotButton = new GameStateButton(WIDTH, 700, "Screenshot:" + InputManager.keySCREENSHOT);
		returnButton = new GameStateButton(WIDTH, 800, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		upButton.tick();
		downButton.tick();
		leftButton.tick();
		rightButton.tick();
		sprintButton.tick();
		exitButton.tick();
		screenshotButton.tick();
		returnButton.tick();
		
		if(returnButton.isHeldOver()){
			if(returnButton.isPressed()){
				GameStateManager.states.push(new ControlsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
		
		if(returnButton.isHeldOver()){
			if(returnButton.isPressed()){
				GameStateManager.states.push(new SettingsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		upButton.render(g);
		downButton.render(g);
		leftButton.render(g);
		rightButton.render(g);
		sprintButton.render(g);
		exitButton.render(g);
		screenshotButton.render(g);
		returnButton.render(g);
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
		
		g.drawString("Controls", Main.width / 3 + 155, 100);
	}

}
