import java.util.HashMap;
import java.util.Map;

public class SparseVector2 {
  Map<Integer, Integer> map;

  public SparseVector2(int[] arr) {
    this.map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      if(arr[i] > 0) map.put(i, arr[i]);
    }
  }
  public int dotProduct(SparseVector2 vec) {
    Map<Integer, Integer> vecMap = vec.map;
    int sum = 0;
    for(int key : map.keySet()) {
      if(vecMap.containsKey(key)) {
        sum += map.get(key) * vecMap.get(key);
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    SparseVector2 s1 = new SparseVector2(new int[] {1,0,0,2,3});
    SparseVector2 s2 = new SparseVector2(new int[] {0,3,0,4,0});
    System.out.println(s1.dotProduct(s2));
  }
}
