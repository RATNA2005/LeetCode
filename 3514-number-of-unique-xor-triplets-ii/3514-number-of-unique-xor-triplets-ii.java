class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int v : nums) {
            boolean[][] temp = new boolean[4][MAX];

            // Copy previous states
            for (int i = 0; i < 4; i++) {
                System.arraycopy(dp[i], 0, temp[i], 0, MAX);
            }

            // Allow using the current index up to 3 times
            for (int repeat = 0; repeat < 3; repeat++) {
                for (int cnt = 2; cnt >= 0; cnt--) {
                    for (int x = 0; x < MAX; x++) {
                        if (temp[cnt][x]) {
                            temp[cnt + 1][x ^ v] = true;
                        }
                    }
                }
            }

            dp = temp;
        }

        int ans = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) ans++;
        }

        return ans;
    }
}