package Snowflake.recursion;

public class ReverseNodeInKGroups {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode() {}
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int count = k;
        ListNode curr = head;
        while (curr != null && count > 0) {
            curr = curr.next;
            count--;
        }

        if(count == 0) {
            curr = reverseKGroup(curr, k);
            while(count++ < k) {
                ListNode temp = head.next;
                head.next = curr;
                curr = head; // curr points to head
                head = temp; // head points to temp
            }
            head = curr;
        }
        return head;
    }

    public void print(ListNode head) {
        ListNode curr = head;
        while(curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        ReverseNodeInKGroups r = new ReverseNodeInKGroups();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        r.print(r.reverseKGroup(head, 2));
    }
    // TC: O(N)
    // SC: O(N / K)
}
