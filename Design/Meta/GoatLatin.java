package Meta;

import java.util.HashSet;
import java.util.Set;

public class GoatLatin {
  public String toGoatLatin(String sentence) {
    Set<Character> vowels = new HashSet<>();
    vowels.add('a');
    vowels.add('e');
    vowels.add('i');
    vowels.add('o');
    vowels.add('u');
    vowels.add('A');
    vowels.add('E');
    vowels.add('I');
    vowels.add('O');
    vowels.add('U');

    StringBuilder ret = new StringBuilder();
    String[] tokens = sentence.split(" ");

    for(int i = 0; i < tokens.length; i++) {
      StringBuilder curr = new StringBuilder(tokens[i]);
      if(!vowels.contains(curr.charAt(0))) {
        // move the first char into the end
        char first = curr.charAt(0);
        curr.deleteCharAt(0);
        curr.append(first);
      }

      curr.append("ma");
      for(int j = 0; j <= i; j++) {
        curr.append('a');
      }
      ret.append(curr);
      if(i != tokens.length - 1) {
        ret.append(' ');
      }
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    GoatLatin g = new GoatLatin();
    String word = "I speak Goat Latin";
    System.out.println(g.toGoatLatin(word));
  }
}
