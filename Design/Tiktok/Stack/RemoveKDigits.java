package Tiktok.Stack;

public class RemoveKDigits {
  public String removeKdigits(String num, int k) {
    StringBuilder ret = new StringBuilder();
    for(int i = 0; i < num.length(); i++) {
      char curr = num.charAt(i);
      while(!ret.isEmpty() && ret.charAt(ret.length()-1) > curr && k > 0) {
        ret.deleteCharAt(ret.length()-1);
        k--;
      }
      ret.append(curr);
    }

    // if still left k
    while(!ret.isEmpty() && k > 0) {
      ret.deleteCharAt(ret.length()-1);
      k--;
    }

    // remove leading zero
    while(ret.length() > 1 && ret.charAt(0) == '0') {
      ret.deleteCharAt(0);
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    RemoveKDigits r = new RemoveKDigits();
    String num = "1432219";
    int k = 3;
    System.out.println(r.removeKdigits(num, k));
    System.out.println(r.removeKdigits("10200", 1));
    System.out.println(r.removeKdigits("10", 1));
  }
}
