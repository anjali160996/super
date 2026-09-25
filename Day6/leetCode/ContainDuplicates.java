package day6.leetCode;

import java.util.HashSet;

public class ContainDuplicates {
	public boolean containsDuplicate(int[] nums) {
		boolean flag = false;
		HashSet<Integer> hs = new HashSet<>();
		for (int i = 0; i < nums.length ; i++) {
			if (hs.contains(nums[i])) 
				return true;
			hs.add(nums[i]);
		}
		return flag;
	}

	public static void main(String args[]) {
		ContainDuplicates c = new ContainDuplicates();
		int[] nums = { 1, 1,2, 3, 1};
		System.out.println(c.containsDuplicate(nums));

	}

}
