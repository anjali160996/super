package day7.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		List<int[]> results = new ArrayList<>();
		results.add(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] present = intervals[i];
			int[] lastElement = results.get(results.size() - 1);
			if (present[0] <= lastElement[1]) {
				lastElement[1] = Math.max(lastElement[1], present[1]);
			} else {
				results.add(present);
			}
		}
		return results.toArray(new int[0][0]);
	}

	public static void main(String args[]) {
		MergeIntervals merge = new MergeIntervals();
		int[][] intervals = { { 2, 6 }, { 1, 3 }, { 8, 10 }, { 15, 18 } };
		int[][] results = merge.merge(intervals);
		System.out.println(Arrays.deepToString(results));

	}

}
