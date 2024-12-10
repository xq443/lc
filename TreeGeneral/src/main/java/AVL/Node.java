package AVL;

public class Node {
  int value;
  Node left, right;
  Node parent;

  public Node(int value, Node left, Node right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public Node(int value) {
    this.value = value;
  }
}

