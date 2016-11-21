package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;

public class MultiplayerState extends GameState {
	
	GameStateButton clientButton;
	GameStateButton serverButton;
	GameStateButton returnButton;
	Mousemanager mm;
	
	private int WIDTH = Main.width / 4 - 100;

	public MultiplayerState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing MultiplayerState");
		mm = new Mousemanager();
		clientButton = new GameStateButton(WIDTH, 200, "Start LAN Server");
		serverButton = new GameStateButton(WIDTH, 300, "Connect to Server");
		returnButton = new GameStateButton(WIDTH, 400, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		clientButton.tick();
		serverButton.tick();
		returnButton.tick();
		
		if(clientButton.isHeldOver()){
			if(clientButton.isPressed()){
			}
		}
		
		if(serverButton.isHeldOver()){
			if(serverButton.isPressed()){
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
		clientButton.render(g);
		serverButton.render(g);
		returnButton.render(g);
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
	}

}
