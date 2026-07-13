class Solution {
    public int maxProduct(int[] nums) {

        int maxPro = Integer.MIN_VALUE;
        int curPro = 1;

        // Left to Right
        for (int i = 0; i < nums.length; i++) {
            curPro *= nums[i];
            maxPro = Math.max(maxPro, curPro);

            if (curPro == 0)
                curPro = 1;
        }

        // Right to Left
        curPro = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            curPro *= nums[i];
            maxPro = Math.max(maxPro, curPro);

            if (curPro == 0)
                curPro = 1;
        }

        return maxPro;
    }
}