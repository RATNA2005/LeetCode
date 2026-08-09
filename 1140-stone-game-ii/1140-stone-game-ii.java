class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        // -1 means state has not been calculated
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {

        // All piles have been taken
        if (i >= n) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int best = 0;

        // Take X piles, where X <= 2*M
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            // Stones taken by current player
            int taken = suffix[i] - suffix[i + X];

            // Opponent gets the best possible result
            int opponent = solve(i + X, Math.max(M, X));

            // Current player wants to maximize their stones
            int current = taken + (suffix[i + X] - opponent);

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}