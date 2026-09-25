import java.util.*;

class Solution {
    private int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {

            // Union
            if (s.charAt(i) == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            }

            // Nested expression
            else if (s.charAt(i) == '{') {
                i++; // skip {

                Set<String> next = parse(s);

                i++; // skip }

                current = combine(current, next);
            }

            // Letter
            else {
                Set<String> next = new HashSet<>();
                next.add(String.valueOf(s.charAt(i)));

                current = combine(current, next);
                i++;
            }
        }

        result.addAll(current);

        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}