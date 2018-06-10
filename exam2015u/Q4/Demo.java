
public class Demo {

	/**
	 * Demo - Some code to demo a table implementation.
	 * The home locations of different super heros are added to the 
	 * table.   Some of them are then looked up. 
	 * Eric McCreath - 2015
	 */
	public static void main(String[] args) {
		StrTable table = new Magneto();
		table.insert("Batman", "Batcave");
		table.insert("Catwoman", "Gotham");
		table.insert("Sherlock", "221b Baker St");
		table.insert("DrWho", "TARDIS");
		table.insert("Robin", "Batcave");
		table.insert("Wonderwoman", "Themyscira Island");
		table.insert("Thor", "Asgar");
		table.insert("Superman", "Northpole");
		table.insert("BlackWidow","Russia");
		table.insert("BlackAdder","Britian");
		
		lookuphome(table,"Thor");
		lookuphome(table,"BlackAdder");
		lookuphome(table,"Wonderwoman");
		
	}

	private static void lookuphome(StrTable table, String name) {
		System.out.println("--------------");
		System.out.println(name + "'s home is " + table.lookup(name) + ".");
	}

	private static void showt(StrTable table) {
		System.out.println("+++++++++++++++");
		System.out.println(((Magneto) table).showTree(0));
		System.out.println("+++++++++++++++");
	}

}
