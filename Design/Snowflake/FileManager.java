package Snowflake;

import java.util.HashMap;
import java.util.Map;

public class FileManager {
  private Map<String, int[]> users;  // Key: username, Value: [capacity, used space]
  private Map<String, String> files;  // Key: filepath, Value: owner

  public FileManager() {
    users = new HashMap<>();
    files = new HashMap<>();
  }

  public String processQuery(String[] query) {
    String operation = query[0];

    if (operation.equals("ADD_USER")) {
      return addUser(query[1], Integer.parseInt(query[2]));
    } else if (operation.equals("ADD_FILE_BY")) {
      return addFileBy(query[1], query[2], Integer.parseInt(query[3]));
    } else if (operation.equals("COPY_FILE")) {
      return copyFile(query[1], query[2]);
    } else if (operation.equals("UPDATE_CAPACITY")) {
      return updateCapacity(query[1], Integer.parseInt(query[2]));
    }
    return "";
  }

  public String addUser(String username, int capacity) {
    if (users.containsKey(username)) {
      return "false";  // User already exists
    }
    users.put(username, new int[]{capacity, 0});  // [capacity, used space]
    return "true";
  }

  public String addFileBy(String username, String filepath, int size) {
    if (!users.containsKey(username)) {
      return "";  // User does not exist
    }

    // Check if the file already exists and is owned by another user
    if (files.containsKey(filepath) && !files.get(filepath).equals(username)) {
      return "";  // File already exists and is owned by another user
    }

    int[] userDetails = users.get(username);
    int userCapacity = userDetails[0];
    int usedSpace = userDetails[1];

    // Check if the user has enough space
    if (usedSpace + size > userCapacity) {
      return "";  // Not enough space to add the file
    }

    // Add the file
    users.put(username, new int[]{userCapacity, usedSpace + size});
    files.put(filepath, username);

    return String.valueOf(userCapacity - usedSpace - size);
  }

  public String copyFile(String srcFilepath, String dstFilepath) {
    // Check if source file exists
    if (!files.containsKey(srcFilepath)) {
      return "";  // Source file doesn't exist
    }

    String owner = files.get(srcFilepath);

    // Check if destination file already exists
    if (files.containsKey(dstFilepath)) {
      return "";  // File already exists at the destination
    }

    // Assuming each file size is 50 units for simplicity (adjust as needed)
    int fileSize = 50;  // Placeholder value, adjust as needed

    // Check if the user has enough space to copy the file
    int[] userDetails = users.get(owner);
    int userCapacity = userDetails[0];
    int usedSpace = userDetails[1];

    if (usedSpace + fileSize > userCapacity) {
      return "false";  // Not enough space to copy the file
    }

    // Copy the file (preserving owner and updating storage)
    users.put(owner, new int[]{userCapacity, usedSpace + fileSize});
    files.put(dstFilepath, owner);

    // Return the remaining space after the copy
    int remainingSpace = userCapacity - (usedSpace + fileSize);
    return "true; After copying, " + owner + " has " + remainingSpace + " capacity left";
  }

  public String updateCapacity(String username, int newCapacity) {
    if (!users.containsKey(username)) {
      return "";  // User does not exist
    }

    int[] userDetails = users.get(username);
    int usedSpace = userDetails[1];

    // Check if the new capacity is enough to hold the used space
    if (newCapacity >= usedSpace) {
      users.put(username, new int[]{newCapacity, usedSpace});
      return String.valueOf(newCapacity - usedSpace);
    }

    // If the new capacity can't hold the used space, delete files to free up space
    int spaceNeeded = usedSpace - newCapacity;
    return deleteFilesToFreeSpace(username, spaceNeeded);
  }

  private int getFileSize(String filepath) {
    // Assuming the file size is 50 units for simplicity (you can extend it as needed)
    return 50;  // Placeholder value, adjust as needed
  }

  private String deleteFilesToFreeSpace(String username, int spaceNeeded) {
    int deletedFiles = 0;
    for (Map.Entry<String, String> entry : files.entrySet()) {
      if (entry.getValue().equals(username)) {
        String filepath = entry.getKey();
        files.remove(filepath);
        deletedFiles++;
        spaceNeeded -= getFileSize(filepath);
        if (spaceNeeded <= 0) break;
      }
    }

    if (spaceNeeded > 0) {
      return "";  // Could not free enough space
    }

    int[] userDetails = users.get(username);
    users.put(username, new int[]{userDetails[0], userDetails[1] - spaceNeeded});
    return String.valueOf(deletedFiles);
  }

  public static void main(String[] args) {
    FileManager fileManager = new FileManager();

    String[][] queries = {
        {"ADD_USER", "user1", "125"},
        {"ADD_USER", "user1", "100"},
        {"ADD_USER", "user2", "100"},
        {"ADD_FILE_BY", "user1", "/dir/file.big", "50"},
        {"ADD_FILE_BY", "user1", "/file.med", "30"},
        {"ADD_FILE_BY", "user2", "/file.med", "40"},
        {"COPY_FILE", "/file.med", "/dir/another/file.med"},
        {"COPY_FILE", "/file.med", "/dir/another/another/file.med"},
//        {"ADD_FILE_BY", "user1", "/dir/file.small", "10"},
//        {"ADD_FILE", "/dir/admin_file", "200"},
//        {"ADD_FILE_BY", "user1", "/dir/file.small", "5"},
//        {"ADD_FILE_BY", "user1", "/my_folder/file.huge", "100"},
//        {"ADD_FILE_BY", "user3", "/my_folder/file.huge", "100"},
//        {"UPDATE_CAPACITY", "user1", "300"},
//        {"UPDATE_CAPACITY", "user1", "50"},
//        {"UPDATE_CAPACITY", "user2", "1000"}
    };

    for (String[] query : queries) {
      System.out.println(fileManager.processQuery(query));
    }
  }
}
