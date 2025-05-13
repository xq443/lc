package Meta;

import java.util.Stack;

public class ValidParenthesis {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for(char ch : s.toCharArray()) {
      if(ch == '(') {
        stack.push(')');
      } else if (ch == '{') {
        stack.push('}');
      } else if (ch == '[') {
        stack.push(']');
      } else if (stack.isEmpty() || stack.pop() != ch) return false;
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    ValidParenthesis v = new ValidParenthesis();
    System.out.println(v.isValid("([])"));
  }
}
