package Snowflake;
import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParenthesis{
    public List<String> removeInvalidParentheses(String s) {
        int count = 0, left = 0, right = 0;

        // calculate the total numbers of opening and closing parentheses
        // that need to be removed in the final solution
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) left--;
                else right++;
            }
        }
        if (left == 0 && right == 0) return List.of(s);

        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        dfs(s.toCharArray(), 0, count, left, right, ret, sb);

        return ret;
    }

    private void dfs(char[] s, int p, int count, int openN, int closeN, List<String> result, StringBuilder sb) {
        if (count < 0) return; // the parentheses are invalid

        if (p == s.length) {
            if (openN == 0 && closeN == 0) { // the minimum number of invalid parentheses have been removed
                result.add(sb.toString());
            }
            return;
        }

        if (s[p] != '(' && s[p] != ')') {
            sb.append(s[p]);
            dfs(s, p + 1, count, openN, closeN, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else if (s[p] == '(') {
            int i = 1;
            while (p + i < s.length && s[p + i] == '(')
                i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i);
            dfs(s, p + i, count + i, openN, closeN, result, sb);
            sb.delete(sb.length() - i, sb.length());

            if (openN > 0) {
                // remove the current opening parenthesis
                dfs(s, p + 1, count, openN - 1, closeN, result, sb);
            }
        } else {
            int i = 1;
            while (p + i < s.length && s[p] == ')')
                i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
            sb.append(s, p, i);
            dfs(s, p + i, count - i, openN, closeN, result, sb);
            sb.delete(sb.length() - i, sb.length());

            if (closeN > 0) {
                // remove the current closing parenthesis
                dfs(s, p + 1, count, openN, closeN - 1, result, sb);
            }
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParenthesis r = new RemoveInvalidParenthesis();
        System.out.println(r.removeInvalidParentheses("(()"));
    }
}