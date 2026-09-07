class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1_000_000_007;
        int n = s.length();

        long[] dp = new long[n + 1];

        // Empty string
        dp[0] = 1;

        // last[c] = previous 1-based position of character c
        int[] last = new int[26];

        for (int i = 1; i <= n; i++) {

            char ch = s.charAt(i - 1);

            // Double previous subsequences
            dp[i] = (2 * dp[i - 1]) % MOD;

            int prev = last[ch - 'a'];

            // If this character appeared before,
            // remove duplicated subsequences
            if (prev != 0) {
                dp[i] = (dp[i] - dp[prev - 1] + MOD) % MOD;
            }

            // Store current 1-based position
            last[ch - 'a'] = i;
        }

        // Remove empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}