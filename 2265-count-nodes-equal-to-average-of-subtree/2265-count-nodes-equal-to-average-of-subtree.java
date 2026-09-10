class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // returns {sum of subtree, number of nodes}
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int sum = left[0] + right[0] + root.val;
        int count = left[1] + right[1] + 1;

        int average = sum / count;

        if (root.val == average) {
            ans++;
        }

        return new int[]{sum, count};
    }
}