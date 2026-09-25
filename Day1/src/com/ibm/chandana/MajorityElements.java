package com.ibm.chandana;

import java.util.Arrays;

public class MajorityElements {
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int count = 0;
		int number = nums[0];
		int maxCount = 0;
		for (int left = 0; left < nums.length - 1; left++) {
			if (number == nums[left]) {
				count++;
			} else {
				number = nums[left];
				count = 1;
			}
			maxCount = Math.max(count, maxCount);

			if (maxCount > (nums.length) / 2) {
				break;
			}

		}
		return number;
	}
}
