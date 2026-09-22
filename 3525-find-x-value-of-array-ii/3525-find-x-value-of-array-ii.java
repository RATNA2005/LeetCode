class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
            prod = 1 % k;
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update persists for future queries
            update(1, 0, n - 1, index, value);

            // After removing prefix [0 ... start-1],
            // consider array [start ... n-1]
            Node ans = query(1, 0, n - 1, start, n - 1);

            result[i] = ans.cnt[x];
        }

        return result;
    }

    private void build(int node, int left, int right, int[] nums) {

        if (left == right) {
            tree[node] = new Node(k);

            int rem = nums[left] % k;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right,
                        int index, int value) {

        if (left == right) {
            tree[node] = new Node(k);

            int rem = value % k;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int left, int right,
                       int qLeft, int qRight) {

        // Completely inside query range
        if (qLeft <= left && right <= qRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Completely on left
        if (qRight <= mid) {
            return query(node * 2, left, mid, qLeft, qRight);
        }

        // Completely on right
        if (qLeft > mid) {
            return query(node * 2 + 1, mid + 1, right, qLeft, qRight);
        }

        // Split between left and right
        Node leftNode =
            query(node * 2, left, mid, qLeft, qRight);

        Node rightNode =
            query(node * 2 + 1, mid + 1, right, qLeft, qRight);

        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of entire combined segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes lying completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Prefixes containing the entire left segment
        // followed by a prefix of right
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;

            res.cnt[newRem] += right.cnt[r];
        }

        return res;
    }
}