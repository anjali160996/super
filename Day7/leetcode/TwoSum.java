package day7.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int required = target - nums[i];
			if (map.containsKey(required)) {
				return new int[] { map.get(required), i };
			}
			map.put(nums[i], i);
		}
		return new int[0];
	}

	public static void main(String[] args) {
		TwoSum t = new TwoSum();
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		int[] result = t.twoSum(nums, target);
		System.out.println(Arrays.toString(result));
	}
}
