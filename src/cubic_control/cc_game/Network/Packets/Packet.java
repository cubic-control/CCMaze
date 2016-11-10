package cubic_control.cc_game.Network.Packets;

import cubic_control.cc_game.Network.Client;
import cubic_control.cc_game.Network.Server;

public abstract class Packet {

	public static enum PacketType{
		INVALID(-1), LOGIN(00), DISCONECTED(01);
		
		private int packetID;
		private PacketType(int packetID){
			this.packetID = packetID;
		}
		
		public int getID(){
			return packetID;
		}
	}
	
	public byte packetID;
	
	public Packet(int packetID){
		this.packetID = (byte) packetID;
	}
	
	public abstract void writeData(Client client);
	public abstract void writeData(Server server);
	
	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	public static PacketType lookupPacket(String packetID){
		try{
			return lookupPacket(Integer.parseInt(packetID));
		}catch(NumberFormatException e){
			return PacketType.INVALID;
		}
	}
	
	public static PacketType lookupPacket(int id){
		for(PacketType p : PacketType.values()){
			if(p.getID() == id){
				return p;
			}
		}
		return PacketType.INVALID;
	}
}
