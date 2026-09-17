class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000;

        // best[i] = minimum length of a valid subarray
        // found from index 0 to i
        int[] best = new int[n];

        int left = 0;
        int sum = 0;
        int minLen = INF;
        int ans = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int currLen = right - left + 1;

                // If a previous non-overlapping valid subarray exists
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, currLen + best[left - 1]);
                }

                minLen = Math.min(minLen, currLen);
            }

            best[right] = minLen;
        }

        return ans == INF ? -1 : ans;
    }
}