public class RingBuffer {

  static class Node {
    int val;
    Node next;

    public Node(int val) {
      this.val = val;
    }
  }

  int capacity;
  int currSize;
  Node head;
  Node tail;

  public RingBuffer(int k) {
    this.capacity = k;
    this.currSize = 0;
    this.head = null;
    this.tail = null;
  }

  // add to the tail
  public boolean enQueue(int value) {
    if (isFull()) return false;

    Node newNode = new Node(value);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
      tail.next = head; // Form the circular connection
    } else {
      tail.next = newNode;
      tail = tail.next;
      tail.next = head; // Maintain the circular connection
    }

    currSize++;
    return true;
  }

  // dequeue the front node
  public boolean deQueue() {
    if (isEmpty()) return false;

    if (currSize == 1) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      tail.next = head; // Maintain the circular connection
    }

    currSize--;
    return true;
  }

  public int Front() {
    if (isEmpty()) return -1;
    return head.val;
  }

  public int Rear() {
    if (isEmpty()) return -1;
    return tail.val;
  }

  public boolean isEmpty() {
    return currSize == 0;
  }

  public boolean isFull() {
    return currSize == capacity;
  }

  public static void main(String[] args) {
    RingBuffer myCircularQueue = new RingBuffer(3);
    System.out.println(myCircularQueue.enQueue(1)); // return True
    System.out.println(myCircularQueue.enQueue(2)); // return True
    System.out.println(myCircularQueue.enQueue(3)); // return True
    System.out.println(myCircularQueue.enQueue(4)); // return False
    System.out.println(myCircularQueue.Rear());     // return 3
    System.out.println(myCircularQueue.isFull());   // return True
    System.out.println(myCircularQueue.deQueue());  // return True
    System.out.println(myCircularQueue.enQueue(4)); // return True
    System.out.println(myCircularQueue.Rear());     // return 4
  }
}
