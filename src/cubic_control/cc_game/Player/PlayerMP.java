package cubic_control.cc_game.Player;

import java.net.InetAddress;

import cubic_control.cc_game.Gen.Map;

public class PlayerMP extends Player{
	
	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Map map, int x, int y, String username, InetAddress ipAddress, int port) {
		super(map);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public void tick(double deltaTime){
		super.tick(deltaTime);
	}

}
