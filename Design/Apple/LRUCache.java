package Apple;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  static class Node {
    int key;
    int val;
    Node prev;
    Node next;
  }

  Map<Integer, Node> map;
  Node head;
  int capacity;

  public LRUCache(int capacity) {
    this.map = new HashMap<>(capacity);
    this.head = new Node();
    this.head.prev = head;
    this.head.next = head;
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    } else {
      Node curr = map.get(key);
      moveToHead(curr);
      return curr.val;
    }
  }

  public void put(int key, int value) {
    if(map.containsKey(key)) {
      Node curr = map.get(key);
      curr.val = value;
      moveToHead(curr);
      return;
    }
    if(map.size() == capacity) {
      Node nodeToRemove = removeTail();
      map.remove(nodeToRemove.key);
    }
    Node newNode = new Node();
    newNode.key = key;
    newNode.val = value;
    map.put(key, newNode);
    moveToHeadNext(newNode);
  }

  public void moveToHead(Node curr) {
    removeNode(curr);
    moveToHeadNext(curr);
  }

  public void moveToHeadNext(Node curr) {
    curr.prev = head;
    curr.next = head.next;
    head.next.prev = curr;
    head.next = curr;
  }

  public void removeNode(Node curr) {
    curr.prev.next = curr.next;
    curr.next.prev = curr.prev;

  }

  public Node removeTail() {
    Node tail = head.prev;
    removeNode(tail);
    return tail;
  }
}
