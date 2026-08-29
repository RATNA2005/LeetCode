import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[n];

        int i = 0;
        while (i < n) {
            int j = i;

            // Find one connected group
            while (j + 1 < n && arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            List<Integer> indices = new ArrayList<>();

            for (int k = i; k <= j; k++) {
                indices.add(arr[k][1]);
            }

            Collections.sort(indices);

            for (int k = 0; k < indices.size(); k++) {
                ans[indices.get(k)] = arr[i + k][0];
            }

            i = j + 1;
        }

        return ans;
    }
}