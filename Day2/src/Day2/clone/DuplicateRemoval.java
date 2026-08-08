package Day2.clone;

public class DuplicateRemoval {
	public int removeDuplicates(int[] nums) {
		int k = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				nums[k] = nums[i];
				k++;
			}
		}
		return k;

	}

	public static void main(String args[]) {
		DuplicateRemoval d = new DuplicateRemoval();
		int nums[] = new int[] { 1, 2, 3, 3, 6, 7, 8, 8, 8 };
		int k = d.removeDuplicates(nums);
		System.out.println("k = " + k);

		for (int i = 0; i < k; i++) {
			System.out.println(nums[i] + " ");
		}

	}

}
