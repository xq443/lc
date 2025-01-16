package Meta;

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  TreeNode(int x) { val = x; }
  TreeNode() { }
}
