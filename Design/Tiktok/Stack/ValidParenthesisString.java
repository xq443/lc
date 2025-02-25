package Tiktok.Stack;

public class ValidParenthesisString {
  public boolean checkValidString(String s) {
    int min = 0, max = 0;
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(c == '(') {
        max++;
        min++;
      } else if (c == ')') {
        max--;
        min--;
      } else if (c == '*') {
        max++;
        min--;
      }
      if(max < 0) return false;
      min = Math.max(min, 0); // if min < 0, we offset as 0
    }
    return min == 0;
  }

  public static void main(String[] args) {
    ValidParenthesisString v = new ValidParenthesisString();
    System.out.println(v.checkValidString("(*()"));
  }
}
