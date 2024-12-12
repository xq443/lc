import java.util.Stack;

public class removeAllAdjacentDuplicatesinStringII {
    public static class Pair{
        int count;
        char character;

        public Pair(int count, char character){
            this.character = character;
            this.count = count;
        }
    }
    public static String removeDuplicates(String s, int k){
        Stack<Pair> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(stack.isEmpty() || stack.peek().character != ch){
                stack.push(new Pair(1, ch));
            }else{
                stack.push(new Pair(stack.peek().count + 1, ch));
            }
            if(stack.peek().count == k){
                for (int i = 0; i < k; i++) {
                    stack.pop();
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        while(!stack.empty()){
            ret.append(stack.peek().character);
            stack.pop();
        }
        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println(removeDuplicates(s, k));
        String s_1 = "abcd";
        int k_1 = 3;
        System.out.println(removeDuplicates(s_1, k_1));
    }
}
//TC:O(N) SC:O(N)
