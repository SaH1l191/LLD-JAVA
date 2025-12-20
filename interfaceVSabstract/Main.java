// class Animal {
//     public static void makeSound(){
//         System.out.println("bark ");
//     }
//     void play(){
//         System.out.println("playing");
//     }
// }

//static are the methods that belong to the class itself rather than the objcets that we create 
//they can be called directly without needin to create an object 
// public class Main {
//     public static void main(String[] args) { 
//         Animal.makeSound(); 
//         Animal a =new Animal();
//         a.makeSound();  // can access static anywhere basically 
//     }
// }
//interface : 
// do not have objects 
// cannot belong to an object 
// must belong to the interface itself 
//ie static 
//if any 
// interface Animal {
//     int LEGS = 4;
//     void makeSound();
// }
// All Animals have this constant rule leg = 4 
// So Java enforces:
// public static final int LEGS = 4;
// but after java 8 
// interface Animal {
//     int LEGS = 4;
//     void makeSound();
//     default void breathe() {
//         System.out.println("Breathing...");
//     }
// }
// What this means
// The interface now has a method body
// Any class implementing Animal:
// Automatically gets breathe()
// Does NOT need to implement it
// Can default methods be overridden? => Yes
// interfaces cannot have constructors , because they define the contract , 
// they dont hold state unlike abstract classes 
// by state it means 
// Eg: 
// Each Dog object can have different values:
// Dog d1 = new Dog();
// d1.age = 3;
// Dog d2 = new Dog();
// d2.age = 5;
// So:
// Objects remember data
// That data is called state
//so primarily interface to implement => multiple inheirtance 
// and the for non classes that needs contract  
//abstract needs to be implemented when we have to define a template  
// hidinmg implementation or revevant fnc from chldren 
// abstract methods cannot have a body.
// If a method is abstract, it’s only a declaration:
// abstract void eat();  //  no body
// Concrete methods (with a body) are not abstract:
// void eat() {           // concrete method
//     System.out.println("some low level logic");
// }
// 2. What “hiding implementation” actually means
// “Hiding implementation” doesn’t mean preventing the subclass from seeing it. It means:
// The subclass does not have to rewrite or know the internal details of a method
// It only uses the method as-is, trusting it works correctly
// If the method were abstract, the subclass would have to implement all the logic themselves
// Example:
// abstract class Animal {
//     // Low-level code that subclasses don't need to know
//     void eat() {
//         System.out.println("Chewing, digesting, swallowing...");
//     }
//     abstract void makeSound(); // contract only
// }
// class Dog extends Animal {
//     void makeSound() {
//         System.out.println("Woof!");
//     }
// }
// Dog doesn’t know or care about the details of eat(). It just calls eat() whenever it wants.
// Dog must implement makeSound(). That’s the contract.
// 3. Analogy
// Think of an abstract class as a machine blueprint:
// in interface 
// interface Animal {
//     void makeSound(); // this is the abstract method
//     default void breathe() {
//         System.out.println("Breathing...");
//     }
//     static void info() {
//         System.out.println("Animal interface");
//     }
// }
// here in below eg , 
// cannot reassign the a.legs thorws error if  did ,
// also watch the overriding the interface function etc 
// interface Animal {
//     int LEGS = 10;           // constant
//     void makeSound();        // abstract method
//     default void play() {    // default method in interface
//         System.out.println("Playing!");
//     }
// }
// class Dog implements Animal {
//     public void makeSound() {
//         System.out.println("Woof!");
//     }
//     @Override
//     public void play() {      // override default method
//         System.out.println("Animal Playing!");
//     }
// }
// public class Main {
//     public static void main(String[] args) {
//         Animal a = new Dog(); 
//         a.makeSound();         
//         System.out.println(a.LEGS); // 10
//         a.play();                  // Animal Playing!
//     }
// }
// Animal a = new Dog(); 
// This is polymorphism: reference type is the interface, object type is the class implementing the interface.
// You can only call methods declared in the interface:
// If Dog had extra methods not in Animal, you cannot call them through the Animal reference:
// class Dog implements Animal {
//     public void fetch() { System.out.println("Fetching..."); }
// }
// Animal a = new Dog();
// a.fetch(); //  compile error! Animal doesn't know about fetch()
// But the actual object is still a Dog, so if you cast:
// ((Dog)a).fetch(); //  now works
// same abstract class eg 
// abstract class Animal {
//     int LEGS = 10;          
//     abstract void makeSound();       
//     void play() {    
//         System.out.println("Playing!");
//     }
// }
// class Dog extends Animal {
//     public void makeSound() {
//         System.out.println("Woof!");
//     }
//     @Override
//     public void play() {      // override default method
//         System.out.println("Animal Playing!");
//     }
// }
// public class Main {
//     public static void main(String[] args) {
//         Animal a = new Dog(); 
//         a.makeSound();         
//         System.out.println(a.LEGS); // 10
//         a.play();                  // Animal Playing!
//         Animal b=  new Dog();
//         b.LEGS = 20202; 
//         System.out.println(b.LEGS); // 10
//     }
// }
// eg -2 
// interface pet { 
//       void play() ;
// }
// abstract class Animal {
//     String name ;
//     Animal(String name ){
//         this.name = name;
//     }
//     abstract void makeSound();
// }
//  class Dog extends Animal implements pet {
//     Dog(String name){
//         super(name);
//     }
//     public void makeSound() {
//         System.out.println("Woof!");
//     }
//     @Override
//     public void play() {       
//         System.out.println("Pet Playing!");
//     }
// }
// public class Main {
//     public static void main(String[] args) {
//         Dog a = new Dog("Buddy"); 
//         a.makeSound();          
//         a.play();                  // Animal Playing!
//     }
// }
//abstrac classes cannot be instantiated 
//bcz they are incomplete blueprint meant to be extended 
//eg :  
// abstract class Animal {
//     String name ;
//     Animal(String name ){
//         this.name = name;
//     }
//     abstract void makeSound();
// }
//  class Dog extends Animal  {
//     Dog(String name){
//         super(name);
//     }
//     public void makeSound() {
//         System.out.println("Woof!");
//     }
//     public void play() {       
//         System.out.println("Pet Playing!");
//     }
// }
// public class Main {
//     public static void main(String[] args) {
//         Animal a = new Animal(); 
//         a.makeSound();                    
//     }
// }
// Rule of thumb for constructors in parent class and their subclass
// Superclass has a no-arg constructor → subclass doesn’t need to explicitly call anything.
// Superclass has only parameterized constructors → subclass must call super(...) explicitly.
//another imp question : 
// can a abstract class implement a interface 
interface Pet {

