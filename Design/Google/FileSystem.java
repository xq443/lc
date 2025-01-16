package Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {

  static class TrieNode {
    Map<String, TrieNode> map; // key: path; value: trienode (file or dir)
    String name;
    boolean isFile;
    StringBuilder fileContent;

    public TrieNode(String name) {
      this.name = name;
      this.isFile = false;
      this.fileContent = new StringBuilder();
      this.map = new HashMap<>();
    }

    public boolean isFile(TrieNode node) {
      return !node.fileContent.isEmpty();
    }
  }

  TrieNode root;

  public FileSystem() {
    this.root = new TrieNode("/");
  }

  public TrieNode traverse(String path) {
    TrieNode curr = root;
    String[] parts = path.split("/");
    for(int i = 1; i < parts.length; i++) {
      if(!curr.map.containsKey(parts[i])) {
        TrieNode newNode = new TrieNode(parts[i]);
        curr.map.put(parts[i], newNode);
      }
      curr = curr.map.get(parts[i]);
    }
    return curr;
  }

  public List<String> ls(String path) {
    TrieNode curr = traverse(path);
    List<String> ret = new ArrayList<>();
    if(curr.isFile) {
      ret.add(curr.name);
    } else {
      ret.addAll(curr.map.keySet());
    }
    Collections.sort(ret);
    return ret;
  }

  public void mkdir(String path) {
    traverse(path);
  }

  public void addContentToFile(String filePath, String content) {
    TrieNode curr = traverse(filePath);
    curr.isFile = true;
    curr.fileContent.append(content);
  }

  public String readContentFromFile(String filePath) {
    TrieNode curr = traverse(filePath);
    return curr.fileContent.toString();
  }

  public static void main(String[] args) {
    FileSystem fs = new FileSystem();
    System.out.println(fs.ls("/")); // return []
    fs.mkdir("/a/b/c");
    fs.addContentToFile("/a/b/c/d", "hello");
    System.out.println(fs.ls("/")); // return ["a"]
    System.out.println(fs.readContentFromFile("/a/b/c/d")); // return "hello"
  }
}
