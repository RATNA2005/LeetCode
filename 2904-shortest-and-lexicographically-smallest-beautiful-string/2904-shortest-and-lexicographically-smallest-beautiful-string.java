class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We have exactly k ones
            while (ones == k) {

                // Remove leading zeros
                while (left <= right && s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                // Update answer
                if (ans.equals("")
                        || current.length() < ans.length()
                        || (current.length() == ans.length()
                            && current.compareTo(ans) < 0)) {

                    ans = current;
                }

                // Remove the first 1
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }
        }

        return ans;
    }
}