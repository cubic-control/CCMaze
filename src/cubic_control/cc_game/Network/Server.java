package cubic_control.cc_game.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import cubic_control.cc_game.GameStates.LevelLoader;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Network.Packets.Packet;
import cubic_control.cc_game.Network.Packets.Packet.PacketType;
import cubic_control.cc_game.Network.Packets.Packet00Login;
import cubic_control.cc_game.Player.PlayerMP;

public class Server extends Thread{
	private DatagramSocket socket;
	@SuppressWarnings("unused")
	private Main game;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public Server(Main game){
		this.game = game;
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketType type = Packet.lookupPacket(message.substring(0, 2));
		switch (type){
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet = new Packet00Login(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "]" + packet.getUsername() + "has connected");
			PlayerMP player = null;
			if(address.getHostAddress().equalsIgnoreCase("127.0.0.1")){
				player = new PlayerMP(LevelLoader.map, 10, 10, packet.getUsername(), address, port);
			}else{
				player = new PlayerMP(LevelLoader.map, 10, 10, packet.getUsername(), address, port);
			}
			if(player != null){
				this.connectedPlayers.add(player);
			}
			break;
		case DISCONECTED:
			break;
		}
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for(PlayerMP p : connectedPlayers){
			sendData(data, p.ipAddress, p.port);
		}
	}
}
