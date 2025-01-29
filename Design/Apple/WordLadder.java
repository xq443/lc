package Apple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if(!wordSet.contains(endWord)) return 0;

    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);

    int ret = 1;
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        String curr = queue.poll();
        if(curr.equals(endWord)) return ret;
        for(int j = 0; j < curr.length(); j++) {
          for(char k = 'a'; k <= 'z'; k++) {
            char[] temp = curr.toCharArray();
            if(temp[j] == k) continue;
            temp[j] = k;
            String newWord = new String(temp);
            if(wordSet.contains(newWord) && !visited.contains(newWord)) {
              queue.add(newWord);
              visited.add(newWord);
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
    List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println(w.ladderLength(beginWord, endWord, wordList));
  }
}
