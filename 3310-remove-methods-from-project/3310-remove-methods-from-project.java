import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] suspicious;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : invocations)
            graph[edge[0]].add(edge[1]);

        suspicious = new boolean[n];

        dfs(k);

        // Check if any outside node invokes a suspicious node
        for (int[] edge : invocations) {

            int u = edge[0];
            int v = edge[1];

            if (!suspicious[u] && suspicious[v]) {

                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++)
                    ans.add(i);

                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.add(i);
        }

        return ans;
    }

    private void dfs(int node) {

        if (suspicious[node])
            return;

        suspicious[node] = true;

        for (int next : graph[node])
            dfs(next);
    }
}