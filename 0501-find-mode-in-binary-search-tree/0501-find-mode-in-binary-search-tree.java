import java.util.*;

class Solution {
    List<Integer> ans = new ArrayList<>();

    int prev = 0;
    int count = 0;
    int maxCount = 0;
    boolean first = true;

    public int[] findMode(TreeNode root) {
        inorder(root);

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        // Count current value
        if (first || root.val != prev) {
            count = 1;
            first = false;
        } else {
            count++;
        }

        // New maximum frequency
        if (count > maxCount) {
            maxCount = count;
            ans.clear();
            ans.add(root.val);
        }
        // Same maximum frequency
        else if (count == maxCount) {
            ans.add(root.val);
        }

        prev = root.val;

        inorder(root.right);
    }
}