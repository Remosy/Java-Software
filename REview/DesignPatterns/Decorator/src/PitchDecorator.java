import java.util.ArrayList;

public class PitchDecorator extends SoundDecorator {

	float pitchchange;

	public PitchDecorator(float pitchchange, Sound parentsound) {
		this.pitchchange = pitchchange;
		this.parentsound = parentsound;
	}

	@Override
	public ArrayList<Float> obtainsamples() {
		ArrayList<Float> list = parentsound.obtainsamples();
		ArrayList<Float> res = new ArrayList<Float>();
		int parentsize = list.size();
		int pos = 0;

		while (pos < parentsize * pitchchange) {

			int index = Math.round(pos / pitchchange);
			if (index < parentsize)
				res.add(list.get(index));
			pos++;
		}
		return res;
	}

}
