class Solution {
    public int longestSubsequence(int[] nums) {

        int n = nums.length;
        int xor = 0;
        int zeroCount = 0;

        // Calculate XOR of all elements
        // and count zeros
        for (int x : nums) {
            xor ^= x;

            if (x == 0) {
                zeroCount++;
            }
        }

        // Case 1:
        // Entire array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // Case 2:
        // All elements are zero
        if (zeroCount == n) {
            return 0;
        }

        // Case 3:
        // Total XOR is 0, but there is
        // at least one non-zero element
        return n - 1;
    }
}