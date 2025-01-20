package Meta;

import java.util.Stack;

public class BinarySearchTreeIterator {
  Stack<TreeNode> stack;
  public BinarySearchTreeIterator(TreeNode root) {
    this.stack = new Stack<>();
    TreeNode curr = root;
    while (curr != null) {
      stack.push(curr);
      curr = curr.left;
    }
  }

  public int next() {
    TreeNode curr = stack.pop();
    TreeNode next = curr.right;
    while (next != null) {
      stack.push(next);
      next = next.left;
    }
    return curr.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public static void main(String[] args) {
    // Construct the BST
    TreeNode root = new TreeNode(7);
    root.left = new TreeNode(3);
    root.right = new TreeNode(15);
    root.right.left = new TreeNode(9);
    root.right.right = new TreeNode(20);

    BinarySearchTreeIterator bst = new BinarySearchTreeIterator(root);
    System.out.println(bst.next());
    System.out.println(bst.next());
    System.out.println(bst.hasNext());
    System.out.println(bst.next());
    System.out.println(bst.hasNext());
    System.out.println(bst.next());
    System.out.println(bst.hasNext());
    System.out.println(bst.next());
    System.out.println(bst.hasNext());
  }
}
