package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.GameOperatingSystem.GameWindow;
import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;
import cubic_control.cc_game.Player.Player;

public class SettingsState extends GameState {
	
	GameStateButton playerIDButton;
	GameStateButton fullscreenButton;
	GameStateButton controlsButton;
	GameStateButton returnButton;
	Mousemanager mm;
	
	public static String fullscreenText = "";
	
	GameWindow window;
	
	private int WIDTH = Main.width / 4 - 100;

	public SettingsState(GameStateManager gsm) {
		super(gsm);
		if(Main.fsm == 0){
			SettingsState.fullscreenText = "Fullscreen";
		}else if(Main.fsm >= 1){
			SettingsState.fullscreenText = "Windowed";
		}
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing SettingsState");
		mm = new Mousemanager();
		playerIDButton = new GameStateButton(WIDTH, 100, "PlayerID =" + Player.playerID);
		fullscreenButton = new GameStateButton(WIDTH, 200, fullscreenText);
		controlsButton = new GameStateButton(WIDTH, 300, new ControlsState(gsm), gsm, "Controls");
		returnButton = new GameStateButton(WIDTH, 500, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		playerIDButton.tick();
		fullscreenButton.tick();
		controlsButton.tick();
		returnButton.tick();
		
		if(playerIDButton.isHeldOver()){
			if(playerIDButton.isPressed()){
				if(Player.playerID == 1){
					Player.playerID = 2;
				}else if(Player.playerID == 2){
					Player.playerID = 3;
				}else if(Player.playerID >= 3){
					Player.playerID = 1;
				}
				GameStateManager.states.push(new SettingsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
		
		if(fullscreenButton.isHeldOver()){
			if(fullscreenButton.isPressed()){
				if(Main.fsm == 0){
					Main.fsm = 1;
					//window.setFullscreen(1);
				}else if(Main.fsm >= 1){
					Main.fsm = 0;
					//window.setFullscreen(0);
				}
				GameStateManager.states.push(new SettingsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
		
		if(returnButton.isHeldOver()){
			if(returnButton.isPressed()){
				GameStateManager.states.push(new MenuState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		playerIDButton.render(g);
		fullscreenButton.render(g);
		controlsButton.render(g);
		returnButton.render(g);
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
		
		g.drawString("Settings", Main.width / 3 + 155, 100);
	}

}
