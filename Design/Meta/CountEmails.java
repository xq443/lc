package Meta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class CountEmails {
  public int countEmails(String path) throws FileNotFoundException {
    int ret = 0;
    // 正则表达式匹配 email
    String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    Pattern pattern = Pattern.compile(emailRegex);
    try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line;
      while( (line = reader.readLine()) != null) {
        // split
        String[] words = line.split("\\s+");
        for (String word : words) {
          if (isValidEmail(word)) {
            ret++;
          }
//          if(pattern.matcher(word).matches()) {
//            ret++;
//          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return ret;
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

  public static void main(String[] args) throws FileNotFoundException {
    CountEmails c = new CountEmails();
    String path = "email.txt";
    int cnt = c.countEmails(path);
    System.out.println(STR."total emails are: \{cnt}");
  }
}
