public class AddNum_Recursive {
  public ListNode addTwoList(ListNode l1, ListNode l2) {
    return add_recursion(l1, l2, 0);
  }
  public ListNode add_recursion(ListNode l1, ListNode l2, int carry) {
    if(l1 == null && l2 == null){
      return (carry > 0) ? new ListNode(carry) : null;
    }
    int val1 = l1 == null ? 0 : l1.val;
    int val2 = l2 == null ? 0 : l2.val;
    int sum = val1 + val2 + carry;
    ListNode ret = (l1 != null) ? l1 : l2;
    ret.val = sum % 10;
    int newCarry = sum / 10;

    l1 = (l1 != null) ? l1.next : null;
    l2 = (l2 != null) ? l2.next : null;

    ret.next = add_recursion(l1, l2, newCarry);
    return ret;
  }

  public static void main(String[] args) {
    AddNum_Recursive addNum = new AddNum_Recursive();
    ListNode l1 = new ListNode(3);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(9);
    ListNode l2 = new ListNode(8);
    l2.next = new ListNode(7);
    l2.next.next = new ListNode(6);

    System.out.println(addNum.addTwoList(l1, l2).val);
  }
}
