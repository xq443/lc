package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctChars {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if(s == null || s.isEmpty()) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, right = 0;

    int ret = 0;

    while(right < s.length()) {
      char c = s.charAt(right);
      map.put(c, map.getOrDefault(c, 0) + 1);
      while (map.size() > k) {
        char leftChar = s.charAt(left);
        map.put(leftChar, map.get(leftChar) - 1);
        if(map.get(leftChar) == 0) map.remove(leftChar);
        left++;
      }

      ret = Math.max(ret, right - left + 1);
      right++;
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestSubstringwithAtMostKDistinctChars l = new LongestSubstringwithAtMostKDistinctChars();
    System.out.println(l.lengthOfLongestSubstringKDistinct("eceba", 2));
    System.out.println(l.lengthOfLongestSubstringKDistinct("aa", 1));
  }
}
