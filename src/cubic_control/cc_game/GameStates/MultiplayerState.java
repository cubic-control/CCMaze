package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;
import cubic_control.cc_game.Network.Client;
import cubic_control.cc_game.Network.Server;

public class MultiplayerState extends GameState {
	
	GameStateButton clientButton;
	GameStateButton serverButton;
	GameStateButton returnButton;
	Mousemanager mm;
	
	private static Client socketClient;
	private static Server socketServer;
	
	private int WIDTH = Main.width / 4 - 100;

	public MultiplayerState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing MultiplayerState");
		mm = new Mousemanager();
		clientButton = new GameStateButton(WIDTH, 200, "Client");
		serverButton = new GameStateButton(WIDTH, 300, "Server");
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
				socketClient = new Client(Main.instance, "localhost");
				socketClient.start();
				socketClient.sendData("ping".getBytes());
			}
		}
		
		if(serverButton.isHeldOver()){
			if(serverButton.isPressed()){
				socketServer = new Server(Main.instance);
				socketServer.start();
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
