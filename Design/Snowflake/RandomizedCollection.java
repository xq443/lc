package Snowflake;

import java.util.*;

class RandomizedCollection {
    public static class Entry {
        int val;
        int idx;

        public Entry(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    Map<Integer, List<Integer>> map;
    List<Entry> list;
    Random rand;

    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        List<Integer> currPositions = map.getOrDefault(val, new ArrayList<>());
        currPositions.add(list.size()); // Adding the new index of the value
        map.put(val, currPositions);
        list.add(new Entry(val, currPositions.size() - 1)); // Storing the value and its index in the list
        return currPositions.size() == 1; // Return true if the value is inserted for the first time
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        List<Integer> currPositions = map.get(val);
        int idxToRemove = currPositions.get(currPositions.size() - 1); // Get the last position of the value to remove
        Entry lastEntry = list.get(list.size() - 1); // Get the last entry in the list

        // Update the map for the last element in the list
        // need to bundle the idx in the list and currPositions
        map.get(lastEntry.val).set(lastEntry.idx, idxToRemove); // Replace the position of the last entry with the idxToRemove

        // Swap the last element with the element to be removed
        list.set(idxToRemove, lastEntry); // Update the list with the last entry at the index to remove

        // Remove the last element
        list.remove(list.size() - 1);

        // Remove the position of the removed element from the map
        currPositions.remove(currPositions.size() - 1);
        if (currPositions.isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int idx = rand.nextInt(list.size());
        return list.get(idx).val; // Return the value of the random index
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();

        System.out.println(randomizedCollection.insert(1)); // true
        System.out.println(randomizedCollection.insert(1)); // false
        System.out.println(randomizedCollection.insert(2)); // true

        System.out.println(randomizedCollection.getRandom()); // Random (1 or 2 with probability)

        System.out.println(randomizedCollection.remove(1)); // true
        System.out.println(randomizedCollection.getRandom()); // Random (1 or 2, both equally likely)
    }
}
