package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;

public class MenuState extends GameState{

	GameStateButton playButton;
	GameStateButton multiplayer;
	GameStateButton settingsButton;
	GameStateButton quitButton;
	Mousemanager mm;
	
	private int WIDTH = Main.width / 3 + 120;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing MenuState");
		mm = new Mousemanager();
		playButton = new GameStateButton(WIDTH, 200, new LevelSelectState(gsm), gsm, "Play Game");
		multiplayer = new GameStateButton(WIDTH, 300, new MultiplayerState(gsm), gsm, "Multiplayer");
		settingsButton = new GameStateButton(WIDTH, 400, new SettingsState(gsm), gsm, "Settings");
		quitButton = new GameStateButton(WIDTH, 500, new QuitState(gsm), gsm, "Quit Game");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		playButton.tick();
		multiplayer.tick();
		settingsButton.tick();
		quitButton.tick();
	}

	@Override
	public void render(Graphics2D g) {
		playButton.render(g);
		multiplayer.render(g);
		settingsButton.render(g);
		quitButton.render(g);
		
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
		
		g.drawString("CCMaze", Main.width / 3 + 155, 100);
	}

}
