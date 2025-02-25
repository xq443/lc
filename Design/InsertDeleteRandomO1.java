import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteRandomO1 {
  Map<Integer, Integer> map; // key: val, value: index
  List<Integer> list;
  Random rand;

  public InsertDeleteRandomO1() {
    map = new HashMap<>();
    list = new ArrayList<>();
    rand = new Random();
  }

  public boolean insert(int val) {
    if(!map.containsKey(val)) {
      list.add(val);
      map.put(val, list.size() - 1);
      return true;
    }
    return false;
  }

  public boolean remove(int val) {
    if(map.containsKey(val)) {
      int idx = map.get(val);
      int lastElement = list.get(list.size() - 1);
      list.set(idx, lastElement);
      map.put(lastElement, idx);

      list.remove(list.size() - 1);
      map.remove(val);
      return true;
    }
    return false;
  }

  public int getRandom() {
    int idx = rand.nextInt(list.size());
    return list.get(idx);
  }

  public static void main(String[] args) {
    InsertDeleteRandomO1 randomizedSet = new InsertDeleteRandomO1();
    System.out.println(randomizedSet.insert(1));
    System.out.println(randomizedSet.remove(2));
    System.out.println(randomizedSet.insert(2));
    System.out.println(randomizedSet.getRandom());
    System.out.println(randomizedSet.remove(1));
    System.out.println(randomizedSet.insert(2));
    System.out.println(randomizedSet.getRandom());
  }
}

// map: insert O(1), delete O(1), getRandom O(n) -> no random access
// arraylist: insert O(1), delete O(n), getRandom O(1)
// make list deletion as swap