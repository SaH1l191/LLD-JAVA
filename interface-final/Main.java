 
interface Animal {
    void makeSound();  
} 
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


// interface :
// similar to class  and a collection of abstract methods and static constants 
//Interface is NOT a class
// Interface is NOT for holding state
// Interface is about behavior contract
// Since Java 8, it can also have default & static methods


// An interface in Java defines a contract that specifies
//  what a class must do, without defining how it does it. I
// t can contain abstract methods, default methods, static methods,
//  and constants.

// Every variable in an interface is:
// public static final

//interfaces can have private mthods as well 


