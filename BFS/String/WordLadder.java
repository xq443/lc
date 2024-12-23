package String;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> set = new HashSet<>(wordList);
    if (!set.contains(endWord))
      return 0;

    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);

    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    int ret = 1; // # number of words included in the transformation process
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String curr = queue.poll();
        assert curr != null;
        if (curr.equals(endWord)) return ret;
        for (int j = 0; j < curr.length(); j++) {
          for (int k = 'a'; k <= 'z'; k++) {
            char[] temp = curr.toCharArray();
            temp[j] = (char) k;
            String newString = new String(temp);

            if (!visited.contains(newString) && set.contains(newString)) {
              queue.add(newString);
              visited.add(newString);
            }
          }
        }
      }
      ret++;
    }
    return 0;
  }

  public static void main(String[] args) {
    WordLadder w = new WordLadder();
    String beginWord = "hit", endWord = "cog";
    List<String> wordList = List.of(new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
    System.out.println(w.ladderLength(beginWord, endWord, wordList));
  }
}
