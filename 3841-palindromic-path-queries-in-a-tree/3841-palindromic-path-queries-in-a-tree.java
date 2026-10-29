
import java.util.*;

class Solution {
    int[] parent, depth, size, heavy, head, pos;
    int[] seg;
    int timer, base;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(heavy, -1);
        Arrays.fill(parent, -2);

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : adj[u]) {
                if (parent[v] != u) continue;

                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        int[] chainStack = new int[n];
        int chainTop = 0;
        chainStack[chainTop++] = 0;
        timer = 0;

        while (chainTop > 0) {
            int start = chainStack[--chainTop];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = start;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        chainStack[chainTop++] = v;
                    }
                }
            }
        }

        base = 1;
        while (base < n) base <<= 1;

        seg = new int[2 * base];

        for (int u = 0; u < n; u++) {
            seg[base + pos[u]] = 1 << (s.charAt(u) - 'a');
        }

        for (int i = base - 1; i > 0; i--) {
            seg[i] = seg[2 * i] ^ seg[2 * i + 1];
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                update(pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = pathQuery(u, v);
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    void update(int p, int value) {
        int i = base + p;
        seg[i] = value;

        while (i > 1) {
            i >>= 1;
            seg[i] = seg[2 * i] ^ seg[2 * i + 1];
        }
    }

    int rangeQuery(int l, int r) {
        int result = 0;
        l += base;
        r += base;

        while (l <= r) {
            if ((l & 1) == 1) {
                result ^= seg[l++];
            }

            if ((r & 1) == 0) {
                result ^= seg[r--];
            }

            l >>= 1;
            r >>= 1;
        }

        return result;
    }

    int pathQuery(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= rangeQuery(pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= rangeQuery(pos[u], pos[v]);
        return mask;
    }
}
