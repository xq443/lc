package Google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatenationAllWords {
  int window;
  public List<Integer> findSubstring(String s, String[] words) {
    this.window = words[0].length();
    List<Integer> ret = new ArrayList<>();

    Map<String, Integer> map = new HashMap<>();
    for(String word : words) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    for(int i = 0; i < window; i++) {
      int start = i, end = i, cnt = 0;
      Map<String, Integer> currMap = new HashMap<>();
      while(end + window <= s.length()) {
        String curr = s.substring(end, end + window);
        end += window;
        if(map.containsKey(curr)) {
          currMap.put(curr, currMap.getOrDefault(curr, 0) + 1);
          cnt++;
          while (currMap.get(curr) > map.get(curr)) {
            String remove = s.substring(start, start + window);
            currMap.put(remove, currMap.get(remove) - 1);
            if(currMap.get(remove) == 0) currMap.remove(remove);
            start += window;
            cnt--;
          }

          if(cnt == words.length) {
            ret.add(start);
          }

        } else {
          currMap.clear();
          start = end;
          cnt = 0;
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    SubstringConcatenationAllWords m = new SubstringConcatenationAllWords();
    String s = "barfoothefoobarman";
    String[] words = {"foo","bar"};
    System.out.println(m.findSubstring(s, words));
  }
}
