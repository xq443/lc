import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    int capacity;
    Node head;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.head.next = head;
        this.head.prev = head;
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            removeNode(curr);
            moveToHead(curr);
            return curr.value;
        } else return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            curr.value = value;
            removeNode(curr);
            moveToHead(curr);
        } else {
            if(map.size() == capacity) {
                // evict
                Node nodeToRemove = removeTail();
                map.remove(nodeToRemove.key); // reason for node to have key attribute
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            moveToHead(newNode);
            map.put(key, newNode);
        }
    }

    public void removeNode(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    public void moveToHead(Node curr) { // head is dummy node
        curr.prev = head;
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
    }

    public Node removeTail() {
        Node tail = head.prev;
        removeNode(tail);
        return tail;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1)); // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));  // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1)); // return -1 (not found)
        System.out.println(lRUCache.get(3)); // return 3
        System.out.println(lRUCache.get(4)); // return 4
    }
}
