import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySound {

	public static void main(String[] args)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException, InterruptedException {

		
		// lets get the samples 
		//ArrayList<Float> samples = new HelloWorldSound().obtainsamples();
		//ArrayList<Float> samples = new PitchDecorator(0.5f,new HelloWorldSound()).obtainsamples();
		//ArrayList<Float> samples = (new VolumeDecorator(0.5f,(new HelloWorldSound()))).obtainsamples();
  // 	ArrayList<Float> samples = (new VolumeDecorator(1.5f,(new EchoDecorator(30000, 0.6f,(new HelloWorldSound()))))).obtainsamples();
		ArrayList<Float> samples = new EchoDecorator(30000,0.9f,(new PitchDecorator(0.5f,new VolumeDecorator(0.5f,(new HelloWorldSound()))))).obtainsamples();

		
		// and then play the samples 
		short sound[] = new short[samples.size()];
		for (int i=0;i< samples.size();i++) sound[i] = (short) Math.round(samples.get(i));
		
		byte audioout[] = new byte[sound.length * 2];
		ByteBuffer.wrap(audioout).order(ByteOrder.BIG_ENDIAN).asShortBuffer()
				.put(sound);
		AudioInputStream hellop = new AudioInputStream(
				new ByteArrayInputStream(audioout), new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 2, 4,
						4.0f, true), sound.length);
		Clip clip = AudioSystem.getClip();

		clip.open(hellop);
		clip.start();
		Thread.sleep((1000/2 * sound.length /  44100)    );
		clip.close();
	}

}
