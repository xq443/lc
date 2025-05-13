package Meta;

import Meta.Dinosaurs.Dino;
import java.io.*;
import java.util.*;

public class DinosaurSpeedAnalyzer {
  // Inner class to hold dinosaur name and speed
  public static class Dinosaur {
    String name;
    float speed;

    public Dinosaur(String name, float speed) {
      this.name = name;
      this.speed = speed;
    }
  }

  /**
   * Calculates the estimated speed of a dinosaur using stride length and leg length.
   * Formula: speed = (strideLength / legLength - 1) * sqrt(legLength * g)
   * where g = 9.8 m/s² (gravity)
   */

  public float computeSpeed(float strideLength, float legLength) {
    if (strideLength / legLength < 1) return 0;
    return (strideLength / legLength - 1) * (float) Math.sqrt(legLength * 9.8);
  }

  /**
   * Reads data from two CSV files and returns a list of bipedal dinosaur names sorted by descending speed.
   *
   * @param dataset1Path Path to dataset1.csv containing name and leg length
   * @param dataset2Path Path to dataset2.csv containing name, stride length, and stance
   * @return List of dinosaur names sorted by speed (fastest first)
   * @throws IOException if file reading fails
   */
  public List<String> getFastestBipedalDinosaurs(String dataset1Path, String dataset2Path) throws IOException {
    Map<String, Float> legLengthMap = new HashMap<>();

    // Read dataset1.csv
    try (BufferedReader br = new BufferedReader(new FileReader(dataset1Path))) {
      String line;
      br.readLine(); // skip header
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length < 2) continue;
        String name = parts[0].trim();
        float legLength = Float.parseFloat(parts[1].trim());
        legLengthMap.put(name, legLength);
      }
    }

    List<Dinosaur> bipedalDinosaurs = new ArrayList<>();

    // Read dataset2.csv
    try (BufferedReader br = new BufferedReader(new FileReader(dataset2Path))) {
      String line;
      br.readLine(); // skip header
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length < 3) continue;
        String name = parts[0].trim();
        float strideLength = Float.parseFloat(parts[1].trim());
        String stance = parts[2].trim();

        if (stance.equals("bipedal") && legLengthMap.containsKey(name)) {
          float legLength = legLengthMap.get(name);
          float speed = computeSpeed(strideLength, legLength);
          bipedalDinosaurs.add(new Dinosaur(name, speed));
        }
      }
    }

    // Sort by speed descending
    bipedalDinosaurs.sort((a, b) -> Float.compare(b.speed, a.speed));
    for(Dinosaur d : bipedalDinosaurs) {
      System.out.println(d.speed);
    }

    // Collect names in sorted order
    List<String> result = new ArrayList<>();
    for (Dinosaur dino : bipedalDinosaurs) {
      result.add(dino.name);
    }

    return result;
  }

  // Example main method
  public static void main(String[] args) {
    DinosaurSpeedAnalyzer analyzer = new DinosaurSpeedAnalyzer();
    String dataset1Path = "dataset1.csv";
    String dataset2Path = "dataset2.csv";

    try {
      List<String> fastestDinos = analyzer.getFastestBipedalDinosaurs(dataset1Path, dataset2Path);
      System.out.println("Fastest bipedal dinosaurs:");
      for (String name : fastestDinos) {
        System.out.println(name);
      }
    } catch (IOException e) {
      System.err.println("Error reading files: " + e.getMessage());
    }
  }
}
