import java.util.ArrayList;
import java.util.List;

public class Island {
    private int width;
    private int length;
    private int animalCount = 0;
    private Cell[][] field;

    public Island(int width, int length) {
        this.width = width;
        this.length = length;
        this.field = new Cell[width][length];
    }

    public void setAnimals(List<Animal> animalList) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
        for (Animal animal : animalList) {
            field[animal.getX()][animal.getY()].setEmpty(false);
            field[animal.getX()][animal.getY()].setAnimalList(animal);
        }
        animalCount = animalList.size();
    }

    public List<Animal> checkCells() {
        List<Animal> animalListToReturn = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (!field[i][j].isEmpty()) {
                    ArrayList<Animal> list = field[i][j].getAnimalList();
                    animalListToReturn.addAll(list);
                    if (list.size() > 1) { // в одной клетке 2 животных
                        Animal animal1 = list.get(0);
                        Animal animal2 = list.get(1);
                        Animal newBornAnimal = null;
                        if (animal1.getClass().getName().equals(animal2.getClass().getName())) {
                            // если одинаковые животные, то рождается новое животное

                            if (animal1 instanceof swimmableAnimal) {
                                /*newBornAnimal = AnimalFactory.createAnimal(Animals.walkableAnimal, i, j, animal1.getName() + animal2.getName(),
                                        animal1.getColor());*/
                                newBornAnimal = AnimalFactory.createAnimal(Animals.swimmableanimal,
                                        i, j, animal1.getName() + animal2.getName(), animal1.getColor());
                               /* newBornAnimal = new swimmableAnimal(i, j, animal1.getName() + animal2.getName(),
                                        animal1.getColor(), 10);*/
                            } else if (animal1 instanceof walkableAnimal) {
                                newBornAnimal = new walkableAnimal(i, j, animal1.getName() + animal2.getName(),
                                        animal1.getColor(), 10);
                            } else if (animal1 instanceof flyableAnimal) {
                                newBornAnimal = new flyableAnimal(i, j, animal1.getName() + animal2.getName(),
                                        animal1.getColor(), 10);
                            } else {
                                newBornAnimal = null;
                            }
                            //list.add(newBornAnimal);
                            if (newBornAnimal != null) {
                                System.out.println("Родилось новое животное класса " + newBornAnimal.getClass().getName());
                            }
                            animalListToReturn.add(newBornAnimal);
                        } else if (animal1.getSize() > animal2.getSize()) {
                            animal1.eat(animal2);
                            animalListToReturn.remove(animal2);
                        } else {
                            animal2.eat(animal1);
                            animalListToReturn.remove(animal1);
                        }
                    }
                }
            }
        }
        //return null;
        return animalListToReturn;
    }

    public enum Animals {
        walkableAnimal,
        swimmableanimal,
        flyableanimal
    }

    public static class AnimalFactory {
        public static Animal createAnimal(Animals animal, int i, int j, String name, String color) {
            switch (animal) {
                case walkableAnimal:
                    return new walkableAnimal(i, j, name, color, 10);
                case flyableanimal:
                    return new flyableAnimal(i, j, name, color, 10);
                case swimmableanimal:
                    return new swimmableAnimal(i, j, name, color, 10);
                default:
                    return null;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Cell[][] getField() {
        return field;
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }

}

