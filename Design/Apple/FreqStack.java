package Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class FreqStack {
  Map<Integer, Integer> map;
  List<Stack<Integer>> list;

  public FreqStack() {
    this.map = new HashMap<>();
    this.list = new ArrayList<>();
  }

  public void push(int val) {
    map.put(val, map.getOrDefault(val, 0) + 1);
    int freq = map.get(val);
    if(freq > list.size()) {
      list.add(new Stack<>());
    }
    list.get(freq - 1).add(val);
  }

  public int pop() {
    int maxFreq = list.size();
    int ret = list.get(maxFreq - 1).pop();
    if(list.get(maxFreq - 1).isEmpty()) {
      list.remove(maxFreq - 1);
    }
    map.put(ret, map.get(ret) - 1);
    if(map.get(ret) == 0) map.remove(ret);
    return ret;
  }

  public static void main(String[] args) {
    FreqStack freqStack = new FreqStack();
    freqStack.push(5); // The stack is [5]
    freqStack.push(7); // The stack is [5,7]
    freqStack.push(5); // The stack is [5,7,5]
    freqStack.push(7); // The stack is [5,7,5,7]
    freqStack.push(4); // The stack is [5,7,5,7,4]
    freqStack.push(5); // The stack is [5,7,5,7,4,5]
    System.out.println(freqStack.pop());  // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
    System.out.println(freqStack.pop());  // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
    System.out.println(freqStack.pop());  // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
    System.out.println(freqStack.pop());  // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].吃的。
  }
}
