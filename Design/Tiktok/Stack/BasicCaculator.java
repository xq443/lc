package Tiktok.Stack;

class BasicCalculator{
  public int calculate(String s) {
    int ret = 0, prev = 0, curr = 0;
    char sign = '+';

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (Character.isDigit(c)) {
        curr = curr * 10 + (c - '0');
      }

      // Process when encountering an operator or reaching the end of the string
      // 3+2*2
      if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
        if (sign == '+') {
          ret += prev;
          prev = curr;
        } else if (sign == '-') {
          ret += prev;
          prev = -curr;
        } else if (sign == '*') {
          prev *= curr;
        } else if (sign == '/') {
          prev /= curr;
        }
        sign = c;
        curr = 0;
      }
    }
    return ret + prev;
  }

  public static void main(String[] args) {
    BasicCalculator c = new BasicCalculator();
    System.out.println(c.calculate("3+2*2"));  // Expected output: 7
  }
}
