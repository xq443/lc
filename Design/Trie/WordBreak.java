package Trie;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
  static class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
      this.children = new TrieNode[26];
      this.isEnd = false;
    }
  }

  TrieNode root;
  int[] memo = new int[300];
  public boolean wordBreak(String s, List<String> wordDict){
    root = new TrieNode();
    // insert
    for(String word: wordDict){
      TrieNode node = root;
      for(int i = 0; i < word.length(); i++){
        char ch = word.charAt(i);
        if(node.children[ch - 'a'] == null){
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.isEnd = true;
    }

    Arrays.fill(memo, -1);
    return dfs(s, 0);
  }

  public boolean dfs(String s, int index){
    if(memo[index] == 1) return false;
    if(index == s.length()) return true;
    TrieNode node = root;
    for(int i = index; i < s.length(); i++){
      char ch = s.charAt(i);
      if(node.children[ch - 'a'] != null){
        node = node.children[ch - 'a'];
        if(node.isEnd && dfs(s, i + 1)) return true;
      } else break;
    }
    memo[index] = 1; // avoid reexploring the failing path
    return false;
  }
}
