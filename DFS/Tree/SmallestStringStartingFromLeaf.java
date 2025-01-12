package Tree;

public class SmallestStringStartingFromLeaf {
  String ret = "~"; // larger value than a to z
  public String smallestFromLeaf(TreeNode root) {
    recursionSmallestString(root, new StringBuilder());
    return ret;
  }

  public void recursionSmallestString(TreeNode node, StringBuilder temp) {
    if(node == null) return;
    temp.append((char) ('a' + node.val));
    if(node.left == null && node.right == null) {
      String curr = temp.reverse().toString();
      if(curr.compareTo(ret) < 0) {
        ret = curr;
      }
      temp.reverse();
    }

    recursionSmallestString(node.left, temp);
    recursionSmallestString(node.right, temp);
    temp.deleteCharAt(temp.length() - 1);
  }

  public static void main(String[] args) {
    SmallestStringStartingFromLeaf s = new SmallestStringStartingFromLeaf();
    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(4);
    System.out.println(s.smallestFromLeaf(root));
  }
}
