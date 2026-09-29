class Solution {

    int m, n;
    char[][] grid;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Total characters in every path
        int len = m + n - 1;

        // Valid parentheses string must have even length
        if (len % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        dp = new Boolean[m][n][len + 1];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int row, int col, int balance) {

        // Update balance
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        // More ')' than '('
        if (balance < 0) {
            return false;
        }

        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        if (dp[row][col][balance] != null) {
            return dp[row][col][balance];
        }

        boolean ans = false;

        // Move down
        if (row + 1 < m) {
            ans = dfs(row + 1, col, balance);
        }

        // Move right
        if (!ans && col + 1 < n) {
            ans = dfs(row, col + 1, balance);
        }

        dp[row][col][balance] = ans;

        return ans;
    }
}