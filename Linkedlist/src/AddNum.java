public class AddNum {
  public ListNode addTwoList(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    int carry = 0;

    while(l1 != null || l2 != null) {
      // get the curr value
      int val1 = (l1 != null) ? l1.val : 0;
      int val2 = (l2 != null) ? l2.val : 0;

      // get sum
      int sum = val1 + val2 + carry;
      curr.next = new ListNode(sum % 10);
      carry = sum / 10;

      // move to the next node
      curr = curr.next;
      if (l1 != null)
        l1 = l1.next;
      if (l2 != null)
        l2 = l2.next;
    }

    // check if we need another node to store the extra carry
    if(carry != 0) {
      curr.next = new ListNode(carry);
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    AddNum addNum = new AddNum();
    ListNode l1 = new ListNode(3);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(9);
    ListNode l2 = new ListNode(8);
    l2.next = new ListNode(7);
    l2.next.next = new ListNode(6);

    System.out.println(addNum.addTwoList(l1, l2).val);
  }
}
