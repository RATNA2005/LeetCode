class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // Check if left child exists
        if (root.left != null) {
            // Check if left child is a leaf
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                // Recursively find left leaves
                sum += sumOfLeftLeaves(root.left);
            }
        }

        // Find left leaves in the right subtree
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }
}