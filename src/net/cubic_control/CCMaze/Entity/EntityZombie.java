package net.cubic_control.CCMaze.Entity;

import java.awt.image.BufferedImage;

import cubic_control.resources.assets.Animator;
import cubic_control.resources.assets.Assets;

public class EntityZombie extends Entity{
	public static EntityZombie instance;
	
	private int pWAS = 20;
    private int pIAS = 40;

	public EntityZombie() {
		super(250, 250);
		this.idle = new Animator(pIAS, new BufferedImage[]{Assets.spr_zomb_idle1, Assets.spr_zomb_idle2});
        this.walkUp = new Animator(pWAS, new BufferedImage[]{Assets.spr_zomb_walk_up1, Assets.spr_zomb_walk_up2, Assets.spr_zomb_walk_up3, Assets.spr_zomb_walk_up4});
        this.walkDown = new Animator(pWAS, new BufferedImage[]{Assets.spr_zomb_walk_down1, Assets.spr_zomb_walk_down2, Assets.spr_zomb_walk_down3, Assets.spr_zomb_walk_down4});
        this.walkLeft = new Animator(pWAS, new BufferedImage[]{Assets.spr_zomb_walk_left1, Assets.spr_zomb_walk_left2, Assets.spr_zomb_walk_left3, Assets.spr_zomb_walk_left4});
        this.walkRight = new Animator(pWAS, new BufferedImage[]{Assets.spr_zomb_walk_right1, Assets.spr_zomb_walk_right2, Assets.spr_zomb_walk_right3, Assets.spr_zomb_walk_right4});
	}

}
