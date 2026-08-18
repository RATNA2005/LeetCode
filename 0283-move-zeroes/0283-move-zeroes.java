class Solution {
    public void moveZeroes(int[] nums) {
        int slow=0;
        for(int fast=0;fast<nums.length;fast++)
        {
            if(nums[fast]!=0)
            {
                nums[slow]=nums[fast];
                slow++;
            }
        }
        for(int i=slow;slow<nums.length;slow++)
        {
            nums[slow]=0;
        }
        for(int i=0;i<nums.length;i++)
        {
            System.out.print(nums[i]);
        }
        
    }
}