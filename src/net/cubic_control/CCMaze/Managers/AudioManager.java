package net.cubic_control.CCMaze.Managers;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	private Clip clip;
	private boolean doesLoop;
	public static boolean musicOn = true;
	
	public AudioManager(String s, boolean b){
		doesLoop = b;
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(s));
			AudioFormat baseFormat = stream.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dStream = AudioSystem.getAudioInputStream(decodeFormat, stream);
			clip = AudioSystem.getClip();
			clip.open(dStream);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(clip == null){
			return;
		}
		stop();
		if(musicOn){
			clip.setFramePosition(0);
			clip.start();
			if(doesLoop){
				loop();
			}
		}
	}
	
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	
	public void close(){
		stop();
		clip.close();
	}
	
	public void loop(){
		clip.loop(500);
	}
}