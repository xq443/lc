import java.util.Stack;

public class TrappingRainWater {
  public int trap(int[] height) {
    if (height.length == 0) return 0;
    Stack<Integer> stack = new Stack<>();
    int ret = 0;
    for(int i = 0; i < height.length; i++){
      while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
        int currIdx = stack.pop();
        if (stack.isEmpty()) break;

        // width is related to the stack.peek()
        int width = i - stack.peek() - 1;
        int h = Math.min(height[stack.peek()], height[i]) - height[currIdx];
        ret += width * h;
      }
      stack.push(i);
    }
    return ret;
  }

  public static void main(String[] args) {
    TrappingRainWater t = new TrappingRainWater();
    int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(t.trap(height));
  }
}
