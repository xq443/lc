package Trie;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionSys {

  public static class TrieNode {
    boolean isEnd;
    TrieNode[] children;

    public TrieNode() {
      this.isEnd = false;
      this.children = new TrieNode[26];
    }
  }

  TrieNode root = new TrieNode();
  public void insert(String word) {
    TrieNode curr = root;
    for(char ch : word.toCharArray()) {
      if(curr.children[ch - 'a'] == null) {
        curr.children[ch - 'a'] = new TrieNode();
      }
      curr = curr.children[ch - 'a'];
    }
    curr.isEnd = true;
  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    for(String product : products) {
      insert(product);
    }

    List<List<String>> ret = new ArrayList<>();
    StringBuilder pre = new StringBuilder();
    TrieNode curr = root;
    for(char ch : searchWord.toCharArray()) {
      if(curr.children[ch - 'a'] == null) {
        while(ret.size() <= searchWord.length()) {
          ret.add(new ArrayList<>());
        }
        return ret;
      }
      curr = curr.children[ch - 'a'];
      pre.append(ch);

      List<String> subret = new ArrayList<>();
      dfs(curr, subret, pre.toString());
      ret.add(subret);
    }
    return ret;
  }

  private void dfs(TrieNode curr, List<String> subret, String pre) {
    if(subret.size() == 3) return;
    if(curr.isEnd) subret.add(pre);

    for(int i = 0; i < 26; i++) {
      if(curr.children[i] == null) continue;
      dfs(curr.children[i], subret, pre + (char)('a' + i));
    }
  }

  public static void main(String[] args) {
    SearchSuggestionSys s = new SearchSuggestionSys();
    String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
    String searchWord = "mouse";
    System.out.println(s.suggestedProducts(products, searchWord));
  }
}
