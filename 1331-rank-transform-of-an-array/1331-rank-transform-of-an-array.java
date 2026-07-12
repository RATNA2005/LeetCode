class Solution {
    public int[] arrayRankTransform(int[] arr) {

        int[] dup = arr.clone();
        Arrays.sort(dup);

        HashMap<Integer, Integer> map = new HashMap<>();

        int rank = 1;

        for (int num : dup) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}