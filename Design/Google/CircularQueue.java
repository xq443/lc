package Google;

public class CircularQueue {
  static class Node {
    int val;
    Node next;

    public Node(int val) {
      this.val = val;
    }
  }

  Node head;
  Node tail;
  int capacity;
  int currSize;

  public CircularQueue(int k) {
    this.capacity = k;
    this.head = null;
    this.tail = null;
    this.currSize = 0;
  }

  // add to the tail
  public boolean enQueue(int value) {
    if(isFull()) return false;
    Node newNode = new Node(value);

    if(isEmpty()) {
      head = newNode;
      tail = newNode;
      tail.next = head;
    } else {
      tail.next = newNode;
      tail = tail.next;
      tail.next = head;
    }

    currSize++;
    return true;
  }

  // remove from the head
  public boolean deQueue() {
    if(isEmpty()) return false;

    if(currSize == 1) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      tail.next = head;
    }

    currSize--;
    return true;
  }

  public int Front() {
    if(isEmpty()) return -1;
    return head.val;
  }

  public int Rear() {
    if(isEmpty()) return -1;
    return tail.val;
  }

  public boolean isEmpty() {
    return currSize == 0;
  }

  public boolean isFull() {
    return currSize == capacity;
  }

  public static void main(String[] args) {
    CircularQueue c = new CircularQueue(3);
    System.out.println(c.enQueue(1));
    System.out.println(c.enQueue(2));
    System.out.println(c.enQueue(3));
    System.out.println(c.enQueue(4));
    System.out.println(c.Rear());
    System.out.println(c.isFull());
    System.out.println(c.deQueue());
    System.out.println(c.enQueue(4));
    System.out.println(c.Rear());
  }
}


