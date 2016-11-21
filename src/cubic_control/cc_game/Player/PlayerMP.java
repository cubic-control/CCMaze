package cubic_control.cc_game.Player;

import java.net.InetAddress;

import cubic_control.cc_game.Gen.Map;
import cubic_control.cc_game.Managers.InputManager;

public class PlayerMP extends Player {

    public InetAddress ipAddress;
    public int port;

    public PlayerMP(Map map, int x, int y, InputManager input, String username, InetAddress ipAddress, int port) {
        super(map, x, y, input, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public PlayerMP(Map map, int x, int y, String username, InetAddress ipAddress, int port) {
        super(map, x, y, null, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void tick(double deltatime) {
        super.tick(deltatime);
    }
}
