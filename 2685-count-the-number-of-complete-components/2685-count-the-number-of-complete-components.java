import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    int vertices;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {

        // Build adjacency list
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];

        int answer = 0;

        // Traverse every connected component
        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                vertices = 0;
                edgeCount = 0;

                dfs(i);

                // Every edge is counted twice in an undirected graph
                edgeCount /= 2;

                int expectedEdges = vertices * (vertices - 1) / 2;

                if (edgeCount == expectedEdges) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(int node) {

        visited[node] = true;
        vertices++;

        // Count all edges connected to this node
        edgeCount += graph[node].size();

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}