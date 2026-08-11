class Solution {

    // Find height by going to the left
    int leftHeight(TreeNode root) {
        int height = 0;

        while (root != null) {
            height++;
            root = root.left;
        }

        return height;
    }

    // Find height by going to the right
    int rightHeight(TreeNode root) {
        int height = 0;

        while (root != null) {
            height++;
            root = root.right;
        }

        return height;
    }

    public int countNodes(TreeNode root) {

        // Empty tree
        if (root == null) {
            return 0;
        }

        int lh = leftHeight(root);
        int rh = rightHeight(root);

        // Perfect binary tree
        if (lh == rh) {
            return (1 << lh) - 1;
        }

        // Otherwise count left and right subtrees
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}