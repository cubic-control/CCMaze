package cubic_control.cc_game.Entity;

import cubic_control.cc_game.Gen.Map;

public abstract class Entity {

	public int x, y;
	protected Map level;

	public Entity(Map level) {
		init(level);
	}

	public final void init(Map level) {
		this.level = level;
	}

	public abstract void tick();

	public abstract void render();
}
