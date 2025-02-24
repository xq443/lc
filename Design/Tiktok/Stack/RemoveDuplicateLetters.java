package Tiktok.Stack;

import java.util.Stack;

public class RemoveDuplicateLetters {
  public String removeDuplicateLetters(String s) {
    int[] freq = new int[26];
    StringBuilder ret = new StringBuilder();
    Stack<Character> stack = new Stack<>();
    boolean[] visited = new boolean[26];

    for(int i = 0; i < s.length(); i++) {
      freq[s.charAt(i) - 'a']++;
    }

    for(int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      freq[ch - 'a']--;
      if(visited[ch - 'a']) continue;
      while(!stack.isEmpty() && stack.peek() > ch && freq[stack.peek() - 'a'] > 0) {
        visited[stack.pop() - 'a'] = false;
      }
      stack.push(ch);
      visited[ch - 'a'] = true;
    }

    while(!stack.isEmpty()) {
      ret.insert(0, stack.pop());
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    RemoveDuplicateLetters r = new RemoveDuplicateLetters();
    System.out.println(r.removeDuplicateLetters("bcabc"));
  }
}
