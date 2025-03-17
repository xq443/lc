package Snowflake.tree;


public class BinaryTreeAverage {
  // 返回子树的总和和节点个数，如果该节点的值不等于子树的平均值则返回 false
  public boolean checkSubtreeAverage(TreeNode root) {
    return checkSubtreeAverageHelper(root) != null;
  }

  // 辅助函数：返回子树的总和和节点个数，如果子树的根节点不符合条件，返回 null
  private Result checkSubtreeAverageHelper(TreeNode node) {
    if (node == null) {
      return new Result(0, 0);
    }

    // 如果是叶子节点，返回它自身的值和数量
    if (node.left == null && node.right == null) {
      return new Result(node.val, 1);
    }

    // 递归处理左右子树
    Result leftResult = checkSubtreeAverageHelper(node.left);
    Result rightResult = checkSubtreeAverageHelper(node.right);

    // 如果左子树或右子树不符合条件，则返回 null
    if (leftResult == null || rightResult == null) {
      return null;
    }

    // 计算当前子树的总和和节点数
    int sum = leftResult.sum + rightResult.sum + node.val;
    int count = leftResult.count + rightResult.count + 1;

    // 计算平均值，并与当前节点的值进行比较
    if (sum / count != node.val) {
      return null; // 如果不相等，返回 null
    }

    // 返回当前子树的总和和节点数
    return new Result(sum, count);
  }

  // 结果类，保存子树的总和和节点个数
  static class Result {
    int sum;
    int count;

    Result(int sum, int count) {
      this.sum = sum;
      this.count = count;
    }
  }

  public static void main(String[] args) {
    BinaryTreeAverage b = new BinaryTreeAverage();
    // 构造测试二叉树
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(8);

    // 测试
    System.out.println(b.checkSubtreeAverage(root));  // true or false
  }
}
// TC: O(N)
// SC: O(LogN) balanced tree best case / O(N) -> skewed tree worst case
