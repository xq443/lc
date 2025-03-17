package Snowflake.tree;

import java.util.*;

class TreeNode {
  int val;
  TreeNode left, right;
  TreeNode(int x) { val = x; }
}

public class PerfectBinaryTree {

  // Check if a tree is perfect
  public static boolean isPerfect(TreeNode root) {
    if (root == null) return true;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      int expectedSize = (int) Math.pow(2, level);
      if (size != expectedSize) return false;

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }
      level++;
    }
    return true;
  }

  // Transform a binary tree into a perfect tree with minimum operations
  public static int transformToPerfect(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0, operations = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      int expectedSize = (int) Math.pow(2, level);
      System.out.println(level);
      System.out.println("expected size is: " + expectedSize + ", actual size is: " + size);

      if (size != expectedSize) {
        operations += Math.min(size, expectedSize - size);
      }

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }
      level++;
    }
    return operations;
  }

  public static void main(String[] args) {
    // Test case: Tree that is missing nodes in multiple levels
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);

    System.out.println("Is perfect tree? " + isPerfect(root)); // false
    System.out.println("Minimum operations to make perfect: " + transformToPerfect(root)); // Expected: 1
  }
}
