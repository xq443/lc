package Meta;

public class EqualPartition {
  public static int findSplitPoint(int[] arr) {
    int leftPointer = 0;
    int rightPointer = arr.length - 1;
    int leftSum = arr[leftPointer];
    int rightSum = arr[rightPointer];

    while (leftPointer < rightPointer - 1) {
      if (leftSum < rightSum) {
        leftPointer++;
        leftSum += arr[leftPointer];
      } else {
        rightPointer--;
        rightSum += arr[rightPointer];
      }

      if (leftSum == rightSum && leftPointer == rightPointer - 1) {
        return leftPointer; // Return the split point index
      }
    }

    return -1; // If no split point is found, return -1
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 6};
    int splitPoint = findSplitPoint(arr);
    if (splitPoint != -1) {
      System.out.println("Split point index: " + splitPoint);
    } else {
      System.out.println("No split point found");
    }
  }
}
