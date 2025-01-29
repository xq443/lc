package Apple;

import java.util.PriorityQueue;

public class MergeKSortedListsHeap {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));

    // add into heap
    for(ListNode lHead : lists) {
      if(lHead != null) {
        pq.add(lHead);
      }
    }

    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;
    while(!pq.isEmpty()) {
      ListNode smallest = pq.poll();
      curr.next = smallest;
      curr = curr.next;

      if(smallest.next != null) {
        pq.add(smallest.next);
      }
    }
    return dummy.next;
  }
}
