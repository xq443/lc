package Meta.Calculator;

public class BasicCalculatorII {
  public int calculate(String s) {
    if(s == null || s.isEmpty()) return 0;
    int curr = 0, prev = 0, ret = 0;
    char sign = '+';
    for(int i = 0; i < s.length(); i++) {
      //extract number from string
      if(Character.isDigit(s.charAt(i))) {
        curr = s.charAt(i) - '0';
        while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
          curr = curr * 10 + s.charAt(i + 1) - '0';
          i++;
        }

        //operator calculation
        if(sign == '+') {
          ret += curr;
          prev = curr;
        } else if (sign == '-') {
          ret -= curr;
          prev = -curr;
        } else if (sign == '*') {
          ret -= prev;
          ret += prev * curr;
          prev = prev * curr;
        } else if (sign == '/') {
          ret -= prev;
          ret += prev / curr;
          prev = prev / curr;
        }
        curr = 0;
      } else if (s.charAt(i) != ' ') {
        sign = s.charAt(i);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    BasicCalculatorII b = new BasicCalculatorII();
    System.out.println(b.calculate("3+2*2"));
    System.out.println(b.calculate(" 3/2 "));
    System.out.println(b.calculate(" 3+5 / 2  "));
  }
}
