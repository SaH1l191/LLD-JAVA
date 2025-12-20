 

//abstraction : 
// epxosing necesary details while hdiing the implementation detials 

// eg using a car , we dont need to know the engine or wheels of the car, we just need to know that it is a car and can move
// so we expose only the necessary details , while hiding the implementation details

// in java done using abstracts and interfaces
 
// The problems in the Above Code which doesn't uses the Concept of Abstraction are as follows :
// 1. Repetitive Code: Both Dog and Cat have the sleep() method, resulting in duplicate logic.
// No Common Structure: If a new animal is added, the same methods need to be redefined, leading to code redundancy.
// 3. Lack of Flexibility: You can't refer to all animals in a common way (e.g., treating a Dog and Cat as Animal).
// 4. Tightly Coupled Code: You must directly interact with individual classes (Dog, Cat), making the code less reusable and harder to maintain.

//abstract acts as blueprint for other class 
  // reusability, abstract methods for unique behvious 
    //flexibility,  
     //concrete methods 

// IMP =>? in abstraction 
// Animal a = new Animal();  // Error! Cannot instantiate an abstract class.


// Animal a = new Dog();
// You are creating a reference of type Animal, but the actual object is of type Dog. This means that a can refer to any class that implements Animal (like Dog, Cat, etc.).

// Example:
// Animal a = new Dog();  // You can now call methods like a.makeSound()
// a = new Cat();          // Switch to Cat without changing any other code
abstract class Animal {
    abstract void makeSound();  // No implementation here
}
//abstracts can be final , non final , static ,non static , 
// can have constructors 

//interface do not have final , constructors 
// Always non-final,are non-static by default,static for nested interfaces

class Dog extends Animal {
    void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    void makeSound() {
        System.out.println("Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();  // This works because Dog provides the implementation
        a.makeSound();         // Output: Woof!

        a = new Cat();         // Now switch to Cat
        a.makeSound();         // Output: Meow!
    }
}
//Why not instantiate Animal directly?
// Incomplete Implementation:

// can do like 
// abstract Animal{ Int a = 10 }



//disadvantaegd : 
// complexity ,a

//overusing abstraction 
//causes redudant code , confusion , implmentation overflow 
// defining fly method in animal , so dog class has to implemnt it 

//best way to aboid and solution : 
//interfaces 
// can have multiple interface s => multiple interitences 
//eg : keep swin in one interface , keep fly in another interface


// learn interface next to understand 