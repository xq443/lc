public class LongestCommonPrefix_Trie {
  static class TrieNode {
    boolean isEnd;
    int size;
    TrieNode[] next ;

    public TrieNode() {
      this.isEnd = false;
      this.size = 0;
      this.next = new TrieNode[26];
    }
  }

  TrieNode root; // one single root node
  public String longestCommonPrefix(String[] strs) {
    root = new TrieNode();
    if(strs == null || strs.length == 0) return "";
    for(String s : strs) {
      insert(s);
    }
    return search(strs[0], strs.length);
  }

  public void insert(String s) {
    TrieNode cur = root;
    for(char ch : s.toCharArray()) {
      if(cur.next[ch - 'a'] == null) {
        cur.next[ch - 'a'] = new TrieNode();
      }

      cur.next[ch - 'a'].size ++;
      cur = cur.next[ch - 'a'];
    }
    cur.isEnd = true;
  }

  public String search(String s, int n) {
    TrieNode cur = root;
    int idx = 0;
    for(char ch : s.toCharArray()) {
      if(cur.next[ch - 'a'] != null && cur.next[ch - 'a'].size == n) {
        idx++;
        cur = cur.next[ch - 'a'];
      } else {
        return s.substring(0, idx);
      }
    }
    return s;
  }

  public static void main(String[] args) {
    LongestCommonPrefix_Trie l =  new LongestCommonPrefix_Trie();
    String[] strs = {"flower","flow","flight"};
    System.out.println(l.longestCommonPrefix(strs));
  }
}
