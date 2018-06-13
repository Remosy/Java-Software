import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class HelloWorldSound implements Sound {

	@Override
	public ArrayList<Float> obtainsamples() {
		// TODO Auto-generated method stub
		ArrayList<Float> data = new ArrayList<Float>();

		int samples = 300000;
		byte audioin[] = new byte[samples * 2];

		AudioInputStream hello;
		try {
			hello = AudioSystem.getAudioInputStream(new File("src/decorator/hello.aiff"));
			AudioFormat format = hello.getFormat();
			assert (format.isBigEndian());
			assert (format.getEncoding() == AudioFormat.Encoding.PCM_SIGNED);
			assert (format.getSampleSizeInBits() == 16);

			System.out.println(format);
			try {
				int res = hello.read(audioin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ShortBuffer fbuf = ByteBuffer.wrap(audioin)
					.order(ByteOrder.BIG_ENDIAN).asShortBuffer();
			final short[] shortbuf = new short[fbuf.capacity()];
			fbuf.get(shortbuf);

			for (int i = 0; i < shortbuf.length; i++)
				data.add(shortbuf[i] * 1.0f);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return data;
	}

}
