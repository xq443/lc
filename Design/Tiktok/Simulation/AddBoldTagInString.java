package Tiktok.Simulation;

public class AddBoldTagInString {
  public String addBoldTag(String s, String[] dict) {
    boolean[] temp = new boolean[s.length()];

    // mark the bold position
    for(String word : dict) {
      int start = 0;
      while((start = s.indexOf(word, start)) != -1) {
        for(int i = start; i < start + word.length(); i++) {
          temp[i] = true;
        }
        start += 1;
      }
    }

    // build the result
    StringBuilder ret = new StringBuilder();
    for(int i = 0; i < s.length(); i++) {
      if(temp[i]) {
        ret.append("<b>");
        int j = i;
        while(j < s.length() && temp[j]) {
          ret.append(s.charAt(j));
          j += 1;
        }
        ret.append("</b>");
        i = j;
      }
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    AddBoldTagInString a = new AddBoldTagInString();
    String s = "abcxyz123";
    String[] words = new String[]{"abc","123"};

    String s1 = "aaabbb";
    String[] words1 = new String[]{"aa","b"};
    System.out.println(a.addBoldTag(s1, words1));
  }
}
