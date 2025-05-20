package Tiktok.Tree;

public class BinaryTreeLongestConsecutiveSequence {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
  }

  int max = 0;
  public int longestConsecutive(TreeNode root) {
    if (root == null) return 0;
    dfs(root, 0, root.val);
    return max;
  }

  public void dfs(TreeNode node, int cur, int expected) {
    if (node == null) return;
    if(node.val == expected) {
      cur += 1;
    } else {
      cur = 1;
    }

    max = Math.max(max, cur);
    dfs(node.left, cur, node.val + 1);
    dfs(node.right, cur, node.val + 1);
  }

  public static void main(String[] args) {
    BinaryTreeLongestConsecutiveSequence b = new BinaryTreeLongestConsecutiveSequence();
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(2);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(5);
    System.out.println(b.longestConsecutive(root));
  }
}
