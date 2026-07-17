class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int MOD = 1000000007;
        long totalSum = 0;

        // Calculate total sum of the array
        for (int num : arr) {
            totalSum += num;
        }

        // Run Kadane on one or two copies
        int times = (k == 1) ? 1 : 2;
        long currSum = 0;
        long maxSum = 0;

        for (int i = 0; i < times * arr.length; i++) {
            currSum = Math.max(arr[i % arr.length], currSum + arr[i % arr.length]);
            maxSum = Math.max(maxSum, currSum);
        }

        // If total sum is positive, include middle copies
        if (k > 1 && totalSum > 0) {
            maxSum += (long) (k - 2) * totalSum;
        }

        return (int) (maxSum % MOD);
    }
}