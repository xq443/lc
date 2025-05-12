package Meta;

import java.io.*;
import java.util.regex.Pattern;

public class CountEmailsDir {
  String emailRegex = "\\b[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\\b";

  private final Pattern emailPattern = Pattern.compile(emailRegex);

  public int countEmailsInPath(String path) {
    File file = new File(path);
    if (!file.exists()) {
      throw new IllegalArgumentException("Path does not exist: " + path);
    }

    return countEmailsRecursive(file);
  }

  private int countEmailsRecursive(File fileOrDir) {
    int total = 0;

    if (fileOrDir.isFile() && fileOrDir.getName().endsWith(".txt")) {
      total += countEmailsInFile(fileOrDir);
    } else if (fileOrDir.isDirectory()) {
      File[] files = fileOrDir.listFiles();
      if (files != null) {
        for (File child : files) {
          total += countEmailsRecursive(child); // recursion
        }
      }
    }

    return total;
  }

  public boolean isValidEmail(String word) {
    if(word == null || word.length() < 5 || !word.contains("@")) return false;
    // 简单 email 判断逻辑，不使用正则

    int atIndex = word.indexOf("@");
    if (atIndex <= 0 || atIndex >= word.length() - 3) return false;

    String local = word.substring(0, atIndex);
    String domain = word.substring(atIndex + 1);

    if (!domain.contains(".") || domain.contains("@")) return false;
    int dotIndex = domain.lastIndexOf(".");
    if (dotIndex <= 0 || dotIndex >= domain.length() - 1) return false;
    return true;
  }

  private int countEmailsInFile(File file) {
    int count = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;

      while ((line = reader.readLine()) != null) {
        String[] words = line.split("\\s+");
        for (String word : words) {
//          if (emailPattern.matcher(word).matches()) {
//            count++;
//          }
          if(isValidEmail(word)) {
            count++;
            System.out.println(word);
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + file.getAbsolutePath());
    }

    return count;
  }

  public static void main(String[] args) {
    try {
      CountEmailsDir counter = new CountEmailsDir();
      String inputPath = "/Users/cathyqin/IdeaProjects/lc/Design/Meta"; // can be a file or folder

      int result = counter.countEmailsInPath(inputPath);
      System.out.println(STR."Total emails found: \{result}");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
