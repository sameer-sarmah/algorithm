

public class Crap {

	public static void main(String[] args) {

		
		System.out.println(lowestOneBit(10));
		System.out.println(Integer.lowestOneBit(10));
		System.out.println(removeLowestOneBit(9));
		System.out.println(addLowestOneBit(10));

//		   Person p=new Person("sameer",30);
//	       Map<Person,String> map=new HashMap<>();
//	       map.put(p, "sam");
//	       System.out.println(map.get(p));
//	       p.setName("samir");
//	       System.out.println(map.get(p));
//	       changeRef(p);
//	       System.out.println(p.getName());
//		
	}
	
	private static int lowestOneBit(int number) {
		String binaryRep= Integer.toBinaryString(number);
		String twosComplement= Integer.toBinaryString((~number)+1);
		twosComplement=twosComplement.substring(twosComplement.length()-binaryRep.length(),twosComplement.length());
		String str= Integer.toBinaryString(Integer.parseInt(binaryRep, 2) & Integer.parseInt(twosComplement, 2));
		return str.length()-str.indexOf('1');
	}
	
	private static int removeLowestOneBit(int number) {
		int lowestOneBitIndex=Integer.lowestOneBit(number);
		String binary=Integer.toBinaryString(number);
		int subStringIndex=binary.length()-lowestOneBitIndex;
		String updatedStr=binary.substring(0, subStringIndex)+"0"+binary.substring(subStringIndex+1);
		return Integer.parseInt(updatedStr,2); 
	}
	
	private static int addLowestOneBit(int number) {
		int lowestOneBitIndex=Integer.lowestOneBit(number);
        StringBuilder str=new StringBuilder();
        str.append("1");
        int counter=lowestOneBitIndex-1;
        while(counter>0) {
        	str.append("0");
        	counter--;
        }
		return Integer.parseInt(str.toString(),2); 
	}
	
	

	public static void changeRef(Person p) {
		p=new Person("sam",30);
	}

}

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}