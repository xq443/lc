package String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
  String beginWord;
  List<List<String>> ret = new ArrayList<>();
  Map<String, Set<String>> prev = new HashMap<>();

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    this.beginWord = beginWord;
    wordList.add(beginWord);

    Set<String> set = new HashSet<>(wordList);
    if(!set.contains(endWord)) return ret;

    // build up graph
    Map<String, Set<String>> next = new HashMap<>();
    for(String word : wordList){
      for(int i = 0; i < word.length(); i++){
        for(char ch = 'a'; ch <= 'z'; ch++){
          StringBuilder temp = new StringBuilder(word);
          temp.setCharAt(i, ch);
          if(temp.toString().equals(word)) continue;

          if(set.contains(temp.toString())) {
            next.computeIfAbsent(word, k -> new HashSet<>()).add(temp.toString());
          }
        }
      }
    }

    // bfs
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    visited.add(beginWord);

    int flag = 0;

    while(!queue.isEmpty()){
      int size = queue.size();
      Set<String> newVisited = new HashSet<>();

      for(int i = 0; i < size; i++){
        String curr = queue.poll();

        // explore neighbours
        for(String neighbours : next.getOrDefault(curr, new HashSet<>())){
          if(visited.contains(neighbours)) continue;
          newVisited.add(neighbours);
          prev.computeIfAbsent(neighbours, k -> new HashSet<>()).add(curr);
          if(neighbours.equals(endWord)) flag = 1;
        }
      }

      for(String word : newVisited){
        visited.add(word);
        queue.add(word);
      }

      if(flag == 1) break; // the shortest # of elements in the path should be the same
     // if we have found the combo, there is no need to further explore
    }

    if(flag == 0) return new ArrayList<>();


    List<String> path = new ArrayList<>();
    path.add(endWord);
    buildLineage(endWord, path);

    return ret;
  }

  private void buildLineage(String curr, List<String> path){
    if(curr.equals(beginWord)) {
      List<String> reverse = new ArrayList<>(path);
      Collections.reverse(reverse);
      ret.add(reverse);
      return;
    }

    for(String pre : prev.getOrDefault(curr, new HashSet<>())){
      path.add(pre);
      buildLineage(pre, path);
      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    WordLadderII w = new WordLadderII();
    String beginWord = "hit", endWord = "cog";
    List<String> wordList = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));
    System.out.println(w.findLadders(beginWord, endWord, wordList));
  }
}

