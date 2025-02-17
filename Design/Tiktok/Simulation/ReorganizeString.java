package Tiktok.Simulation;

public class ReorganizeString {
  public String reorganizeString(String s) {
    int[] cnt = new int[26];
    for(char ch : s.toCharArray()) {
      cnt[ch - 'a']++;
    }

    int maxCnt = 0, letter = 0;
    for(int i = 0; i < cnt.length; i++) {
      if(cnt[i] > maxCnt) {
        maxCnt = cnt[i];
        letter = i;
      }
    }
    if(maxCnt > (s.length() + 1) / 2) return "";

    char[] ret = new char[s.length()];
    int idx = 0;
    while(cnt[letter] > 0) {
      ret[idx] = (char)('a' + letter);
      idx += 2;
      cnt[letter]--;
    }

    for(int i = 0; i < cnt.length; i++) {
      while(cnt[i] > 0) {
        if(idx >= s.length()) {
          idx = 1;
        }
        ret[idx] = (char)('a' + i);
        idx += 2;
        cnt[i]--;
      }
    }
    return String.valueOf(ret);
  }

  public static void main(String[] args) {
    ReorganizeString r = new ReorganizeString();
    System.out.println(r.reorganizeString("aab"));
    System.out.println(r.reorganizeString("aaab"));
  }
}
