import java.util.*;

public class RandomizedSet {
    List<Integer> list;
    Map<Integer,Integer> ValueToIdx;
    Random rand;

    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.ValueToIdx = new HashMap<>();
        this.rand = new Random();
    }

    public boolean insert(int val) { // insert to the last position of the list
        if(ValueToIdx.containsKey(val)) return false;
        list.add(val);
        ValueToIdx.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) { // find the idx -> swap value of the idx to the last position -> remove the last element
        if(!ValueToIdx.containsKey(val)) return false;
        int idx = ValueToIdx.get(val);
        int last = list.get(list.size() - 1);
        list.set(idx, last);
        ValueToIdx.put(last, idx);

        list.remove(list.size() - 1);
        ValueToIdx.remove(val);
        return true;
    }

    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomizedSet.remove(2)); // Returns false as 2 does not exist in the set.
        System.out.println(randomizedSet.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.
        System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomizedSet.insert(2)); // 2 was already in the set, so return false.
        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.
    }
}

// getrandom -> list random access
// remove -> map(build the value to idx relationship) -> move the last element to the idx -> remove the last element
// insert -> list (add to the last position)