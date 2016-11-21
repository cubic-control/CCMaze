package cubic_control.cc_game.Managers;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	private Clip clip;
	
	public AudioManager(Class<?> classfile, String path){
		try{
			AudioInputStream AIS = AudioSystem.getAudioInputStream(classfile.getResource(path));
			AudioFormat baseFormat = AIS.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
					baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(), 
					false);
			AudioInputStream DAIS = AudioSystem.getAudioInputStream(decodeFormat, AIS);
			clip = AudioSystem.getClip();
			clip.open(DAIS);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(clip == null){
			return;
		}else{
			stop();
			clip.setFramePosition(0);
			clip.start();
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

}
