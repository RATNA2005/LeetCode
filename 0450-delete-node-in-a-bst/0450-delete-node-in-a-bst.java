class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {

        // Node not found
        if (root == null) {
            return null;
        }

        // Search in left subtree
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }

        // Search in right subtree
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        // Node found
        else {

            // Case 1: No left child
            if (root.left == null) {
                return root.right;
            }

            // Case 2: No right child
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            TreeNode successor = root.right;

            while (successor.left != null) {
                successor = successor.left;
            }

            // Copy successor value
            root.val = successor.val;

            // Delete successor
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }
}