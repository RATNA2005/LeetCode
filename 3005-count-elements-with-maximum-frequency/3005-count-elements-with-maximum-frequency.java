import java.util.*;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;
        int ans = 0;

        for (int num : nums) {
            int freq = map.getOrDefault(num, 0) + 1;
            map.put(num, freq);

            if (freq > maxFreq) {
                maxFreq = freq;
                ans = freq;
            } else if (freq == maxFreq) {
                ans += freq;
            }
        }

        return ans;
    }
}