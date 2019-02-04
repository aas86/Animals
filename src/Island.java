import java.util.ArrayList;

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

    public void setAnimals(ArrayList<Animal> animalList) {
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

    public Animal checkCells() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (!field[i][j].isEmpty()) {
                    ArrayList<Animal> list = field[i][j].getAnimalList();
                    if (list.size() > 1) { // в одной клетке 2 животных
                        Animal animal1 = list.get(0);
                        Animal animal2 = list.get(1);
                        if (animal1.getClass().getName().equals(animal2.getClass().getName())) {
                            // если одинаковые животные, то рождается новое животное
                            Animal newAnimal = new Animal(i, j, animal1.getName() + animal2.getName(),
                                    animal1.getColor(), 10);
                            list.add(newAnimal);
                            System.out.println("Родилось новое животное");
                            return newAnimal;

                        } else if (animal1.getSize() > animal2.getSize()) {
                            animal1.eat(animal2);
                            list.remove(animal2);
                            return animal2;
                        } else {
                            animal2.eat(animal1);
                            list.remove(animal1);
                            return animal1;
                        }

                    }
                }
            }
        }
        return null;
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
