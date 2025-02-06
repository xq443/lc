package Tiktok.Stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinRemoveValidParentheses {
  public String minRemoveToMakeValid(String s) {
    Stack<Integer> stack = new Stack<>();
    for(int i = 0; i < s.length(); i++){
      char curr = s.charAt(i);
      if(Character.isAlphabetic(curr)) continue;
      else if(curr == '('){
        stack.push(i);
      } else {
        if(!stack.isEmpty() && s.charAt(stack.peek()) == '('){
          stack.pop();
        } else
          stack.push(i);
      }
    }
    StringBuilder ret = new StringBuilder();
    Set<Integer> set = new HashSet<>(stack);
    for(int i = 0; i < s.length(); i++){
      if(!set.contains(i)) ret.append(s.charAt(i));
    }
    return ret.toString();
  }

  public String minRemoveToMakeValid_cnt(String s) {
    StringBuilder temp = new StringBuilder();
    int cnt = 0;
    for(int i = 0; i < s.length(); i++){
      char curr = s.charAt(i);
      if(curr == '(') {
        cnt++;
        temp.append(curr);
      } else if(curr == ')') {
        if(cnt > 0) {
          cnt--;
          temp.append(curr);
        }
      } else temp.append(curr);
    }

    StringBuilder ret = new StringBuilder();
    for(int i = temp.length() - 1; i >= 0; i--){
      if(cnt > 0 && temp.charAt(i) == '(') cnt--;
      else ret.append(temp.charAt(i));
    }
    return ret.reverse().toString();
  }

  public static void main(String[] args) {
    MinRemoveValidParentheses m = new MinRemoveValidParentheses();
    String s = "lee(t(c)o)de)";
    System.out.println(m.minRemoveToMakeValid(s));
    System.out.println(m.minRemoveToMakeValid_cnt(s));
  }
}
