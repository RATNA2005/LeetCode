class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] orderObj = new Integer[n];
        for (int i = 0; i < n; i++) orderObj[i] = i;
        Arrays.sort(orderObj, (a, b) -> nums[a] - nums[b]);

        int[] order = new int[n];
        int[] valSorted = new int[n];
        int[] pos = new int[n];
        for (int s = 0; s < n; s++) {
            order[s] = orderObj[s];
            valSorted[s] = nums[order[s]];
            pos[order[s]] = s;
        }

        // component id for each sorted position
        int[] comp = new int[n];
        for (int i = 1; i < n; i++) {
            comp[i] = comp[i - 1] + (valSorted[i] - valSorted[i - 1] > maxDiff ? 1 : 0);
        }

        // farthest[i]: furthest sorted index reachable in ONE hop from i
        int[] farthest = new int[n];
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (right < i) right = i;
            while (right + 1 < n && valSorted[right + 1] - valSorted[i] <= maxDiff) {
                right++;
            }
            farthest[i] = right;
        }

        int LOG = Math.max(1, 32 - Integer.numberOfLeadingZeros(Math.max(1, n - 1)));
        int[][] jump = new int[LOG][n];
        jump[0] = farthest;
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];
        for (int idx = 0; idx < q; idx++) {
            int u = queries[idx][0], v = queries[idx][1];
            if (u == v) {
                ans[idx] = 0;
                continue;
            }
            int p = pos[u], target = pos[v];
            if (p > target) {
                int tmp = p; p = target; target = tmp;
            }
            if (comp[p] != comp[target]) {
                ans[idx] = -1;
                continue;
            }
            int cur = p, steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (jump[k][cur] < target) {
                    cur = jump[k][cur];
                    steps += (1 << k);
                }
            }
            if (cur < target) steps += 1;
            ans[idx] = steps;
        }

        return ans;
    }
}