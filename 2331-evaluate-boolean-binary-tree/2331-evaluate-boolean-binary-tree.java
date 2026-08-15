class Solution {
    public boolean evaluateTree(TreeNode root) {
        
        // Leaf node
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        // Recursively evaluate left and right subtrees
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        // 2 = OR
        if (root.val == 2) {
            return left || right;
        }

        // 3 = AND
        return left && right;
    }
}