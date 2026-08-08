package Day2.clone;

public class ObjectClone {
	public static void main(String args[]) throws CloneNotSupportedException {
//		Person original = new Person("Ada", new Address("Pune", "411001","India"));
//		Person shallow = original.shallowClone();
		
//		shallow.getAddress().setCity("Andhra");
//		shallow.setName("Chandu");
//		
//		System.out.println(original.getName());
//		System.out.println(original.getAddress().getCity());
//		System.out.println(original.getAddress().getZip());
//		System.out.println(shallow.getName());
//		System.out.println(shallow.getAddress().getCity());
//		System.out.println(shallow.getAddress().getZip());
//		
//		
//		Person original2 = new Person("Ada", new Address("Pune", "411001",""));
//		Person deep = original2.deepClone();
//		deep.getAddress().setCity("Andhra");
//		deep.setName("Chandu");
//		System.out.println(deep.getName());
//		System.out.println(deep.getAddress().getCity());
//		System.out.println(deep.getAddress().getZip());
//		
//		System.out.println(original2.getName());
//		System.out.println(original2.getAddress().getCity());
//		System.out.println(original2.getAddress().getZip());
		
		
		
		Person original1 = new Person("Ada", new Address("Pune", "411001","India"));
		Person deep = original1.clone();
		
		deep.getAddress().setCity("Andhra");
		deep.setName("Chandu");
		
		System.out.println(original1.getName());
		System.out.println(original1.getAddress().getCity());
		System.out.println(original1.getAddress().getZip());
		System.out.println(deep.getName());
		System.out.println(deep.getAddress().getCity());
		System.out.println(deep.getAddress().getZip());
		
		
		
		PesronShallow original3 = new PesronShallow("Ada", new Address("Pune", "411001",""));
		PesronShallow shallow1 = original3.clone();
		shallow1.getAddress().setCity("Andhra");
		shallow1 .setName("Chandu");
		System.out.println(shallow1.getName());
		System.out.println(shallow1.getAddress().getCity());
		System.out.println(shallow1 .getAddress().getZip());
		
		System.out.println(original3.getName());
		System.out.println(original3.getAddress().getCity());
		System.out.println(original3.getAddress().getZip());
	}
	
	

}
