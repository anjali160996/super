package day3;

public class RotateRight {
	public void rotate(int[] nums, int k) {
		 k=k%nums.length;
		reverse(nums,0,nums.length-1);
		reverse(nums,0,k-1);
		reverse(nums,k,nums.length-1);	
	}	
	public void reverse(int[] nums,int start,int end) {
		int temp=0;
		while(start<end) {
			temp = nums[end];
			nums[end] = nums[start];
			nums[start] = temp;
			end--;
			start++;		
		}
	}
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5,6,7 };
		int k = 3;
		RotateRight right = new RotateRight();
		right.rotate(nums,k);
		int[] nums2 = { 1, 2, 3, 4, 5,6,7 };
		int k1=100;
		right.rotate(nums2,k1);
		for (int num : nums) {
			System.out.println(num + " ");
		}
		for (int num : nums2) {
			System.out.println(num + " ");
		}
	}

}
