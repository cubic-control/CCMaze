package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.GameOperatingSystem.GameWindow;
import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.AudioManager;
import cubic_control.cc_game.Managers.Mousemanager;

public class SettingsState extends GameState {
	
	GameStateButton playerIDButton;
	GameStateButton fullscreenButton;
	GameStateButton controlsButton;
	GameStateButton muteButton;
	GameStateButton returnButton;
	Mousemanager mm;
	
	GameWindow window;
	
	private int WIDTH = Main.width / 4 - 100;

	public SettingsState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System][INFO]:Initializing SettingsState");
		mm = new Mousemanager();
		playerIDButton = new GameStateButton(WIDTH, 100, "PlayerID =" + Main.settings.playerID);
		fullscreenButton = new GameStateButton(WIDTH, 200, "Fullscreen/Windowed");
		controlsButton = new GameStateButton(WIDTH, 300, new ControlsState(gsm), gsm, "Controls");
		muteButton = new GameStateButton(WIDTH, 400, "Mute Music");
		returnButton = new GameStateButton(WIDTH, 500, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		playerIDButton.tick();
		fullscreenButton.tick();
		controlsButton.tick();
		muteButton.tick();
		returnButton.tick();
		
		if(playerIDButton.isHeldOver()){
			if(playerIDButton.isPressed()){
				if(Main.settings.playerID == 1){
					Main.settings.playerID = 2;
				}else if(Main.settings.playerID == 2){
					Main.settings.playerID = 3;
				}else if(Main.settings.playerID >= 3){
					Main.settings.playerID = 1;
				}
				GameStateManager.states.push(new SettingsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
		
		if(fullscreenButton.isHeldOver()){
			if(fullscreenButton.isPressed()){
				if(Main.settings.isFullscreen){
					Main.settings.isFullscreen = false;
				}else if(!Main.settings.isFullscreen){
					Main.settings.isFullscreen = true;
				}
				GameStateManager.states.push(new SettingsState(gsm));
				GameStateManager.states.peek().init();
			}
		}
		
		if(muteButton.isHeldOver()){
			if(muteButton.isPressed()){
				if(AudioManager.musicOn = true){
					AudioManager.musicOn = false;
					GameStateManager.audio.close();
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
		muteButton.render(g);
		returnButton.render(g);
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
		
		g.drawString("Settings", Main.width / 3 + 155, 100);
	}

}
