package Snowflake.wordsearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
  Set<String> set = new HashSet<String>();
  public static class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
  }

  public TrieNode insert(String[] word) {
    TrieNode root = new TrieNode();
    for(String s : word) {
      TrieNode curr = root;
      for(char c : s.toCharArray()) {
        if(curr.children[c - 'a'] == null) {
          curr.children[c - 'a'] = new TrieNode();
        }
        curr = curr.children[c - 'a'];
      }
      curr.isEnd = true;
    }
    return root;
  }

  public List<String> findWords(char[][] board, String[] words) {
    List<String> ret = new ArrayList<>();
    if(board == null || board.length == 0 || board[0].length == 0) return ret;

    // build up trie
    TrieNode root = insert(words); // O(L) O(L)
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        dfs(board, words, i, j, root, ""); // O(MN * 4^l)
      }
    }
    ret.addAll(set);
    return ret;
  }

  public void dfs(char[][] board, String[] words, int i, int j, TrieNode node, String sub) {
    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '!') return;
    char curr = board[i][j];
    if(node.children[curr - 'a'] == null) return;

    node = node.children[curr - 'a'];
    sub += curr;

    if(node.isEnd) set.add(sub);

    board[i][j] = '!';
    dfs(board, words, i + 1, j, node, sub);
    dfs(board, words, i - 1, j, node, sub);
    dfs(board, words, i, j + 1, node, sub);
    dfs(board, words, i, j - 1, node, sub);
    board[i][j] = curr;
  }

  // TC: O(MN * 4^K) + O(L) where l is the total length of the words combined
  // SC : O(L) + O(K) where k is the max length of the word, also is the depth of recursion
}
