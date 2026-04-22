import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Zoo {
    public static void main(String[] args) {

        ArrayList<Animal> animalList = new ArrayList<>();
// This hashmap counts all the species together.
        HashMap<String, Integer> speciesCount = new HashMap<>();
        ArrayList<String> hyenaNames = new ArrayList<>();
        ArrayList<String> lionNames = new ArrayList<>();
        ArrayList<String> tigerNames = new ArrayList<>();
        ArrayList<String> bearNames = new ArrayList<>();

        try {
            File nameFile = new File("animalNames.txt");
            Scanner nameReader = new Scanner(nameFile);
            String currentSpeciesName = "";
// This loops through the animalNames.txt file, which identifies species and stores names into the right ArrayLists.
            while (nameReader.hasNextLine()) {
                String line = nameReader.nextLine().trim();
                if (line.contains("Hyena")) {
                    currentSpeciesName = "hyena";
                } else if (line.contains("Lion")) {
                    currentSpeciesName = "lion";
                } else if (line.contains("Tiger")) {
                    currentSpeciesName = "tiger";
                } else if (line.contains("Bear")) {
                    currentSpeciesName = "bear";
                } else if (!line.isEmpty()) {
                    String[] names = line.split(",");
                    for (String name : names) {
                        if (currentSpeciesName.equals("hyena")) {
                            hyenaNames.add(name);
                        } else if (currentSpeciesName.equals("lion")) {
                            lionNames.add(name);
                        } else if (currentSpeciesName.equals("tiger")) {
                            tigerNames.add(name);
                        } else if (currentSpeciesName.equals("bear")) {
                            bearNames.add(name);
                        }
                    }
                }
            }
// In this part, I had to go back and redo a lot, because I had put brackets in the wrong spot. It grabs the age and species.
            nameReader.close();

            File myFile = new File("arrivingAnimals.txt");
            Scanner reader = new Scanner(myFile);
// This checks what kind of animal it's looking at, so it can give it a name.
            int hyenaCount = 0;
            int lionCount = 0;
            int tigerCount = 0;
            int bearCount = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine().trim();

                if (data.isEmpty()) {
                    continue;
                }

                String[] commaParts = data.split(",");
                String[] wordParts = commaParts[0].trim().split(" ");

                int age = Integer.parseInt((wordParts[0]));
                String species = wordParts[4].toLowerCase();

                String sex = wordParts[3];
                String season = commaParts[1].trim().split(" ")[2];
                String color = commaParts[2].trim();
                String weight = commaParts[3].trim();
                String origin = commaParts[4].trim() + "," + commaParts[5].trim();

                System.out.println("Age: " + age + " | Species: " + species);
                if (species.equals("hyena")) {
                    Hyena newHyena = new Hyena();
                    hyenaCount++;
                    newHyena.setUniqueID(genUniqueID(species, hyenaCount));
                    newHyena.setName(hyenaNames.remove(0));
                    newHyena.setAge(age);
                    newHyena.setSpecies(species);
                    newHyena.setSex(sex);
                    newHyena.setColor(color);
                    newHyena.setSound(Hyena.sounds[hyenaCount - 1]);
                    newHyena.setWeight(weight);
                    newHyena.setOrigin(origin);
                    newHyena.setArrivalDate("2024-04-07");
                    newHyena.setBirthDate(genBirthDay(age, season));
                    animalList.add(newHyena);
                } else if (species.equals("lion")) {
                    Lion newLion = new Lion();
                    lionCount++;
                    newLion.setUniqueID(genUniqueID(species, lionCount));
                    newLion.setName(lionNames.remove(0));
                    newLion.setAge(age);
                    newLion.setSpecies(species);
                    animalList.add(newLion);
                    newLion.setSex(sex);
                    newLion.setColor(color);
                    newLion.setSound(Lion.sounds[lionCount - 1]);
                    newLion.setWeight(weight);
                    newLion.setOrigin(origin);
                    newLion.setArrivalDate("2024-04-07");
                    newLion.setBirthDate(genBirthDay(age, season));
                } else if (species.equals("tiger")) {
                    Tiger newTiger = new Tiger();
                    tigerCount++;
                    newTiger.setUniqueID(genUniqueID(species, tigerCount));
                    newTiger.setName(tigerNames.remove(0));
                    newTiger.setAge(age);
                    newTiger.setSpecies(species);
                    animalList.add(newTiger);
                    newTiger.setSex(sex);
                    newTiger.setColor(color);
                    newTiger.setSound(Tiger.sounds[tigerCount - 1]);
                    newTiger.setWeight(weight);
                    newTiger.setOrigin(origin);
                    newTiger.setArrivalDate("2024-04-07");
                    newTiger.setBirthDate(genBirthDay(age, season));
                } else if (species.equals("bear")) {
                    Bear newBear = new Bear();
                    bearCount++;
                    newBear.setUniqueID(genUniqueID(species, bearCount));
                    newBear.setName(bearNames.remove(0));
                    newBear.setAge(age);
                    newBear.setSpecies(species);
                    animalList.add(newBear);
                    newBear.setSex(sex);
                    newBear.setColor(color);
                    newBear.setSound(Bear.sounds[bearCount -1]);
                    newBear.setWeight(weight);
                    newBear.setOrigin(origin);
                    newBear.setArrivalDate("2024-04-07");
                    newBear.setBirthDate(genBirthDay(age, season));
                }
                if (speciesCount.containsKey(species)) {
                    int currentCount = speciesCount.get(species);
                    speciesCount.put(species, currentCount + 1);
                } else {
                    speciesCount.put(species, 1);
                }

            }
// This is the part that actually puts everything together, at least I think so...It prints out all the animal details.
            reader.close();

            try {
                FileWriter writer = new FileWriter("zooPopulation.txt");
                String[] habitats = {"hyena", "lion", "tiger", "bear",};
                writer.write("******* Zoo Population and Habitat Assignment Report *******\n\n");
                for (String habitat : habitats) {
                    writer.write(habitat.substring(0,1).toUpperCase() + habitat.substring(1) + " Habitat:\n\n");
                    for (Animal animal : animalList) {
                        if (animal.getSpecies().equals(habitat)) {
                            writer.write(animal.getUniqueID() + "; "
                                    + (animal.getAge()) + " years old; "
                                    + animal.getName() + "; "
                                    + "birthDate: " + animal.getBirthDate() + "; "
                                    + animal.getColor() + "; "
                                    + animal.getSex() + "; "
                                    + animal.getWeight() + "; "
                                    + (habitat.equals("hyena")? "laugh: " : "roar: ") + animal.getSound() + "; "
                                    + animal.getOrigin() + "; "
                                    + "arrived: " + animal.getArrivalDate()
                                    + "\n");
                        }
                    }
                    writer.write("\n");
                        }
                    writer.close();
                System.out.println("Report successfully created!");


        } catch (Exception e) {
            System.out.println("Could not find the animal file!");
            e.printStackTrace();
        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String genUniqueID(String species, int count) {
        String prefix = species.substring(0, 1).toUpperCase() + species.substring(1, 2);
        String idNumber = String.format("%02d", count);
        return prefix + idNumber;

    }

    public static String genBirthDay(int age, String season) {
        season = season.toLowerCase();
        int birthYear = 2026 - age;
        String monthDay = "01-01";
        if (season.contains("spring")) {
            monthDay = "03-21";
        } else if (season.contains("summer")) {
            monthDay = "06-21";
        } else if (season.contains("fall")) {
            monthDay = "09-21";
        } else if (season.contains("winter")) {
            monthDay = "12-21";
        }
        return birthYear + "-" + monthDay;
    }
}
