package rms;

public interface Register {
	public void set(int v);
	public void reset();
	public int get();


	public void setConvert(int sel);
}
