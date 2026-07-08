class Solution {

    static final int MOD = 1_000_000_007;

    class Node {
        long val;
        int len;
        Node(long v, int l) {
            val = v;
            len = l;
        }
    }

    Node[] tree;
    int[] pow10;
    int[] prefix;
    int[] digits;
    int n;

    Node merge(Node a, Node b) {
        Node res = new Node(0, 0);
        res.len = a.len + b.len;
        res.val = (a.val * pow10[b.len] + b.val) % MOD;
        return res;
    }

    void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(digits[l], 1);
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    Node queryTree(int idx, int l, int r, int ql, int qr) {
        if (ql > r || qr < l)
            return new Node(0, 0);

        if (ql <= l && r <= qr)
            return tree[idx];

        int mid = (l + r) / 2;

        Node left = queryTree(idx * 2, l, mid, ql, qr);
        Node right = queryTree(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] sumAndMultiply(String s, int[][] queries) {

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> vals = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos.add(i);
                vals.add(d);
            }
        }

        n = vals.size();

        int[] ans = new int[queries.length];

        if (n == 0)
            return ans;

        digits = new int[n];

        for (int i = 0; i < n; i++)
            digits[i] = vals.get(i);

        prefix = new int[n + 1];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + digits[i];

        pow10 = new int[n + 1];
        pow10[0] = 1;

        for (int i = 1; i <= n; i++)
            pow10[i] = (int) ((long) pow10[i - 1] * 10 % MOD);

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            Node cur = queryTree(1, 0, n - 1, left, right);

            long sum = prefix[right + 1] - prefix[left];

            ans[i] = (int) (cur.val * (sum % MOD) % MOD);
        }

        return ans;
    }

    int lowerBound(ArrayList<Integer> a, int x) {
        int l = 0, r = a.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (a.get(mid) >= x)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    int upperBound(ArrayList<Integer> a, int x) {
        int l = 0, r = a.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (a.get(mid) > x)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}