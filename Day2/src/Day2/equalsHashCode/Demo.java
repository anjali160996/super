package Day2.equalsHashCode;

import java.util.HashMap;
import java.util.Map;

public class Demo {
	public static void main(String[] args) {
		Map<Point, String> map = new HashMap<>();
		Point p = new Point(1,2);
		Point p2 = new Point(1,2);
		map.put(p,"A");
		System.out.println(map.get(new Point(0,0)));
		System.out.println(map.get(new Point(1,2)));
		System.out.println(map.get(p));
		System.out.println(map.get(p2));
	}

}
