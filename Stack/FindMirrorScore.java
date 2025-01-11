import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FindMirrorScore {
  public long calculateScore(String s) {
    long ret = 0;
    Map<Integer, Stack<Integer>> map = new HashMap<>(); // key: char, value: position index
    for(int i = 0; i < 26; i++) {
      map.put(i, new Stack<>());
    }

    for(int i = 0; i < s.length(); i++) {
      int curr = s.charAt(i) - 'a';
      int mirror = 25 - curr;
      if(!map.get(mirror).isEmpty()) {
        int position = map.get(mirror).pop(); // closet unmarked
        ret += i - position;
      } else {
        map.get(curr).push(i);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    FindMirrorScore f = new FindMirrorScore();
    System.out.println(f.calculateScore("aczzx"));
  }
}
