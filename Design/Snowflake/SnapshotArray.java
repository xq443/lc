package Design;
import java.util.*;

class SnapshotArray {
    private List<List<int[]>> snapshotList; // index -> [snapshotId, val]
    private int snapshotId;

    public SnapshotArray(int length) {
        snapshotList = new ArrayList<>(length);
        snapshotId = 0;
        
        for (int i = 0; i < length; i++) {
            snapshotList.add(new ArrayList<>());
            snapshotList.get(i).add(new int[]{-1, 0}); // initial state
        }
    }
    
    public void set(int index, int val) {
        snapshotList.get(index).add(new int[]{snapshotId, val});
    }
    
    public int snap() {
        return snapshotId++;
    }
    
    public int get(int index, int snapId) {
        List<int[]> history = snapshotList.get(index);
        int left = 0, right = history.size() - 1;
        
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (history.get(mid)[0] <= snapId) { 
                // find the largest snapshotId <= the requested snapId
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return history.get(left)[1]; // return the value
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        System.out.println(snapshotArr.snap()); // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        System.out.println(snapshotArr.snap()); // snap_id = 1
        System.out.println(snapshotArr.get(0,1));// Get the value of array[0] with snap_id = 0, return 5
    }
}
// TC : O(logn)
// SC : O(1)
