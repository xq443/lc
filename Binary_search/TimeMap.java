import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {
  static class Pair {
    int timestamp;
    String value;
    public Pair(int timestamp, String value) {
      this.timestamp = timestamp;
      this.value = value;
    }
  }

  Map<String, List<Pair>> map;
  public TimeMap() {
    this.map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    if(!map.containsKey(key)) {
      map.put(key, new ArrayList<>());
    }
    map.get(key).add(new Pair(timestamp, value));
  }

  public String get(String key, int timestamp) {
    if(!map.containsKey(key)) return "";
    return bs(map.get(key), timestamp);
  }

  public String bs(List<Pair> data, int timestamp) {
    String ret = "";
    int left = 0, right = data.size() - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      int curr = data.get(mid).timestamp;
      if(curr == timestamp) return data.get(mid).value;
      else if(curr > timestamp) right = mid - 1;
      else left = mid + 1;
    }

    if(left > 0) {
      ret = data.get(left - 1).value;
    }
    return ret;
  }

  public static void main(String[] args) {
    TimeMap timeMap = new TimeMap();
    timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
    System.out.println(timeMap.get("foo", 1));         // return "bar"
    System.out.println(timeMap.get("foo", 3)); // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
    timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
    System.out.println(timeMap.get("foo", 4));
    System.out.println(timeMap.get("foo", 5));
  }
}
