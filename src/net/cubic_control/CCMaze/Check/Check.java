package net.cubic_control.CCMaze.Check;

import java.awt.Point;

import net.cubic_control.CCMaze.Gen.Block;
import net.cubic_control.CCMaze.Gen.TileManager;

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
