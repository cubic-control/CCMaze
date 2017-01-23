package net.cubic_control.CCMaze.GameStates;

import java.awt.Graphics2D;

import net.cubic_control.CCMaze.GameState.GameState;
import net.cubic_control.CCMaze.GameState.GameStateButton;
import net.cubic_control.CCMaze.GameState.GameStateManager;
import net.cubic_control.CCMaze.Main.Main;
import net.cubic_control.CCMaze.Managers.Mousemanager;

public class LevelSelectState extends GameState{
	
	GameStateButton hubButton;
	GameStateButton tutorialButton;
	//CCorp
	GameStateButton level1Button;
	GameStateButton level2Button;
	GameStateButton level3Button;
	GameStateButton level4Button;
	GameStateButton level5Button;
	//DTCorp
	GameStateButton level6Button;
	GameStateButton level7Button;
	GameStateButton level8Button;
	GameStateButton returnButton;
	Mousemanager mm;
	
	private int WIDTH = Main.width / 4 - 100;

	public LevelSelectState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System][INFO]:Initializing LevelSelectState");
		mm = new Mousemanager();
		hubButton = new GameStateButton(WIDTH, 100, new LevelLoader(gsm, "hub"), gsm, "Play Hub");
		tutorialButton = new GameStateButton(WIDTH, 200, new LevelLoader(gsm, "tutorial"), gsm, "Play Tutorial");
		level1Button = new GameStateButton(WIDTH, 300, new LevelLoader(gsm, "level1"), gsm, "Play Level1");
		level2Button = new GameStateButton(WIDTH, 400, new LevelLoader(gsm, "level2"), gsm, "Play Level2");
		level3Button = new GameStateButton(WIDTH, 500, new LevelLoader(gsm, "level3"), gsm, "Play Level3");
		level4Button = new GameStateButton(WIDTH, 600, new LevelLoader(gsm, "level4"), gsm, "Play Level4");
		level5Button = new GameStateButton(WIDTH + 200, 100, new LevelLoader(gsm, "level5"), gsm, "Play Level5");
		level6Button = new GameStateButton(WIDTH + 200, 200, new LevelLoader(gsm, "level6"), gsm, "Play Level6");
		level7Button = new GameStateButton(WIDTH + 200, 300, new LevelLoader(gsm, "level7"), gsm, "Play Level7");
		level8Button = new GameStateButton(WIDTH + 200, 400, new LevelLoader(gsm, "level8"), gsm, "Play Level8");
		returnButton = new GameStateButton(WIDTH, 700, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		hubButton.tick();
		tutorialButton.tick();
		level1Button.tick();
		level2Button.tick();
		level3Button.tick();
		level4Button.tick();
		level5Button.tick();
		level6Button.tick();
		level7Button.tick();
		level8Button.tick();
		returnButton.tick();
		
		if(returnButton.isHeldOver()){
			if(returnButton.isPressed()){
				GameStateManager.states.push(new MenuState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		hubButton.render(g);
		tutorialButton.render(g);
		level1Button.render(g);
		level2Button.render(g);
		level3Button.render(g);
		level4Button.render(g);
		level5Button.render(g);
		level6Button.render(g);
		level7Button.render(g);
		level8Button.render(g);
		returnButton.render(g);
		
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
	}

}
