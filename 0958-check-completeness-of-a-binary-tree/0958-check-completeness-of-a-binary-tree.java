import java.util.*;

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean nullFound = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                nullFound = true;
            } else {
                // If we already encountered a null,
                // there cannot be another non-null node.
                if (nullFound) {
                    return false;
                }

                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return true;
    }
}