import java.util.Arrays;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        boolean[] used = new boolean[m];
        int[] ans = new int[Math.min(n, m)];

        int c = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (!used[j] && nums1[i] == nums2[j]) {

                    ans[c++] = nums1[i];
                    used[j] = true;
                    break;
                }
            }
        }

        return Arrays.copyOf(ans, c);
    }
}