class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum=0;
        int maxSum=Integer.MIN_VALUE;
        for(int num:nums)
        {
            currentSum+=num;
            maxSum=Math.max(currentSum,maxSum);
            if(currentSum<0)
            {
                currentSum=0;
            }
        }
        return maxSum;
    }
}