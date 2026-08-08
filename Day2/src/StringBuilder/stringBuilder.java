package StringBuilder;

import java.util.List;

public class stringBuilder {
	String join(List<String> parts, String delim) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < parts.size(); i++) {
			s.append(parts.get(i));
			if(i<parts.size()-1) {
				s.append(delim);
			}
		}
		return s.toString();
	}

	//join(List.of("a", "b", "c"), ",")  →  "a,b,c"
	public static void main(String args[]) {
		stringBuilder s= new stringBuilder();
		System.out.println(s.join(List.of("a", "b", "c"), ","));
		System.out.println(s.join(List.of(), ","));
		System.out.println(s.join(List.of("only"), ","));
		
		

	}

}
