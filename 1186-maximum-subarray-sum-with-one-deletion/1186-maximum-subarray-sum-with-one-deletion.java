class Solution {
    public int maximumSum(int[] arr) {

        int keep = arr[0];
        int delete = 0;
        int ans = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int newDelete = Math.max(keep, delete + arr[i]);

            keep = Math.max(arr[i], keep + arr[i]);

            delete = newDelete;

            ans = Math.max(ans, Math.max(keep, delete));
        }

        return ans;
    }
}