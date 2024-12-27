package Trie;

public class LongestCommonPrefixLength {
  static class TrieNode {
    TrieNode[] children = new TrieNode[10]; // 10 digits
  }

  // convert into string format to split as char array
  private void insert(TrieNode root, String str) {
    TrieNode curr = root;
    for(char ch : str.toCharArray()) {
      int index = ch - '0';
      if(curr.children[index] == null) {
        curr.children[index] = new TrieNode();
      }
      curr = curr.children[index];
    }
  }

  private int findLongestCommonPrefix(TrieNode root, String another) {
    TrieNode curr = root;
    int len = 0;
    for(char ch : another.toCharArray()) {
      int index = ch - '0';
      if(curr.children[index] == null) break;
      len++;
      curr = curr.children[index];
    }
    return len;
  }

  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    TrieNode root = new TrieNode();

    // insert into a trie
    for(int num : arr1) {
      insert(root, String.valueOf(num));
    }

    // compare with the other elements in arr2
    int ret = 0;
    for(int num : arr2) {
      ret = Math.max(ret, findLongestCommonPrefix(root, String.valueOf(num)));
    }
    return ret;
  }

  public static void main(String[] args) {
    LongestCommonPrefixLength longestCommonPrefixLength = new LongestCommonPrefixLength();
    int[] arr1 = {1,10,100};
    int[] arr2 = {1000};
    System.out.println(longestCommonPrefixLength.longestCommonPrefix(arr1, arr2));

    int[] arr3 = {1,2,3};
    int[] arr4 = {4,4,4};
    System.out.println(longestCommonPrefixLength.longestCommonPrefix(arr3, arr4));
  }
}