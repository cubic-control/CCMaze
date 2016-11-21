package cubic_control.cc_game.GameStates;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Gen.Map;

import java.awt.Graphics2D;

public class LevelLoader
extends GameState {
    public static Map map;
    private String levelName;
    private String audioName;

    public LevelLoader(GameStateManager gsm, String levelName, String audioName) {
        super(gsm);
        this.levelName = levelName;
        this.audioName = audioName;
    }
    
    public LevelLoader(GameStateManager gsm, String name) {
        super(gsm);
        this.levelName = name;
        this.audioName = name;
    }

    public void init() {
        System.out.println("[System]:Initializing LevelLoader");
        LevelLoader.map = new Map(levelName + ".png", audioName + ".wav");
        LevelLoader.map.init();
    }

    public void tick(double deltaTime) {
        LevelLoader.map.tick(deltaTime);
    }

    public void render(Graphics2D g) {
        LevelLoader.map.render(g);
    }
}