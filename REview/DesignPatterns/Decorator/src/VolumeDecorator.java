import java.util.ArrayList;


public class VolumeDecorator extends SoundDecorator {

	float volume;
	
	public VolumeDecorator(float volume, Sound parentsound) {
		this.volume = volume;
		this.parentsound = parentsound;
	}
	
	@Override
	public ArrayList<Float> obtainsamples() {
		ArrayList<Float> list = parentsound.obtainsamples();
		for (int i=0;i<list.size(); i++) list.set(i,list.get(i) * volume);
		return list;
		
	}

}
