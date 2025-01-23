package Meta.Heap;

import java.util.PriorityQueue;

public class MedianFinder {
  PriorityQueue<Integer> minHeap; // store larger half
  PriorityQueue<Integer> maxHeap; // store smaller half
  public MedianFinder() {
    this.minHeap = new PriorityQueue<>();
    this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
  }

  public void addNum(int num) { // tc O(lgn)
    if(maxHeap.isEmpty() || num < maxHeap.peek()) {
      maxHeap.add(num);
    } else
      minHeap.add(num);

    if(maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() { // tc O(1)
    if (maxHeap.isEmpty() || minHeap.isEmpty()) {
      return 0.0;
    }

    if(maxHeap.size() > minHeap.size()) {
      return maxHeap.peek();
    } else {
      return (double) (maxHeap.peek() + minHeap.peek()) / 2;
    }
  }

  public static void main(String[] args) {
    MedianFinder m = new MedianFinder();
    m.addNum(1);    // arr = [1]
    m.addNum(2);    // arr = [1, 2]
    System.out.println(m.findMedian()); // 1.5
    m.addNum(3);    // arr[1, 2, 3]
    System.out.println(m.findMedian());
  }
}
