class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;

        long[][] dp = new long[k + 1][2];

        // 0 segments made, no segment currently open
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {

            long[][] next = new long[k + 1][2];

            for (int j = 0; j <= k; j++) {

                // Case 1: No segment is currently open
                if (dp[j][0] != 0) {

                    // Don't start a segment at point i
                    next[j][0] =
                        (next[j][0] + dp[j][0]) % MOD;

                    // Start a new segment at point i
                    next[j][1] =
                        (next[j][1] + dp[j][0]) % MOD;
                }

                // Case 2: A segment is currently open
                if (dp[j][1] != 0) {

                    // Keep the segment open
                    next[j][1] =
                        (next[j][1] + dp[j][1]) % MOD;

                    // Close the segment at point i
                    if (j < k) {

                        // Close and don't start another
                        next[j + 1][0] =
                            (next[j + 1][0] + dp[j][1]) % MOD;

                        // Close and immediately start another
                        // segment from the same endpoint
                        next[j + 1][1] =
                            (next[j + 1][1] + dp[j][1]) % MOD;
                    }
                }
            }

            dp = next;
        }

        return (int) dp[k][0];
    }
}