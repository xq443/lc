package Snowflake.ThermostateDB;

import java.util.ArrayList;
import java.util.List;


class BST {
  static class Node {
    Record record;
    Node left, right;

    public Node(Record record) {
      this.record = record;
      this.left = this.right = null;
    }
  }


 static class Record {
    String content;
    long timestamp;

    public Record(String content, long timestamp) {
      this.content = content;
      this.timestamp = timestamp;
    }
  }

  private Node root;

  public BST() {
    root = null;
  }

  // Add a record to the BST
  public void add(Record record) {
    root = addRecursive(root, record);
  }

  private Node addRecursive(Node node, Record record) {
    if (node == null) {
      return new Node(record);
    }

    if (record.timestamp < node.record.timestamp) {
      node.left = addRecursive(node.left, record);
    } else if (record.timestamp > node.record.timestamp) {
      node.right = addRecursive(node.right, record);
    }

    return node;
  }

  // Remove a record by timestamp
  public void remove(long timestamp) {
    root = removeRecursive(root, timestamp);
  }

  private Node removeRecursive(Node node, long timestamp) {
    if (node == null) {
      return null;
    }

    if (timestamp < node.record.timestamp) {
      node.left = removeRecursive(node.left, timestamp);
    } else if (timestamp > node.record.timestamp) {
      node.right = removeRecursive(node.right, timestamp);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      node.record = minValue(node.right);
      node.right = removeRecursive(node.right, node.record.timestamp);
    }

    return node;
  }

  private Record minValue(Node node) {
    Record minValue = node.record;
    while (node.left != null) {
      minValue = node.left.record;
      node = node.left;
    }
    return minValue;
  }

  // Search for a record by timestamp
  public Record search(long timestamp) {
    return searchRecursive(root, timestamp);
  }

  private Record searchRecursive(Node node, long timestamp) {
    if (node == null || node.record.timestamp == timestamp) {
      return node == null ? null : node.record;
    }

    if (timestamp < node.record.timestamp) {
      return searchRecursive(node.left, timestamp);
    }

    return searchRecursive(node.right, timestamp);
  }

  // Search for all records between two timestamps
  public List<Record> search(long timestamp1, long timestamp2) {
    List<Record> result = new ArrayList<>();
    searchInRangeRecursive(root, timestamp1, timestamp2, result);
    return result;
  }

  private void searchInRangeRecursive(Node node, long timestamp1, long timestamp2, List<Record> result) {
    if (node == null) {
      return;
    }

    if (timestamp1 < node.record.timestamp) {
      searchInRangeRecursive(node.left, timestamp1, timestamp2, result);
    }

    if (timestamp1 <= node.record.timestamp && timestamp2 >= node.record.timestamp) {
      result.add(node.record);
    }

    if (timestamp2 > node.record.timestamp) {
      searchInRangeRecursive(node.right, timestamp1, timestamp2, result);
    }
  }

  // For testing purposes, print the BST
  public void printTree() {
    printTreeRecursive(root);
  }

  private void printTreeRecursive(Node node) {
    if (node != null) {
      printTreeRecursive(node.left);
      System.out.println("Timestamp: " + node.record.timestamp + ", Content: " + node.record.content);
      printTreeRecursive(node.right);
    }
  }

  public void main(String[] args) {
    BST bst = new BST();

    bst.add(new Record("Record 1", 1627459200));
    bst.add(new Record("Record 2", 1627462800));
    bst.add(new Record("Record 3", 1627466400));
    bst.add(new Record("Record 4", 1627470000));

    // Print the tree in order
    bst.printTree();

    // Search for a specific timestamp
    System.out.println("\nSearch for timestamp 1627462800:");
    Record record = bst.search(1627462800);
    if (record != null) {
      System.out.println("Found: " + record.content);
    } else {
      System.out.println("Not found.");
    }

    // Search for records in a range
    System.out.println("\nSearch for records between 1627460000 and 1627470000:");
    List<Record> recordsInRange = bst.search(1627460000, 1627470000);
    for (Record r : recordsInRange) {
      System.out.println("Found: " + r.content);
    }

    // Remove a record
    bst.remove(1627462800);

    // Print the tree after removal
    System.out.println("\nTree after removal:");
    bst.printTree();
  }
}
