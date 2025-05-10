package Meta;

import java.util.Random;

public class RandomLinePicker {
    public static String pickRandomLine(String text) {
      String[] lines = text.split("\n"); // Split text into lines
      int totalLines = lines.length;

      if (totalLines == 0) return null; // Edge case: empty input

      Random random = new Random();
      int randomIndex = random.nextInt(totalLines); // Select a random index
      return lines[randomIndex]; // Pick the corresponding line
    }

    public static void main(String[] args) {
      String text = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5"; // Example text
      System.out.println(pickRandomLine(text));
    }


  public static String pickRandomLine_reservior_sampling(String text) {
    String[] lines = text.split("\n"); // Splitting input text into lines
    Random random = new Random();
    String selectedLine = null;
    int index = 0;

    for (String line : lines) {
      index++;
      if (random.nextInt(index) == 0) { // Probability of replacement = 1/index
        selectedLine = line;
      }
    }
    return selectedLine;
  }

  /**
   * Reservoir sampling is better for large-scale data
   * because it does not require loading all lines into memory at once.
   * Instead, it processes lines one at a time,
   * making it highly efficient for huge datasets—even when dealing with millions or billions of lines.
   */
}
