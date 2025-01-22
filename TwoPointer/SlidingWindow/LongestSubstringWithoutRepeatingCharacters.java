package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.isEmpty()) return 0;
    Set<Character> set = new HashSet<>();
    int ret = 0;
    int left = 0, right = 0;

    while (right < s.length()) {
      char curr = s.charAt(right);
      if(!set.contains(curr)) {
        set.add(curr);
        ret = Math.max(ret, right - left + 1);
        right++;
      } else {
        set.remove(s.charAt(left));
        left++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
    System.out.println(l.lengthOfLongestSubstring("bbbb"));
    System.out.println(l.lengthOfLongestSubstring("pwwkew"));
  }
}