    void play();
}

abstract class Animal implements Pet {

    String name;

    Animal(String name) {
        this.name = name;
    }

    abstract void sound();

    @Override
    public void play() {
        System.out.println("animal playing ");
    }
}

class Dog extends Animal {

    Dog(String name) {
        super(name);
    }
    @Override
    void sound(){
        System.out.println("dog playing ");
    }

}
// we didnt write play again in dog as dog inherits the function play naturally 



// abstract limitations : 
//Interfaces allow multiple inheritance, abstract classes do not.
// . Abstract classes limitation


// abstract class A {
//     void methodA() { System.out.println("A"); }
// }

// abstract class B {
//     void methodB() { System.out.println("B"); }
// }

//  Not allowed
// class C extends A, B { }


// Java does not allow a class to extend two classes (abstract or not).

// Why?

// If both classes have a method with the same name, compiler wouldn’t know which one to pick → ambiguity problem.

//  You can only do:

// class C extends A { } // allowed

// 3. Interfaces allow multiple inheritance
// interface X {
//     void methodX();
// }

// interface Y {
//     void methodY();
// }

// //  Allowed
// class Z implements X, Y {
//     public void methodX() { System.out.println("X"); }
//     public void methodY() { System.out.println("Y"); }
// }





// abstract class can have variables (with values) and abstract methods
// 1. Abstract class with variables and an abstract method
// abstract class Animal {
//     // Variables with values
//     String type = "Mammal";
//     int legs = 4;

//     // Abstract method (no implementation)
//     abstract void makeSound();

//     // Optional: concrete method
//     void printInfo() {
//         System.out.println("Type: " + type + ", Legs: " + legs);
//     }
// }


// type and legs are instance variables with values → every object of a subclass will inherit these.

// makeSound() is abstract → must be implemented in the subclass.

// printInfo() is concrete → can be used as-is or overridden.

// 2. Subclass implementing abstract method
// class Dog extends Animal {
//     @Override
//     void makeSound() {
//         System.out.println("Woof!");
//     }
// }
// Dog inherits the variables type and legs.
// It must implement the abstract method makeSound()





//diamond pattern solving using animal VS pet 
//using default 
//  “This method has an implementation inside the interface.”
// “Classes implementing this interface may inherit this method directly.”
// Without default, the method is assumed to be abstract (no body allowed).
// interface  Animal {
//     default void commonFnc(){
//         System.out.println("Animal!");
//     }
// }
 
// interface  Pet {
//     default void commonFnc(){
//         System.out.println("Pet Animal!");
//     }
// }
// class Dog implements  Animal,Pet{
    
//     @Override
//     public void commonFnc() {
//         Animal.super.commonFnc();
//         Pet.super.commonFnc();
//         System.out.println("Pet Dog Animal's own FncCall!");
//     } 
// }

// public class Main {
//     public static void main(String[] args) {
//         Dog a = new Dog(); 
//         a.commonFnc(); 
//     }
// }
//default method have an body (to have backward compatibility )

//otherwise , interfaces do not have a funcition impemlents because 
// as it acts as an abstract method 
// interface e { void play() //abstract method }


// asbtract body does not have body 
//default have body and can be overloadded optionally 



// if a class implements default func of a interface and that class
// extends its super class whcih has the same funciton 
// which will be give n priroity => the parent class 



// if a class impleents two interfaces with the same name , then the corr esopnsing 
// calss must provide explicitly a overriding function resolving that 



//can a class implemnting a default function of interface , can it be 
//used as an abstract class ? 
// no => because defualt means concrete (has body) and 
// something concrete canot define somthing abstract so false 




//Can default methods access instance variables of the implementing class? Why or why not?
//  Answer:  No, default methods cannot access instance variables of the implementing class because interfaces do not have state.
//  Reason:
//  Default methods are stateless and only work with parameters and their internal logi


// limitations of defualt method is 
// / cannot have instance variables
// they cannot use super to refre the impmenting class's parnent 
//


// why do you use constructos in abstract class 
// when we can in subclassses 
// common logic could be written in abstract and explict logic 
// needs to be wrirtten , 
// follows dry principle 









