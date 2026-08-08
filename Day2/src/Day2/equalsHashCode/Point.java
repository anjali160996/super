package Day2.equalsHashCode;

import java.util.Objects;

public final class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		Point p = (Point) o;
		return this.x == p.x && this.y == p.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
