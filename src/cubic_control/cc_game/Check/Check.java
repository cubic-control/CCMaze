package cubic_control.cc_game.Check;

import java.awt.Point;

import cubic_control.cc_game.Gen.Block;
import cubic_control.cc_game.Gen.TileManager;

public class Check {
	public static boolean CollisionPlayerBlock(Point p1, Point p2){
		for(Block block : TileManager.blocks){
			if(block.isSolid()){
				if(block.contains(p1) || block.contains(p2)){
					return true;
				}
			}
		}
		return false;
	}

}
