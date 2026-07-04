class Solution {

    class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        ArrayList<Pair>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph[u].add(new Pair(v, d));
            graph[v].add(new Pair(u, d));
        }

        boolean[] vis = new boolean[n + 1];

        dfs(1, graph, vis);

        return ans;
    }

    private void dfs(int node, ArrayList<Pair>[] graph, boolean[] vis) {

        vis[node] = true;

        for (Pair nei : graph[node]) {

            ans = Math.min(ans, nei.dist);

            if (!vis[nei.node]) {
                dfs(nei.node, graph, vis);
            }
        }
    }
}