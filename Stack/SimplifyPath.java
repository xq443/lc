import java.util.Stack;

public class SimplifyPath {
  public String simplifyPath(String path) {
    Stack<String> paths = new Stack<>();

    for(String s : path.split("/")) {
      if(s.isEmpty() || s.equals(".")) continue;
      else if(s.equals("..")) {
        if(!paths.isEmpty()) paths.pop();
      } else paths.push(s);
    }

    StringBuilder ret = new StringBuilder();
    for(String s : paths) {
      ret.append("/");
      ret.append(s);
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    SimplifyPath simplifyPath = new SimplifyPath();
    System.out.println(simplifyPath.simplifyPath("//home/user/Documents/../Pictures/"));
    System.out.println(simplifyPath.simplifyPath("/.../a/../b/c/../d/./"));
  }
}
