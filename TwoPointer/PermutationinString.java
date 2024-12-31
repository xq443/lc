public class PermutationinString {
  public boolean checkInclusion(String s1, String s2) {
    int size = s1.length();
    if(size > s2.length()) return false;

    int[] char1 = new int[26];
    int[] char2 = new int[26];

    for(int i = 0; i < s1.length(); i++) {
      char1[s1.charAt(i) - 'a']++;
      char2[s2.charAt(i) - 'a']++;
    }

    if(match(char1, char2)) return true;

    for(int i = 1; i <= s2.length() - size; i++) {
      char2[s2.charAt(i - 1) - 'a']--;
      char2[s2.charAt(i + size - 1) - 'a']++;
      if(match(char1, char2)) return true;
    }
    return false;
  }

  private boolean match(int[] char1, int[] char2) {
    for(int i = 0; i < 26; i++) {
      if(char1[i] != char2[i]) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    PermutationinString ps = new PermutationinString();
    System.out.println(ps.checkInclusion("ab", "eidbaooo"));
    System.out.println(ps.checkInclusion("ab", "eidboaoo"));
  }
}
