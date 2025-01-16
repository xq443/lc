package Google;

import java.util.Stack;

public class ValidParentheses {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(c == '(') stack.push(')');
      else if(c == '{') stack.push('}');
      else if(c == '[') stack.push(']');
      else if(stack.isEmpty() || stack.pop() != c) return false;
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    ValidParentheses v = new ValidParentheses();
    System.out.println(v.isValid("()"));
    System.out.println(v.isValid(""));
  }
}
