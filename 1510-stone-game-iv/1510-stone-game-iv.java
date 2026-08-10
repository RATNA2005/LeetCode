class Solution {
    public boolean winnerSquareGame(int n) {

        // dp[i] = true if the player can win with i stones
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> no move -> lose

        for (int i = 1; i <= n; i++) {

            // Try every perfect square <= i
            for (int j = 1; j * j <= i; j++) {

                int square = j * j;

                // If this move makes opponent lose,
                // current player wins
                if (!dp[i - square]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}