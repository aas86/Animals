import java.util.ArrayList;

/**
 * Created by Andrey on 04.02.2019.
 */
public class Main {
    public static void main(String[] args) {
        //TODO считать входные данные из файла(входными данными, как я понял д.б. размер острова и животные)
        //А пока сымитируем входные данные
        int length = 3;
        int width = 3;

        Island islandHTL = new Island(width, length);

        Animal swim = new swimmableAnimal(0, 0, "KvaKva", "green", 10);
        Animal walk = new walkableAnimal(1, 1, "MuMu", "brown", 20);
        Animal fly = new flyableAnimal(2, 2, "KarKar", "black", 30);
        Animal fly2 = new flyableAnimal(0, 0, "ChirikChirik", "black", 20);
        ArrayList<Animal> animalList = new ArrayList<>();
        animalList.add(swim);
        animalList.add(walk);
        animalList.add(fly);
        animalList.add(fly2);
        int i = 0;
        while (i < 10) {
            islandHTL.setAnimals(animalList);
            Animal animal = islandHTL.checkCells();
            if (animalList.contains(animal)) {
                animalList.remove(animal);
            } else if (animal != null) {
                animalList.add(animal);
            }
            animalList.forEach(x -> x.move(sign(), sign(), islandHTL));
            i++;
        }
    }

    private static int sign() {
        double randomNumber = Math.random();
        if (randomNumber > 0 && randomNumber < 0.33) {
            return (int) Math.floor(randomNumber - 1);
        } else if (randomNumber > 0.33 && randomNumber < 0.66) {
            return 0;
        } else {
            return (int) Math.ceil(randomNumber);
        }
    }
}





