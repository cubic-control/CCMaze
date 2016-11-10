package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;

public class QuitState extends GameState{

	GameStateButton yes;
	GameStateButton no;
	Mousemanager mm;
	
	public QuitState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing QuitState");
		mm = new Mousemanager();
		yes = new GameStateButton(Main.width / 3, 200, "Yes!");
		no = new GameStateButton(Main.width / 3 + 200, 200, "No!");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		yes.tick();
		no.tick();
		
		if(yes.isHeldOver()){
			if(yes.isPressed()){
				System.exit(1);
			}
		}
		if(no.isHeldOver()){
			if(no.isPressed()){
				GameStateManager.states.push(new MenuState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		yes.render(g);
		no.render(g);
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
		
		g.drawString("Leaving so soon?", Main.width / 2 - 125, 100);
	}

}
