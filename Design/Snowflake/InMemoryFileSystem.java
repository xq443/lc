package Snowflake;

import java.util.*;

public class InMemoryFileSystem {
    public static class TrieNode {
        boolean isFile;
        Map<String, TrieNode> next;
        String name;
        StringBuilder content;

        public TrieNode(String name) {
            this.isFile = false;
            this.next = new HashMap<>();
            this.name = name;
            this.content = new StringBuilder();
        }

    }

    TrieNode root;
    public InMemoryFileSystem() {
        this.root = new TrieNode("/"); // root path
    }

    public List<String> ls(String path) {
        List<String> ret = new ArrayList<>();
        TrieNode curr = traverse(path);
        if(curr.isFile) {
            ret.add(curr.name);
        } else {
            ret.addAll(curr.next.keySet());
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
        curr.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        TrieNode curr = traverse(filePath);
        return curr.content.toString();
    }

    public TrieNode traverse(String path) {
        TrieNode curr = root;
        String[] filePaths = path.split("/");
        for(int i = 1; i < filePaths.length; i++) {
            if(!curr.next.containsKey(filePaths[i])) {
                TrieNode newNode = new TrieNode(filePaths[i]);
                curr.next.put(filePaths[i], newNode);
            }
            curr = curr.next.get(filePaths[i]);
        }
        return curr;
    }

    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        System.out.println(fileSystem.ls("/")); // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));                   // return ["a"]
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d")); // return "hello"
    }
}
