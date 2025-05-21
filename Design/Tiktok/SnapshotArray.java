package Tiktok;

import java.util.ArrayList;
import java.util.List;

public class SnapshotArray {
  public List<List<int[]>> list;
  public int snapshotId;

  public SnapshotArray(int length) {
    this.list = new ArrayList<>(length);
    this.snapshotId = 0;

    for(int i = 0; i < length; i++) {
      list.add(new ArrayList<>());
      // get() always has at least one snapshot to compare against.
      list.get(i).add(new int[]{-1,0});
    }
  }

  public void set(int index, int val) {
    list.get(index).add(new int[]{snapshotId, val});
  }

  public int snap() {
    return snapshotId++;
  }

  public int get(int index, int snapId) {
    List<int[]> history = list.get(index);
    int left = 0, right = history.size() - 1;

    // find the largest id that is <= snapId
    while(left < right) {
      int mid = right - (right - left) / 2;
      if(history.get(mid)[0] <= snapId) {
        left = mid;
      } else {
        right = mid - 1;
      }
    }
    return history.get(left)[1];
  }

  public static void main(String[] args) {
    SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
    snapshotArr.set(0,5);  // Set array[0] = 5
    System.out.println(snapshotArr.snap()); // Take a snapshot, return snap_id = 0
    snapshotArr.set(0,6);
    System.out.println(snapshotArr.get(0,0)); // Get the value of array[0] with snap_id = 0, return 5
  }
}
