package Snowflake;

public class LeafSimilar {
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    return dfs(root1, root2);
  }

  // 深度优先遍历比较树的叶节点
  private boolean dfs(TreeNode node1, TreeNode node2) {
    // 如果两个节点都为 null，说明都到达了树的末端
    if (node1 == null && node2 == null) {
      return true;
    }

    // 如果只有一个节点为 null，或者节点值不一致，则返回 false
    if (node1 == null || node2 == null) {
      return false;
    }

    // 如果当前节点是叶节点（没有子节点），则返回它们的值是否一致
    if (node1.left == null && node1.right == null && node2.left == null && node2.right == null) {
      return node1.val == node2.val;
    }

    // 如果当前节点不是叶节点，则继续递归地检查左右子树
    return dfs(node1.left, node2.left) && dfs(node1.right, node2.right);
  }

  // 测试用例
  public static void main(String[] args) {
    TreeNode root1 = new TreeNode(3);
    root1.left = new TreeNode(5);
    root1.right = new TreeNode(1);
    root1.left.left = new TreeNode(6);
    root1.left.right = new TreeNode(2);


    TreeNode root2 = new TreeNode(3);
    root2.left = new TreeNode(5);
    root2.right = new TreeNode(1);
    root2.left.left = new TreeNode(6);
    root2.left.right = new TreeNode(2);

    LeafSimilar solution = new LeafSimilar();
    System.out.println(solution.leafSimilar(root1, root2));  // Output: true
  }
}
