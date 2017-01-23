package net.cubic_control.CCMaze.Entity;

import java.net.InetAddress;

import net.cubic_control.CCMaze.Gen.Map;
import net.cubic_control.CCMaze.Managers.InputManager;

public class EntityPlayerMP extends EntityPlayerSP {

    public InetAddress ipAddress;
    public int port;

    public EntityPlayerMP(Map map, int x, int y, InputManager input, String username, InetAddress ipAddress, int port) {
        super(map, x, y, input, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public EntityPlayerMP(Map map, int x, int y, String username, InetAddress ipAddress, int port) {
        super(map, x, y, null, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void tick(double deltatime) {
        super.tick(deltatime);
    }
}
