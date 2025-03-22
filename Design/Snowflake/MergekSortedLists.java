public class MergekSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeDivideConquer(lists, 0, lists.length - 1);
    }

    public ListNode mergeDivideConquer(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left]; // base case
        }
        int mid = left + (right - left) / 2;
        ListNode l = mergeDivideConquer(lists, left, mid);
        ListNode r = mergeDivideConquer(lists, mid + 1, right);
        return mergeList(l, r);
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public ListNode createList(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for(int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return head.next;
    }

    public void printList(ListNode head) {
        while(head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        MergekSortedLists m = new MergekSortedLists();
        ListNode[] lists = new ListNode[3];
        lists[0] = m.createList(new int[]{1,4,5});
        lists[1] = m.createList(new int[]{1,3,4});
        lists[2] = m.createList(new int[]{2,6});

        ListNode head = m.mergeKLists(lists);
        m.printList(head);
    }
}

// TC: O(NlogK) N-> # of nodes in both lists; k-> # of lists
// SC: O(logK) due to the recursion depth
