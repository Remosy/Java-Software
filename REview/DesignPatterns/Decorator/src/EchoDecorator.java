import java.util.ArrayList;


public class EchoDecorator extends SoundDecorator {

	int delay;
	float echovol;
	
	public EchoDecorator(int delay, float echovol, Sound parentsound) {
		this.delay = delay;
		this.echovol = echovol;
		this.parentsound = parentsound;
	}
	
	@Override
	public ArrayList<Float> obtainsamples() {
		ArrayList<Float> list = parentsound.obtainsamples();
		int parentsize = list.size();
		for (int i=delay;i<parentsize+delay; i++) {
			if (i<parentsize) {
				list.set(i,list.get(i)   + list.get(i-delay) * echovol);
			} else {
				list.add(list.get(i-delay) * echovol);
			}
		}
		return list;
		
	}

}
