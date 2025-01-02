import java.util.Arrays;

public class MinimumNumberOperationsMoveAllBalls {
  public int[] minOperations(String boxes) {
    //Input: boxes = "110"
    int[] ret = new int[boxes.length()];

    // left side
    int ops = 0, leftPrev = 0;
    for (int i = 0; i < boxes.length(); i++) {
      ret[i] += ops;
      if(boxes.charAt(i) == '1') {
        leftPrev++;
      }
      ops += leftPrev;
    }

    // right side
    ops = 0;
    int rightPrev = 0;
    for(int i = boxes.length() - 1; i >= 0; i--) {
      ret[i] += ops;
      if(boxes.charAt(i) == '1') {
        rightPrev++;
      }
      ops += rightPrev;
    }

    return ret;
  }

  public static void main(String[] args) {
    MinimumNumberOperationsMoveAllBalls m = new MinimumNumberOperationsMoveAllBalls();
    System.out.println(Arrays.toString(m.minOperations("110")));
  }
}
