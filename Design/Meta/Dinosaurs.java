package Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dinosaurs {
  public static class Dino {
    String name;
    float speed;

    public Dino(String name, float speed) {
      this.name = name;
      this.speed = speed;
    }
  }

  public float computeSpeed(float strideLength, float legLength) {
    return (strideLength / legLength - 1) * (float) Math.sqrt(legLength * 9.8);
  }

  public List<String> getFastestBipeds(List<String> dataset1, List<String> dataset2) {
    Map<String, Float> legLength = new HashMap<>();
    for(int i = 1; i < dataset1.size(); i++) {
      String[] parts = dataset1.get(i).split(",");
      legLength.put(parts[0], Float.parseFloat(parts[1]));
    }

    List<Dino> bipedalDinos = new ArrayList<>();
    for(int i = 1; i < dataset2.size(); i++) {
      String[] parts = dataset2.get(i).split(",");
      String name = parts[0];
      float strideLength = Float.parseFloat(parts[1]);
      String stance = parts[2];

      if(stance.equals("bipedal") && legLength.containsKey(name)) {
        float speed = computeSpeed(strideLength, legLength.get(name));
        bipedalDinos.add(new Dino(name, speed));
      }
    }

    bipedalDinos.sort((a, b) -> Float.compare(b.speed, a.speed));
    for(Dino d : bipedalDinos) {
      System.out.println(d.speed);
    }
    List<String> ret = new ArrayList<>();
    for(Dino d : bipedalDinos) {
      ret.add(d.name);
    }
    return ret;
  }

  public static void main(String[] args) {
    Dinosaurs d = new Dinosaurs();
    List<String> dataset1 = Arrays.asList(
        "NAME,LEG_LENGTH,DIET",
        "Hadrosaurus,1.2,herbivore",
        "Struthiomimus,0.92,omnivore",
        "Velociraptor,1.0,carnivore",
        "Triceratops,0.87,herbivore",
        "Euoplocephalus,1.6,herbivore",
        "Stegosaurus,1.40,herbivore",
        "Tyrannosaurus Rex,2.5,carnivore"
    );

    List<String> dataset2 = Arrays.asList(
        "NAME,STRIDE_LENGTH,STANCE",
        "Euoplocephalus,1.87,quadrupedal",
        "Stegosaurus,1.90,quadrupedal",
        "Tyrannosaurus Rex,5.76,bipedal",
        "Hadrosaurus,1.4,bipedal",
        "Deinonychus,1.21,bipedal",
        "Struthiomimus,1.34,bipedal",
        "Velociraptor,2.72,bipedal"
    );

    List<String> result = d.getFastestBipeds(dataset1, dataset2);
    System.out.println("Bipedal dinosaurs from fastest to slowest:");
    for (String name : result)
      System.out.println(name);
  }
}

// TC: O(n + m + p* logp)
// SC: O(n + p)
//Struthiomimus
//    Hadrosaurus
//Tyrannosaurus Rex