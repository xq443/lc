package Tiktok.Stack;

import java.util.Stack;

public class LargestRectangleHistogram {
  public int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int ret = Integer.MIN_VALUE / 2;
    for(int i = 0; i <= heights.length; i++){
      int x = (i < heights.length) ? heights[i] : 0;
      while(!stack.isEmpty() && heights[stack.peek()] > x){
        int currIdx = stack.pop();
        int currHeight = heights[currIdx];
        int width = !stack.isEmpty() ? (i - stack.peek() - 1) : i;
        ret = Math.max(ret, currHeight * width);
      }
      stack.push(i);
    }
    return ret;
  }

  public static void main(String[] args) {
    LargestRectangleHistogram l = new LargestRectangleHistogram();
    System.out.println(l.largestRectangleArea(new int[]{2,1,5,6,2,3}));
  }
}
