package btree;

public class KeyAndValue {
	String key;
	String value;

	public KeyAndValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String show() {
		return key + "=>" + value;
	}

}
