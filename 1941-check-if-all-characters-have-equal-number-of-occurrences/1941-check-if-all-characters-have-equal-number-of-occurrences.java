class Solution {
    public boolean areOccurrencesEqual(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int expected = 0;

        for (int f : freq) {
            if (f != 0) {
                expected = f;
                break;
            }
        }

        for (int f : freq) {
            if (f != 0 && f != expected) {
                return false;
            }
        }

        return true;
    }
}