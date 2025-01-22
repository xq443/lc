package SlidingWindow;

public class PermutationinString {
  public boolean checkInclusion(String s1, String s2) {
    int n1 = s1.length();
    int n2 = s2.length();
    int[] char1 = new int[26];
    int[] char2 = new int[26];

    for(int i = 0; i < n1; i++) {
      char1[s1.charAt(i)-'a']++;
      char2[s2.charAt(i)-'a']++;
    }

    if(isMatch(char1, char2)) return true;

    for(int i = 1; i <= n2 - n1; i++) {
      char2[s2.charAt(i - 1)-'a']--;
      char2[s2.charAt(i + n1 - 1)-'a']++;
      if(isMatch(char1, char2)) return true;
    }

    return false;
  }

  private boolean isMatch(int[] char1, int[] char2) {
    for(int i = 0; i < 26; i++) {
      if(char1[i] != char2[i]) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    PermutationinString p = new PermutationinString();
    System.out.println(p.checkInclusion("ab", "eidbaooo"));
    System.out.println(p.checkInclusion("ab", "eidboaoo"));
  }
}
