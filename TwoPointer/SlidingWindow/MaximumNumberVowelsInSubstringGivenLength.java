package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class MaximumNumberVowelsInSubstringGivenLength {
  public int maxVowels(String s, int k) {
    Set<Character> set = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u'));
    int left = 0, right = 0;
    int n = s.length();
    int ret = 0;
    int cnt = 0;

    while (right < n) {
      if (set.contains(s.charAt(right))) cnt++;
      while (right - left + 1 > k) {
        if(set.contains(s.charAt(left))) cnt--;
        left++;
      }
      ret = Math.max(ret, cnt);
      right++;
     }

    return ret;
  }

  public static void main(String[] args) {
    MaximumNumberVowelsInSubstringGivenLength m = new MaximumNumberVowelsInSubstringGivenLength();
    System.out.println(m.maxVowels("abciiidef", 3));
    System.out.println(m.maxVowels("aeiou", 2));
    System.out.println(m.maxVowels("leetcode", 3));
  }
}
