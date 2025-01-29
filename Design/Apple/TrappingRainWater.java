package Apple;

import java.util.Stack;

public class TrappingRainWater {
  public int trap(int[] height) {
    Stack<Integer> stack = new Stack<>();
    int ret = 0;
    for(int i = 0; i < height.length; i++){
      while(!stack.isEmpty() && height[stack.peek()] < height[i]){
        int top = stack.pop();
        if(stack.isEmpty()) break;
        int width = i - stack.peek() - 1;
        int currHeight = Math.min(height[i], height[stack.peek()]) - height[top];
        ret += currHeight * width;
      }
      stack.push(i);
    }
    return ret;
  }

  public static void main(String[] args) {
    TrappingRainWater t = new TrappingRainWater();
    System.out.println(t.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    System.out.println(t.trap(new int[]{4,2,0,3,2,5}));
  }
}
