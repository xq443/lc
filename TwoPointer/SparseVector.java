import java.util.ArrayList;
import java.util.List;

public class SparseVector {
  List<int[]> list;

  public SparseVector(int[] arr) {
    this.list = new ArrayList<>();
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] > 0) {
        list.add(new int[] {i, arr[i]});
      }
    }
  }
  public int dotProduct(SparseVector vec) {
    int sum = 0;
    List<int[]> other = vec.list;
    int i = 0, j = 0;
    while(i < list.size() && j < other.size()) {
      int idx1 = list.get(i)[0], idx2 = other.get(j)[0];
      int element1 = list.get(i)[1], element2 = other.get(j)[1];
      if(idx1 < idx2) {
        i++;
      } else if(idx1 > idx2) {
        j++;
      } else {
        sum += element2 * element1;
        i++;
        j++;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    SparseVector s1 = new SparseVector(new int[] {1,0,0,2,3});
    SparseVector s2 = new SparseVector(new int[] {0,3,0,4,0});
    System.out.println(s1.dotProduct(s2));
  }
}
