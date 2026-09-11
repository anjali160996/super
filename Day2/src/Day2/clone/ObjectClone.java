package Day2.clone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ObjectClone {
	public static void main(String args[]) throws CloneNotSupportedException {
		Person original1 = new Person("Ada", new Address("Pune", "411001", "India"));
		Person deep = original1.clone();

		deep.getAddress().setCity("Andhra");
		deep.setName("Chandu");

		System.out.println(original1.getName());
		System.out.println(original1.getAddress().getCity());
		System.out.println(original1.getAddress().getZip());
		System.out.println(deep.getName());
		System.out.println(deep.getAddress().getCity());
		System.out.println(deep.getAddress().getZip());

		PesronShallow original3 = new PesronShallow("Ada", new Address("Pune", "411001", ""));
		PesronShallow shallow1 = original3.clone();
		shallow1.getAddress().setCity("Andhra");
		shallow1.setName("Chandu");
		System.out.println(shallow1.getName());
		System.out.println(shallow1.getAddress().getCity());
		System.out.println(shallow1.getAddress().getZip());
		System.out.println(original3.getName());
		System.out.println(original3.getAddress().getCity());
		System.out.println(original3.getAddress().getZip());
		List<Person> people = new ArrayList<>();
		people.add(new Person("Ada", new Address("Mumbai", "411001", "")));
		people.add(new Person("Zed", new Address("Mumbai", "400001", "")));
		people.add(new Person("bob", new Address("Pune", "411001", "")));
		people.add(new Person("Amy", new Address(null, "400001", "")));
		List<Person> sorted = sortForMailMerge(people);
		sorted.forEach(System.out::println);
	}

	public static List<Person> sortForMailMerge(List<Person> people) {
		Comparator<Person> byCity = Comparator
				.comparing(Person::getAddress,
						Comparator.nullsLast(Comparator.comparing(Address::getCity,
								Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))))
				.thenComparing(Person::getName, String.CASE_INSENSITIVE_ORDER);
		List<Person> result = new ArrayList<>(people);
		result.sort(byCity);
		return result;
	}
}
