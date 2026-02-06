


  class Character implements Cloneable  { 
    private String name ;
    private String gender;
    private int age;

    public Character(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }

    // Getters 
    public String getName() {
        return name;
    } 
    public String getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
}

class CharacterFactory {
    private Character characterPrototype;
    public CharacterFactory() {
        characterPrototype = new Character("Default", "Unknown", 0);
    }
    public Character createCharacterWithNewName(String name) throws CloneNotSupportedException {
        Character cloned= characterPrototype.clone();
        cloned = new Character(name, cloned.getGender(), cloned.getAge());
        return cloned;
    }
    //......
}


public class T {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        CharacterFactory factory = new CharacterFactory();
        try {
            Character hero = factory.createCharacterWithNewName("Hero");
            System.out.println("Character Name: " + hero.getName());


            Character villain = factory.createCharacterWithNewName("Villain");
            System.out.println("Character Name: " + villain.getName());
            
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}