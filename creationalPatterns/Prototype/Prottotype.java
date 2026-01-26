package Prototype;

// interface Prototype {
//     Prototype clone();
// }
// class Employee implements Prototype {
//     private String name;
//     public Employee(String name) {
//         this.name = name;
//     }
//     public String getName() {
//         return name;
//     }
//     public Employee clone() {
//         return new Employee(name);
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
// }
// public class Prottotype {
//     public static void main(String[] args) {
//         Employee emp1 = new Employee("John Doe");
//         Employee emp2 = emp1.clone();
//         emp2.setName("Maria");
//         System.out.println("Employee 1 Name: " + emp1.getName());
//         System.out.println("Employee 2 Name: " + emp2.getName());
//     }
// }
class Character implements Cloneable {

    String name;
    int health;
    int attackPower;
    int level;

    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }

    @Override
    public String toString() {
        return "Character{"
                + "name='" + name + '\''
                + ", health=" + health
                + ", attackPower=" + attackPower
                + ", level=" + level
                + '}';
    }
}

class CharacterFactory {

    private Character prototypeCharacter;

    public CharacterFactory() {
        prototypeCharacter = new Character("DefaultName", 100, 50, 1);
    }

    public Character createCharacterWithNewName(String name) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter.name = name;
        return clonedCharacter;
    }
}

public class Prottotype {

    public static void main(String[] args) {
        CharacterFactory f1 = new CharacterFactory();
        try {
            Character c1 = f1.createCharacterWithNewName("Alice");
            Character c2 = f1.createCharacterWithNewName("Bob");
            System.out.println(c1);
            System.out.println(c2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
