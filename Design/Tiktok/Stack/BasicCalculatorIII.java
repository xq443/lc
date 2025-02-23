package Tiktok.Stack;

public class BasicCalculatorIII {
  int idx;
  public int calculate(String s) {
    this.idx = 0;
    return helper(s);
  }

  public int helper(String s) {
    int ret = 0, curr = 0, prev = 0;
    char sign = '+';

    while(idx < s.length()) {
      char c = s.charAt(idx++);
      if(c == '(') {
        curr = helper(s);
      }
      if(Character.isDigit(c)) {
        curr = curr * 10 + (c - '0');
      }

      if(!Character.isDigit(c) && c != ' ' || idx == s.length()) {
        if(sign == '+') {
          ret += prev;
          prev = curr;
        } else if(sign == '-') {
          ret -= prev;
          prev = curr;
        } else if(sign == '*') {
          prev *= curr;
        } else if(sign == '/') {
          prev /= curr;
        }
        if(c == ')') return ret + prev;
        sign = c;
        curr = 0;
      }
    }
    return ret + prev;
  }

  public static void main(String[] args) {
    BasicCalculatorIII bcii = new BasicCalculatorIII();
    System.out.println(bcii.calculate("2*(5+5*2)/3+(6/2+8)"));
  }
}
