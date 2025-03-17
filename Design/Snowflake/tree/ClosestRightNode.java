package Snowflake.tree;

import java.util.LinkedList;
import java.util.Queue;


public class ClosestRightNode {
  public static class TreeNode {
    int val;
    TreeNode left, right, next; // next pointer for right neighbor

    TreeNode(int val) {
      this.val = val;
      this.left = this.right = this.next = null;
    }
  }

  public static void connect(TreeNode root) {
    if (root == null) return;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      TreeNode prev = null;

      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();

        if (prev != null) {
          prev.next = curr;  // Connect previous node to current node
        }
        prev = curr;

        if (curr.left != null) queue.offer(curr.left);
        if (curr.right != null) queue.offer(curr.right);
      }

      // Last node of each level points to null
      prev.next = null;
    }
  }

  // Helper function to print next pointers (for testing)
  public static void printNextPointers(TreeNode root) {
    while (root != null) {
      TreeNode curr = root;
      while (curr != null) {
        System.out.print(curr.val + " -> ");
        curr = curr.next;
      }
      System.out.println("null");

      // Move to next level (find leftmost node)
      while (root != null && root.left == null && root.right == null) {
        root = root.next;
      }
      root = (root != null) ? (root.left != null ? root.left : root.right) : null;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.right = new TreeNode(6);

    connect(root);
    printNextPointers(root);
  }
}
