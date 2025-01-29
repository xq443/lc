package Apple;

public class MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;
    return mergeDivideConquer(lists, 0, lists.length - 1);
  }

  public ListNode mergeDivideConquer(ListNode[] lists, int left, int right) {
    if(left == right) return lists[left];
    int mid = (left + right) / 2;
    ListNode l1 = mergeDivideConquer(lists, left, mid);
    ListNode l2 = mergeDivideConquer(lists, mid + 1, right);
    return mergeList(l1, l2);
  }

  public ListNode mergeList(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while(l1 != null && l2 != null) {
      if(l1.val < l2.val) {
        prev.next = l1;
        l1 = l1.next;
      } else {
        prev.next = l2;
        l2 = l2.next;
      }
      prev = prev.next;
    }
    prev.next = l1 == null ? l2 : l1;
    return dummy.next;
  }

  public static void main(String[] args) {
    MergeKSortedLists m = new MergeKSortedLists();
    // Test case: [[1,4,5], [1,3,4], [2,6]]
    ListNode[] lists = new ListNode[3];
    lists[0] = new ListNode(1);
    lists[0].next = new ListNode(4);
    lists[0].next.next = new ListNode(5);

    lists[1] = new ListNode(1);
    lists[1].next = new ListNode(3);
    lists[1].next.next = new ListNode(4);

    lists[2] = new ListNode(2);
    lists[2].next = new ListNode(6);

    // Merging the lists
    ListNode ret = m.mergeKLists(lists);

    while (ret != null) {
      System.out.print(ret.val + " ");
      ret = ret.next;
    }
    // Expected Output: 1 1 2 3 4 4 5 6
  }

}
