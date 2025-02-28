package Snowflake;

public class PermutationinString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int[] char1 = new int[26];
        int[] char2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            char1[s1.charAt(i) - 'a']++;
            char2[s2.charAt(i) - 'a']++;
        }

        if(isMatch(char1, char2)) return true;

        int window = s1.length();

        for(int i = 1; i <= s2.length() - window; i++) {
            char2[s2.charAt(i - 1) - 'a']--;
            char2[s2.charAt(i + window - 1) - 'a']++;
            if(isMatch(char1, char2)) return true;
        }
        return false;
    }

    public boolean isMatch(int[] char1, int[] char2) {
        for (int i = 0; i < 26; i++) {
            if(char1[i] != char2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationinString ps = new PermutationinString();
        System.out.println(ps.checkInclusion("ab", "eidbaooo"));
    }
}
