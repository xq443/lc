package Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakII {
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
  List<String> ret;
  public List<String> wordBreak(String s, List<String> wordDict) {
    this.root = new TrieNode();
    this.ret = new ArrayList<>(); // Fixed: Initialize the list
    for(String word: wordDict) {
      TrieNode node = this.root;
      for(int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        if(node.children[ch - 'a'] == null) {
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.isEnd = true;
    }

    Arrays.fill(memo, -1);
    List<String> temp = new ArrayList<>();
    dfs(s, 0, temp);
    return ret;
  }

  boolean dfs(String s, int index, List<String> temp) {
    if (memo[index] == 1)
      return false;
    if (index == s.length()) {
      StringBuilder sub = new StringBuilder();
      for (String word : temp) {
        sub.append(word).append(" ");
      }
      sub.deleteCharAt(sub.length() - 1);
      ret.add(sub.toString());
      return true;
    }

    TrieNode node = this.root;
    boolean flag = false;

    for (int i = index; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (node.children[ch - 'a'] != null) {
        node = node.children[ch - 'a'];
        if (node.isEnd) { // s[index : i] is a word
          temp.add(s.substring(index, i + 1));
          if (dfs(s, i + 1, temp)) {
            flag = true;
          }
          temp.remove(temp.size() - 1);
        }
      } else
        break;
    }
      if (!flag)
        memo[index] = 1;
      return flag;
  }

  public static void main(String[] args) {
    WordBreakII w = new WordBreakII();
    String s = "catsanddog";
    List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
    System.out.println(w.wordBreak(s, wordDict));
  }
}
