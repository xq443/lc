package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctChars {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    if (s == null || s.isEmpty()) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, right = 0;
    int ret = 0;

    while (right < s.length()) {
      map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

      while(map.size() > 2) {
        map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
        if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
        left++;
      }

      ret = Math.max(ret, right - left + 1);
      right++;
    }

    return ret;
  }

  public static void main(String[] args) {
    LongestSubstringWithAtMostTwoDistinctChars l =  new LongestSubstringWithAtMostTwoDistinctChars();
    System.out.println(l.lengthOfLongestSubstringTwoDistinct("eceba"));
    System.out.println(l.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
  }
}
