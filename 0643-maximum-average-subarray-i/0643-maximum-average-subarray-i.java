class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int left=0;
        double sum=0;
        double max_avg=Integer.MIN_VALUE;
        for(int right=0;right<nums.length;right++)
        {
            sum+=nums[right];
            if(right-left+1==k)
            {
                max_avg=Math.max(max_avg,sum/k);
                sum-=nums[left];
                left++;
            }
        }
        return max_avg;
        
    }
}