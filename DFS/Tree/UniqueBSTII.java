package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UniqueBSTII {
  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> ret = new ArrayList<>();
    if(n == 0) return ret;
    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> ret = new ArrayList<>();
    if (start > end) {
      ret.add(null);
      return ret;
    }

    for (int i = start; i <= end; i++) {
      List<TreeNode> left = generateTrees(start, i - 1);
      List<TreeNode> right = generateTrees(i + 1, end);
      for (TreeNode l : left) {
        for (TreeNode r : right) {
          TreeNode root = new TreeNode(i);
          root.left = l;
          root.right = r;
          ret.add(root);
        }
      }
    }
    return ret;
  }

  private List<Integer> printTree(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node != null) {
        result.add(node.val);
        queue.add(node.left);
        queue.add(node.right);
      } else {
        result.add(null);
      }
    }

    return result;
  }
}
