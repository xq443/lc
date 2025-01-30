package Apple;

import java.util.Stack;

public class DecodeString {
  public String decodeString(String s) {
    Stack<String> str = new Stack<>();
    Stack<Integer> nums = new Stack<>();
    String curr = "";

    for(int i = 0; i < s.length(); i++) {
      if(Character.isDigit(s.charAt(i))) {
        int temp = i;
        while(temp < s.length() && Character.isDigit(s.charAt(temp))) {
          temp++;
        }
        int num = Integer.parseInt(s.substring(i, temp));
        nums.push(num);
        str.push(curr);
        curr = "";
      } else if(s.charAt(i) == ']') {
        int num = nums.pop();
        String temp = curr;
        for(int j = 0; j < num - 1; j++) {
          curr += temp;
        }
        curr = str.pop() + curr;
        str.pop();
      } else {
        curr += s.charAt(i);
      }
    }
    return curr;
  }
}
