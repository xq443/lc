package Meta;

public class ReverseSubarray {
  public void reverseSubarray(int[] arr, int i, int j) {
    if (arr == null) {
      throw new IllegalArgumentException("Input array is null.");
    }
    int n = arr.length;
    if (i < 0 || j < 0 || i >= n || j >= n) {
      throw new IndexOutOfBoundsException("i or j is out of bounds.");
    }
    if (i >= j) {
      // Nothing to do if i >= j
      return;
    }

    while (i < j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      i++;
      j--;
    }
  }

  public static void main(String[] args) {
    ReverseSubarray r = new ReverseSubarray();
    int[] arr = {1, 2, 3, 4, 5, 6};
    int i = 2, j = 4;

    r.reverseSubarray(arr, i, j);

    // Print the result
    for (int num : arr) {
      System.out.print(num + " ");
    }
    // Expected output: 1 2 5 4 3 6
  }
}
