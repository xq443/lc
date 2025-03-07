package Uber;
import java.util.*;

class Morris {
  static class Node {
    int data;
    Node left, right;

    Node(int x) {
      data = x;
      left = right = null;
    }
  }

  // Function for inorder traversal using Morris Traversal
   public List<Integer> inOrder(Node root, int k) {
      List<Integer> ret = new ArrayList<>();
      Node curr = root;
      int count = 0;

      while (curr != null) {
        if (curr.right == null) {

          // If no right child, visit this node and go left
          // ret.add(curr.data);
          count++;
          if (count == k) {
            ret.add(curr.data);
          }
          curr = curr.left;
        }
        else {

          // Find the inorder predecessor of curr
          Node prev = curr.right;
          while (prev.left != null && prev.left != curr) {
            prev = prev.left;
          }

          // Make curr the left child of its inorder predecessor
          if (prev.left == null) {
            prev.left = curr;
            curr = curr.right;
          }
          else {

            // Revert the changes made in the tree structure
            prev.left = null;
            //ret.add(curr.data);
            count++;
            if (count == k) {
              ret.add(curr.data);
            }
            curr = curr.left;
          }
        }
     }
    return ret;
  }

  public static void main(String[] args) {
    Morris m = new Morris();

    // Representation of input binary tree:
    //           7
    //          / \
    //         5   9
    //        /   / \
    //       1   8  15
    Node root = new Node(7);
    root.left = new Node(5);
    root.right = new Node(9);
    root.left.left = new Node(1);
    root.right.right = new Node(15);
    root.right.left = new Node(8);

    List<Integer> res = m.inOrder(root, 5);

    for (int data : res) {
      System.out.print(data + " ");
    }
  }
}

// TC: O(N)
// SC: O(1)

