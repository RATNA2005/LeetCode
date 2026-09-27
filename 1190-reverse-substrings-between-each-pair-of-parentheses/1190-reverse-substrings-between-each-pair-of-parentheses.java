import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder curr = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before '('
                stack.push(curr);
                curr = new StringBuilder();

            } else if (ch == ')') {
                // Reverse content inside parentheses
                curr.reverse();

                // Add it to the previous string
                StringBuilder prev = stack.pop();
                prev.append(curr);
                curr = prev;

            } else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}