package hashtable;

public class Driver {

	public static void main(String[] args) {
		HashTable<String,String> map=new HashTable<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		System.out.println(map.get("key1"));
		System.out.println(map.getKeys());

	}

}
