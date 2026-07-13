class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int curMax = 0;
        int maxSum = Integer.MIN_VALUE;
        int curMin = 0;
        int minSum = Integer.MAX_VALUE;
        for(int num:nums)
        {
            curMax+=num;
            totalSum+=num;
            curMin+=num;
            maxSum=Math.max(curMax,maxSum);
            minSum=Math.min(curMin,minSum);
            if(curMax<0)
            {
                curMax=0;
            }
            if(curMin>0)
            {
                curMin=0;
            }
        }
        int curSum=totalSum-minSum;
        if (maxSum<0)
        {
            return maxSum;
        }
        else
        {
            return Math.max(maxSum,curSum);
        }

    }
}