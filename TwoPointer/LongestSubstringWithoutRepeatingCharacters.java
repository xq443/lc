import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();
    int left = 0, right = 0;
    int ret = 0;
    while(right < s.length()) {
      char curr = s.charAt(right);
      if(!set.contains(curr)) {
        set.add(curr);
        right++;
        ret = Math.max(ret, right - left);
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
  }
}
