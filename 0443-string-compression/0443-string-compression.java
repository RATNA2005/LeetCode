class Solution {
    public int compress(char[] chars) {

        int write = 0;
        int read = 0;

        while (read < chars.length) {

            char current = chars[read];
            int count = 0;

            // Count consecutive characters
            while (read < chars.length && chars[read] == current) {
                read++;
                count++;
            }

            // Write the character
            chars[write] = current;
            write++;

            // Write the count if greater than 1
            if (count > 1) {

                String cnt = String.valueOf(count);

                for (char ch : cnt.toCharArray()) {
                    chars[write] = ch;
                    write++;
                }
            }
        }

        return write;
    }
}