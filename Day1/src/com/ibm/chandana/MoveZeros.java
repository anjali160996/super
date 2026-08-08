package com.ibm.chandana;

import java.util.Arrays;

class MoveZeros {
    public void moveZeroes(int[] nums) {
        int temp;
        int left=0;
        for(int right=0;right<nums.length;right++){
         if(nums[right]!=0){
            temp=nums[right];
            nums[right]=nums[left];
            nums[left]=temp;
            left++; 
         }    
        }     
    }
    public static void main(String args[]) {
    	MoveZeros s = new MoveZeros();
    	  int[] nums = {0, 1, 0, 3, 12};
          s.moveZeroes(nums);
          System.out.println(Arrays.toString(nums));
    	
    }
}
