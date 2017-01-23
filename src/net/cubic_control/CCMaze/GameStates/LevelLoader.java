package net.cubic_control.CCMaze.GameStates;

import java.awt.Graphics2D;

import net.cubic_control.CCMaze.GameState.GameState;
import net.cubic_control.CCMaze.GameState.GameStateManager;
import net.cubic_control.CCMaze.Gen.Map;

public class LevelLoader
extends GameState {
    public static Map map;
    private String levelName;
    private String audioName;

    public LevelLoader(GameStateManager gsm, String levelName) {
        super(gsm);
        this.levelName = levelName;
        this.audioName = levelName;
    }

    public void init() {
        System.out.println("[System][INFO]:Initializing LevelLoader");
        LevelLoader.map = new Map(levelName + ".png", "/assets/sounds/music/" + audioName + ".wav");
        LevelLoader.map.init();
    }

    public void tick(double deltaTime) {
        LevelLoader.map.tick(deltaTime);
    }

    public void render(Graphics2D g) {
        LevelLoader.map.render(g);
    }
}