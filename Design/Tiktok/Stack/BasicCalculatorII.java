package Tiktok.Stack;

import java.util.Stack;

public class BasicCalculatorII {
  public int calculate(String s) {
    if(s.isEmpty()) return 0;
    int n = s.length();
    int curr = 0, ret = 0, prev = 0;
    char sign = '+';
    for(int i = 0; i < s.length(); i++) {
      // extract number from string
      if(Character.isDigit(s.charAt(i))) {
        curr = s.charAt(i) - '0';
        while(i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
          curr = curr * 10 + s.charAt(i + 1) - '0';
          i++;
        }
        if(sign == '+') {
          ret += curr;
          prev = curr;
        } else if (sign == '-') {
          ret -= curr;
          prev = curr;
        } else if (sign == '*') {
          ret -= prev;
          ret += prev * curr;
          prev = curr;
        } else {
          ret -= prev;
          ret += prev / curr;
          prev = curr;
        }
        curr = 0;
      } else if (s.charAt(i) != ' ') {
        sign = s.charAt(i);
      }
    }
    return ret;
  }

  public int calculate_stack(String s) {
    int n = s.length();
    int ret = 0, curr = 0;
    char sign = '+';
    Stack<Integer> stack = new Stack<>();
    for(int i = 0; i < n; i++) {
      if(Character.isDigit(s.charAt(i))) {
        curr = s.charAt(i) - '0';
        while(i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
          curr = curr * 10 + s.charAt(i + 1) - '0';
          i++;
        }
        if(sign == '+') {
          stack.push(curr);
        } else if (sign == '-') {
          stack.push(-curr);
        } else if (sign == '*') {
          stack.push(stack.pop() * curr);
        } else {
          stack.push(stack.pop() / curr);
        }
        curr = 0;
      } else if (s.charAt(i) != ' ') {
        sign = s.charAt(i);
      }
    }

    for(int num : stack) {
      ret += num;
    }
    return ret;
  }

  public static void main(String[] args) {
    BasicCalculatorII b = new BasicCalculatorII();
    System.out.println(b.calculate("3+2* 2"));
    System.out.println(b.calculate("3/2"));
    System.out.println(b.calculate("3+5/ 2"));
    System.out.println(b.calculate_stack("3+2* 2"));
    System.out.println(b.calculate_stack("3/2"));
    System.out.println(b.calculate_stack("3+5/ 2"));
  }
}
