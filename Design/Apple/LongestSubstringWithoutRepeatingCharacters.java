package Apple;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int left = 0, right = 0;
    int ret = 0;
    Set<Character> set = new HashSet<>();
    while(right < n) {
      char curr = s.charAt(right);
      if(!set.contains(curr)) {
        set.add(curr);
        ret = Math.max(ret,  right - left + 1);
        right++;
      } else {
        set.remove(s.charAt(left));
        left++;
      }
    }
    return ret;
  }
}
