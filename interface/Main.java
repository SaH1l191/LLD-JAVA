// Define the interface
interface Animal {
    void makeSound();  // Method declaration
}

// Concrete class implementing the interface
class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Now we can instantiate classes that implement the Animal interface
        Animal dog = new Dog();  // dog is an instance of Dog, which implements Animal
        dog.makeSound();  // Output: Woof!

        Animal cat = new Cat();  // cat is an instance of Cat, which implements Animal
        cat.makeSound();  // Output: Meow!
    }
}
//abstracts can be final , non final , static ,non static , 
// can have constructors 

//interface do not have final , constructors 
// Always non-final,are non-static by default,static for nested interfaces