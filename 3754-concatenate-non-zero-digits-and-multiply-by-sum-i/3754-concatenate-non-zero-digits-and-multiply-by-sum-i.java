class Solution {
    public long sumAndMultiply(int n) {

        String num = String.valueOf(n);

        long x = 0;
        int sum = 0;

        for (char ch : num.toCharArray()) {
            if (ch != '0') {
                int digit = ch - '0';
                x = x * 10 + digit;
                sum += digit;
            }
        }

        return x * sum;
    }
}