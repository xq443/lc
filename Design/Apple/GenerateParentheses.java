package Apple;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  List<String> ret;
  public List<String> generateParenthesis(int n) {
    ret = new ArrayList<>();
    generateParenthesisDfs(n, n, "");
    return ret;
  }

  public void generateParenthesisDfs(int left, int right, String curr) {
    if (left == 0 && right == 0) {
      ret.add(curr);
      return;
    }
    if (left > 0) {
      generateParenthesisDfs(left - 1, right, curr + "(");
    }
    if (right > left) {
      generateParenthesisDfs(left, right - 1, curr + ")");
    }
  }

  List<String> result;
  public List<String> generateParenthesis_backtracking(int n) {
    result = new ArrayList<>();
    StringBuilder curr = new StringBuilder();
    dfs(n, n, curr);
    return result;
  }

  public void dfs (int l, int r, StringBuilder curr) {
    if (l == 0 && r == 0) {
      result.add(new String(curr));
      return;
    }

    if(l > 0) {
      curr.append("(");
      dfs(l - 1, r, curr);
      curr.deleteCharAt(curr.length() - 1);
    }
    if(r > l) {
      curr.append(")");
      dfs(l, r - 1, curr);
      curr.deleteCharAt(curr.length() - 1);
    }
  }

  public static void main(String[] args) {
    GenerateParentheses g = new GenerateParentheses();
    System.out.println(g.generateParenthesis_backtracking(3));
  }
}
