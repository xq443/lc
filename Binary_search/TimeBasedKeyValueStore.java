import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
    static class Pair {
        String value;
        int timestamp;

        public Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Pair>> timeMap;

    public TimeBasedKeyValueStore() {
        this.timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!timeMap.containsKey(key)) {
            timeMap.put(key, new ArrayList<>());
            timeMap.get(key).add(new Pair(timestamp, value));
        } else {
            List<Pair> pairs = timeMap.get(key);
            pairs.add(new Pair(timestamp, value));
        }
    }

    public String get(String key, int timestamp) throws IllegalAccessException {
        if(!timeMap.containsKey(key)) {
            throw new IllegalAccessException("no such key exists");
        } else {
            // find the last element <= timestamp
            List<Pair> list = timeMap.get(key);
            int idx = binarySearch(list, timestamp);
            if (idx == -1) return "";
            return list.get(idx).value;
        }
    }

    public int binarySearch(List<Pair> list, int timestamp) {
        int left = 0, right = list.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(list.get(mid).timestamp == timestamp) return mid;
            else if(list.get(mid).timestamp > timestamp) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    public static void main(String[] args) throws IllegalAccessException {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "bar", 1);
        System.out.println(store.get("foo", 1));

        System.out.println(store.get("foo", 3));

        store.set("foo", "bar2", 4);
        System.out.println(store.get("foo", 4));
        System.out.println(store.get("foo", 5));
    }
}
