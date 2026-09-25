package day3;

public class ProductOfArray {
	int[] answer;

	public int[] leftproduct(int[] nums) {
		answer = new int[nums.length];
		int left = 1;
		for (int i = 0; i < nums.length; i++) {
			answer[i] = left;
			left = left * nums[i];
		}
		return answer;
	}

	public int[] totalproduct(int[] nums, int[] answer) {
		int right = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			answer[i] = answer[i] * right;
			right = right * nums[i];
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4 };
		int[] nums2 = { -1, 1, 0, -3, 3 };
		ProductOfArray p = new ProductOfArray();
		int answer[] = p.leftproduct(nums);
		answer = p.totalproduct(nums, answer);
		for (int num : answer) {
			System.out.print(num + " ");
		}
		int answer1[] = p.leftproduct(nums2);
		answer1 = p.totalproduct(nums2, answer1);
		for (int num : answer1) {
			System.out.print(num + " ");
		}
	}
//	public int[] rightproduct(int[] nums) {
//	int[] answer2 = new int[nums.length];
//	int right = 1;
//	for (int i = nums.length - 1; i >= 0; i--) {
//		answer2[i] = right;
//		right = right * nums[i];
//	}
//	return answer2;
//}
}
