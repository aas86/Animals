
import java.util.ArrayList;
import java.util.List;

public class Animal {
    private int x;
    private int y;
    private String name;
    private String color;
    private int size;
    private int satiety = 5; // насыщение животного по умолчанию будет 5 из 10

    public Animal(int x, int y, String name, String color, int size) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void move(int dx, int dy, Island island) {
        // Нужно проверить что не вылезли за пределы острова
        if (this.x + dx < 0 || this.x + dx > island.getWidth() - 1) {
            //System.out.println(island.getWidth());
            //System.out.println("выход за пределы острова поэтому ");
        } else {
            this.x = this.x + dx;
        }
        if (this.y + dy < 0 || this.y + dy > island.getLength() - 1) {
            //System.out.println("выход за пределы острова");
        } else {
            this.y = this.y + dy;
        }
    }

    public void eat(Animal animal) {
        // Когда одно животное съедает другое к его насыщению прибавляется насыщение съеденного ))
        this.satiety = satiety + animal.getSatiety();
        System.out.println("Животное " + this.getName() + " съело животное " + animal.getName());
    }
}

class flyableAnimal extends Animal {
    public flyableAnimal(int x, int y, String name, String color, int size) {
        super(x, y, name, color, size);
    }
}

class swimmableAnimal extends Animal {
    public swimmableAnimal(int x, int y, String name, String color, int size) {
        super(x, y, name, color, size);
    }
}

class walkableAnimal extends Animal {
    private List<Claw> claws = new ArrayList<>();

    public walkableAnimal(int x, int y, String name, String color, int size) {
        super(x, y, name, color, size);
        for (int i = 0; i < 5; i++) { // пусть будет по 5 когтей у ходячих
            claws.add(new Claw(Math.random() * 10));
        }
    }
}

class Claw {
    private double clawLength;

    public Claw(double clawLength) {
        this.clawLength = clawLength;
    }

    public double getClawLength() {
        return clawLength;
    }

    public void setClawLength(double clawLength) {
        this.clawLength = clawLength;
    }
}

