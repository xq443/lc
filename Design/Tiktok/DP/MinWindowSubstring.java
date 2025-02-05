package Tiktok.DP;

public class MinWindowSubstring {
  public String minWindow(String s, String t) {
    if(t.length() > s.length()) return "";

    int[] map = new int[256];
    int left = 0, right = 0;
    int ret = Integer.MAX_VALUE;
    for(char ch : t.toCharArray()) {
      map[ch]++;
    }
    int start = 0;
    int cnt = t.length();
    char[] charS = s.toCharArray();

    while(right < charS.length) {
      // expand the window
      if(map[charS[right]] > 0)  cnt--;
      map[charS[right]]--;
      right++;

      // contract the window
      while(cnt == 0) {
        if(right - left < ret) {
          ret = right - left;
          start = left;
        }

        if(map[charS[left]] == 0) cnt++;
        map[charS[left]]++;
        left++;
      }
    }
    return ret == Integer.MAX_VALUE ? "" : s.substring(start, start + ret);
  }

  public static void main(String[] args) {
    MinWindowSubstring m = new MinWindowSubstring();
    String s = "ADOBECODEBANC", t = "ABC";
    System.out.println(m.minWindow(s, t));
  }
}
