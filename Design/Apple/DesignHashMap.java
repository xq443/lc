package Apple;

public class DesignHashMap {
  public static class Node {
    int val;
    int key;
    Node next;
    public Node (int key, int val) {
      this.key = key;
      this.val = val;
      this.next = null;
    }
  }
  public int size;
  public Node[] bucket;
  public static final double LOAD_FACTOR = 0.75;
  public DesignHashMap() {
    this.size = 0;
    this.bucket = new Node[16];
  }

  public void put(int key, int value) {
    int idx = key % bucket.length;
    Node head = bucket[idx];
    while(head != null && head.key != key) {
      head = head.next;
    }
    if(head != null) {
      head.val = value;
    } else {
      Node newNode = new Node(key, value);
      newNode.next = bucket[idx];
      bucket[idx] = newNode;
      size++;
    }

    if(size >= bucket.length * LOAD_FACTOR) {
      expand();
    }
  }

  public int get(int key) {
    int idx = key % bucket.length;
    Node head = bucket[idx];

    while(head != null && head.key != key) {
      head = head.next;
    }

    return head == null ? -1 : head.val;
  }

  public void remove(int key) {
    int idx = key % bucket.length;
    Node head = bucket[idx];

    Node dummy = new Node(0, 0);
    Node curr = dummy;
    dummy.next = head;

    while(curr.next != null && curr.next.key != key) {
      // 1->2->3  1->3
      curr = curr.next;
    }

    if(curr.next != null && curr.next.key == key) {
      curr.next = curr.next.next;
      size--;
    }
    bucket[idx] = dummy.next;
  }

  public void expand() {
    Node[] oldBucket = bucket;
    Node[] newBucket = new Node[bucket.length * 2];
    bucket = newBucket;

    for(Node curr : oldBucket) {
      while(curr != null) {
        put(curr.key, curr.val);
        curr = curr.next;
      }
    }
  }

  public static void main(String[] args) {
    DesignHashMap myHashMap = new DesignHashMap();
    myHashMap.put(1, 1); // The map is now [[1,1]]
    myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
    System.out.println(
        myHashMap.get(1)); // return 1, The map is now [[1,1], [2,2]]
    System.out.println(myHashMap.get(
        3)); // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
    myHashMap.put(
        2,
        1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
    System.out.println(
        myHashMap.get(2)); // return 1, The map is now [[1,1], [2,1]]
    myHashMap.remove(2);   // remove the mapping for 2, The map is now [[1,1]]
    System.out.println(myHashMap.get(
        2)); // return -1 (i.e., not found), The map is now [[1,1]]
  }
}
