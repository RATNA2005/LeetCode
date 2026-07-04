class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int zeros = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            // Expand the window
            if (nums[right] == 0) {
                zeros++;
            }

            // Shrink the window if it contains more than k zeros
            while (zeros > k) {

                if (nums[left] == 0) {
                    zeros--;
                }

                left++;
            }

            // Update the maximum window length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}