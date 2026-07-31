class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;

        // {score, originalIndex}
        int[][] athletes = new int[n][2];

        for (int i = 0; i < n; i++) {
            athletes[i][0] = score[i];
            athletes[i][1] = i;
        }

        Arrays.sort(athletes, (a, b) -> b[0] - a[0]);

        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            int index = athletes[i][1];

            if (i == 0) {
                ans[index] = "Gold Medal";
            } else if (i == 1) {
                ans[index] = "Silver Medal";
            } else if (i == 2) {
                ans[index] = "Bronze Medal";
            } else {
                ans[index] = String.valueOf(i + 1);
            }
        }

        return ans;
    }
}