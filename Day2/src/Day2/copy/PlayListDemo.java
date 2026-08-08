package Day2.copy;

import java.util.ArrayList;
import java.util.List;

public class PlayListDemo {
	public static void main(String[] args) {
		List<String> input = new ArrayList<>(List.of("a", "b"));
		Playlist p = new Playlist();
		p.setTracks(input);
		input.forEach(s -> System.out.println("Initial " + s));
		input.add("c");
		input.add("d");
		
		input.forEach(s -> System.out.println("After addition " + s));

		p.getTracks().forEach(s -> System.out.println("Copied Tracks " + s));
		try {
			p.getTracks().add("d");
		} catch (UnsupportedOperationException e) {
			System.out.println("Exception list is unmodifieable");
			input.clear();
			input.forEach(s -> System.out.println("After addition " + s));
		}

	}
}
