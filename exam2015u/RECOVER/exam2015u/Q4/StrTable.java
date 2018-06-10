/*
 * StrTable - a simple interface for a table.  
 * This table is indexed via a key (which is a String) and maps to a value (also a String). 
 * Eric McCreath - 2015
 */


public interface StrTable {
	String lookup(String key); // lookup a value within the table.
	void insert(String key, String value); // add a new mapping to the table
}
