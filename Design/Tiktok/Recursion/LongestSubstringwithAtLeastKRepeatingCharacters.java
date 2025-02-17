package Tiktok.Recursion;

public class LongestSubstringwithAtLeastKRepeatingCharacters {
  public int longestSubstring(String s, int k) {
    return helper(s.toCharArray(), 0, s.length(), k);
  }

  public int helper(char[] s, int left, int right, int k) {
    // termination condition
    if(right - left < k) return 0;

    int[] cnt = new int[26];
    for(int i = left; i < right; i++) {
      int idx = s[i] - 'a';
      cnt[idx]++;
    }

    for(int i = 0; i < 26; i++){
      if(cnt[i] > 0 && cnt[i] < k) {
        // which position idx is i
        for(int j = left; j < right; j++){
          if(s[j] == 'a' + i) {
            int lower = helper(s, left, j, k);
            int higher = helper(s, j + 1, right, k);
            return Math.max(lower, higher);
          }
        }
      }
    }
    return right - left;
  }

  public static void main(String[] args) {
    LongestSubstringwithAtLeastKRepeatingCharacters l = new LongestSubstringwithAtLeastKRepeatingCharacters();
    System.out.println(l.longestSubstring("aaabb", 3));
    System.out.println(l.longestSubstring("ababbc", 2));
  }
}
