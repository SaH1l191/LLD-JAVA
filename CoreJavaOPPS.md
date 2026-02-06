# Core Java OOPS Concepts

## Table of Contents

1. [Introduction to Classes and Objects](#introduction-to-classes-and-objects)
2. [Class Relationships: A Deep Dive](#class-relationships-a-deep-dive)
3. [Constructor and Its Types](#constructor-and-its-types)
4. [This Keyword in OOPS](#this-keyword-in-oops)
5. [Polymorphism and Its Types](#polymorphism-and-its-types)
6. [Inheritance and Its Types](#inheritance-and-its-types)
7. [Encapsulation](#encapsulation)
8. [Abstraction](#abstraction)
9. [Generics and Wildcards](#generics-and-wildcards)
10. [Access Modifiers](#access-modifiers)

---

## Introduction to Classes and Objects

### Topic Tags:
- OOP
- Java

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

Object-Oriented Programming (OOP) is a programming paradigm that organizes code into objects, which represent real-world entities. It allows developers to model complex systems by breaking them down into smaller, manageable pieces.

The foundation of OOP lies in classes and objects, which together enable the creation of structured, reusable, and scalable code. 🧩

Imagine a car manufacturing company. To produce cars, the company uses a design blueprint. The blueprint defines the structure and functionality of a car (e.g., the number of wheels, type of engine, color, etc.).

However, the blueprint itself is not a car—it is only a guide. The actual cars manufactured from this blueprint are like objects, and the blueprint itself is a class. 🚗📏

### 📋 What is a Class?

A class is a blueprint for creating objects. It defines the properties (attributes) and behaviors (methods) that the objects will have. Think of it as a template that outlines the structure and capabilities of an object but does not represent any actual instance. 🏗️

#### Key Characteristics of a Class:

1. **Attributes (State):** These are variables defined within the class that describe the characteristics of the object. 📝
2. **Methods (Behavior):** These are functions defined in the class that describe what the objects can do. 🔧
3. **Constructor:** A special method used to initialize the attributes of the class when an object is created. 🛠️

```java
public class Car {
    // Attributes : 
    String manufacturer;
    String model;
    int year;

    // Constructor : 
    public Car(String manufacturer, String model, int year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }

    // Methods : 
    public void startEngine() {
        System.out.println("The " + year + " " + manufacturer + " " + model + "'s engine has started.");
    }
    public void displayInfo() {
        System.out.println("Car Info: " + manufacturer + " " + model + " (" + year + ")");
    }
}
```

### ⚡ What is an Object?

An object is an instance of a class. It represents a specific realization of the class blueprint, with its own unique set of data.

In the car analogy, each manufactured car is an object, and it holds specific values for its attributes (e.g., make: "Toyota", model: "Corolla", year: 2021).

#### 🔑 Key Characteristics of an Object:

1. **State:** Represented by the object's attributes.
2. **Behavior:** Defined by the methods the object can execute.
3. **Identity:** A unique reference to the object in memory.

```java
public class Main {
    public static void main(String[] args) {

        // Creating objects
        Car car1 = new Car("Toyota", "Corolla", 2021);
        Car car2 = new Car("Honda", "Civic", 2022);

        // Using objects
        car1.startEngine();  // Output: The 2021 Toyota Corolla's engine has started.
        car2.startEngine();  // Output: The 2022 Honda Civic's engine has started.
        car1.displayInfo();  // Output: Car Info: Toyota Corolla (2021)
        car2.displayInfo();  // Output: Car Info: Honda Civic (2022)
    }
}
```

Here, `car1` and `car2` are objects of the Car class, each holding specific data for the attributes and performing actions through methods.

### 🏗️ Real-World Analogy:

A car manufacturing blueprint is a perfect analogy for understanding classes and objects:

1. **Blueprint (Class):** It specifies the design and functionality of the car but does not represent an actual car. 🏗️🚗
2. **Cars (Objects):** Each car produced from the blueprint is unique, with specific values for attributes like color, manufacturer, and model Number, but all cars share the same general structure and behavior defined by the blueprint. 🌟

For example:

- **Blueprint:** Car design with details like "4 wheels," "engine capacity," "fuel type." 🛠️🔧

- **Objects:**
  - Car 1: A red Hyundai i20, 1.8L engine capacity and petrol fuel type. 🔴🚗
  - Car 2: A blue Honda Civic, 2.0L engine capacity and diesel fuel type. 🔵🚙

### 🤔 Why Use Classes and Objects?

1. **Reusability:** Write a class once and create multiple objects with different data. ♻️
2. **Modularity:** Classes help organize code into logical sections, making it easier to debug and maintain. 🧩
3. **Abstraction:** Focus on the essential details of an entity without worrying about the internal workings. 🔍
4. **Scalability:** Adding new features is straightforward without affecting existing code. 📈

### 🎯 Conclusion

Understanding classes and objects is the first step in mastering Object-Oriented Programming. A class provides the structure and design, while objects bring that structure to life with specific data. 💡

Together, they enable the creation of modular, reusable, and scalable applications. 🔄

By practicing these concepts, you lay a strong foundation for tackling more advanced topics in OOP. 🚀

---

## Class Relationships: A Deep Dive

**Topic Tags:**
- OOP
- System Design
- LLD

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

Hey there, fellow coder! 😊👋

Today, we're going on an exciting journey into the world of class relationships and diagrams. In this article, we'll explore all kinds of relationships in object-oriented design using Java—complete with real-life examples, short code snippets, and easy-to-follow explanations.

By the end, you'll even create a UML diagram that ties everything together. So, grab your favorite snack 🍿, and let's dive right in!

### 🌳 The Family Tree of Classes: Inheritance

Inheritance represents an "is-a" relationship where a subclass inherits properties and behaviors from its parent class.

Think of it like a family tree—just as a Dog is an Animal, a subclass extends the functionality of its superclass.

```java
// Parent class
class Animal {
  void eat() {
    System.out.println("Animal is eating.");
  }
}

// Subclass inheriting from Animal
class Dog extends Animal {
  void bark() {
    System.out.println("Dog barks: Woof Woof!");
  }
}

public class InheritanceDemo {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Inherited behavior
    dog.bark(); // Specific behavior
  }
}
```

**Explanation:**
- Animal is the parent class with a basic eat() method.
- Dog extends Animal and adds its own bark() method.
- The Dog class inherits behavior from Animal while adding its unique actions.

### Side by Side: Association

Association is a general relationship where one class knows about or uses another. It's like a friendship—two entities are aware of each other, but they exist independently.

```java
// A Person can have a Car.
class Car {
  String model;
  Car(String model) {
    this.model = model;
  }
  void drive() {
    System.out.println("Driving a " + model);
  }
}

class Person {
  String name;
  // Association: A Person "has a" Car.
  Car car;
  Person(String name, Car car) {
    this.name = name;
    this.car = car;
  }
  void goForDrive() {
    System.out.println(name + " is going for a drive.");
    car.drive();
  }
}

public class AssociationDemo {
  public static void main(String[] args) {
    Car car = new Car("Tesla Model 3");
    Person person = new Person("Alice", car);
    person.goForDrive();
  }
}
```

**Explanation:**
- Person has a reference to Car, representing an association.
- Both Person and Car exist independently. The Car doesn't rely solely on the Person for its existence.

### Aggregation: Grouping with a Twist

Aggregation is a specialized form of association that represents a "has-a" relationship where the parts can exist independently of the whole—but they are grouped together by a container.

Think of a Team and its Players: a team has players, yet the players can exist even if the team is disbanded.

```java
import java.util.ArrayList;
import java.util.List;

class Player {
  String name;
  Player(String name) {
    this.name = name;
  }
}

class Team {
  String teamName;
  // Aggregation: A team "has" players.
  List<Player> players = new ArrayList<>();
  Team(String teamName) {
    this.teamName = teamName;
  }
  void addPlayer(Player player) {
    players.add(player);
  }
  void showTeam() {
    System.out.println("Team " + teamName + " has players:");
    for (Player p : players) {
      System.out.println(" - " + p.name);
    }
  }
}

public class AggregationDemo {
  public static void main(String[] args) {
    Team team = new Team("Warriors");
    team.addPlayer(new Player("Stephen"));
    team.addPlayer(new Player("Klay"));
    team.showTeam();
  }
}
```

**Explanation:**
- Team aggregates Player objects.
- Players exist independently of the team—they can join or leave different teams.
- This indicates that while the Team holds and manages Player instances, the players are not solely dependent on the team for their existence.

#### Key Differences Between Association and Aggregation:
- **Association** is a general relationship (friendship) with no ownership implied; objects simply interact or use each other.
- **Aggregation** represents a "has-a" relationship where one object (the whole) contains or references other objects (the parts), but the parts can exist independently of the whole.

### Bond for Life: Composition

Composition is a stronger form of aggregation with full ownership—if the whole is destroyed, the parts cannot exist independently. Think of a House and its Rooms: without the house, the rooms cease to exist.

```java
class Room {
  String name;
  Room(String name) {
    this.name = name;
  }
}

class House {
  // Composition: A House is composed of Rooms.
  private Room livingRoom;
  private Room kitchen;
  House() {
    // Rooms are created and owned by the House.
    livingRoom = new Room("Living Room");
    kitchen = new Room("Kitchen");
  }
  void showHouse() {
    System.out.println(
        "House contains: " + livingRoom.name + " and " + kitchen.name);
  }
}

public class CompositionDemo {
  public static void main(String[] args) {
    House house = new House();
    house.showHouse();
  }
}
```

**Explanation:**
- House creates and controls the lifecycle of Room objects.
- The Room objects do not exist outside the context of the House.
- The composition relationships indicate that a House is composed of Room objects, meaning the rooms are created and owned by the house and do not exist independently.

### Just a Little Dependency: Dependency

Dependency represents a temporary relationship where one class uses another class, typically via method parameters or local variables. It's like borrowing a tool for a short while.

```java
class Printer {
  void print(String message) {
    System.out.println("Printing: " + message);
  }
}

class Document {
  String content;
  Document(String content) {
    this.content = content;
  }
  // Dependency: Document uses Printer to print its content.
  void printDocument(Printer printer) {
    printer.print(content);
  }
}

public class DependencyDemo {
  public static void main(String[] args) {
    Document doc = new Document("Hello, World!");
    Printer printer = new Printer();
    doc.printDocument(printer);
  }
}
```

**Explanation:**
- Document doesn't store a Printer; it simply uses one when needed.
- The relationship is transient and exists only during the method call.

#### Key Differences Between Association and Dependency:
- **Association** is a general relationship (friendship) with no ownership implied; objects simply interact or use each other.
- **Dependency** represents a temporary relationship where one class uses another class, typically via method parameters or local variables.

### Walking the Interface Tightrope: Realization (Interface Implementation)

Realization represents a relationship where a class implements an interface. It's like signing a contract to provide specific behaviors.

```java
interface Payment {
  void pay();
}

class CreditCardPayment implements Payment {
  @Override
  public void pay() {
    System.out.println("Paid using Credit Card.");
  }
}

class CashPayment implements Payment {
  @Override
  public void pay() {
    System.out.println("Paid using Cash.");
  }
}

public class RealizationDemo {
  public static void main(String[] args) {
    Payment payment1 = new CreditCardPayment();
    Payment payment2 = new CashPayment();
    payment1.pay();
    payment2.pay();
  }
}
```
Defines a contract with a read() method. The Book class implements this interface, ensuring that all books provide a reading behavior.

**Book and EBook:**
Book has attributes for title and an associated Author. EBook extends Book by adding a fileFormat attribute. The inheritance arrow (solid line with a closed arrowhead) shows that EBook is a specialized type of Book.

**Association (Book & Author):**
The arrow from Book to Author indicates that every book is written by an author. This is a simple association where both objects can exist independently.

**Composition (Library & Book):**
The filled diamond from Library to Book indicates composition. A library is composed of books, meaning the library strongly owns its books.

**Dependency (Reader & Book):**
The dashed arrow from Reader to Book denotes a dependency. A reader uses a book (for example, to read), but does not own it.

**Aggregation (ReadingClub & Reader):**
The open diamond from ReadingClub to Reader represents aggregation. A reading club groups readers together, but readers can exist independently of the club.

### Wrapping Up Our Class Relationships Journey

Today, we've explored the full spectrum of class relationships—from the familial bonds of inheritance to the friendly connections of association, the groupings of aggregation, the strong ties of composition, the temporary links of dependency, and the contractual nature of realization. Each relationship plays a vital role in building clean, organized, and scalable systems.

Understanding these relationships—and how to represent them in UML diagrams—will help you design software that's both robust and easy to maintain. I hope this journey has been as fun and enlightening for you as it has been for me! Keep exploring, keep coding, and most importantly, enjoy the process! 😄👍

Happy coding!

---

## Constructor and Its Types

**Topic Tags:**
- OOP
- System Design
- LLD

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

Constructors are special methods in object-oriented programming used to initialize objects of a class.

They play a pivotal role in ensuring that an object is in a valid state right after it is "created". Unlike regular methods, constructors have the same name as the class and do not have a return type. 🔧

### Key Features of Constructors:
1. **Automatic Invocation:** Constructors are called automatically when an object of the class is instantiated. 🔄
2. **No Return Type:** Unlike methods, constructors do not have a return type, not even void. 🚫
3. **Overloading Support:** You can have multiple constructors with different parameters in the same class, enabling flexibility in object initialization. 🔁

### Types of Constructors:

Constructors can be broadly categorized into the following types:

#### 1. Default Constructor (No-Argument Constructor)

**When No Constructor Is Defined (Implicit Default Constructor):**

If you do not explicitly create any constructor for a class, Java automatically provides a default constructor.

This constructor initializes instance variables to their default values based on the data type. 🔄

**Default Values:**
- `int` → 0
- `double` → 0.0
- `boolean` → false
- `Object` → null

**Example:**

```java
class Movie {
  private String title; // Default: null
  private int duration; // Default: 0

  public void displayDetails() {
    System.out.println("Title: " + title + ", Duration: " + duration + " mins");
  }
}

public class Main {
  public static void main(String[] args) {
    Movie movie = new Movie(); // Implicit default constructor is called
    movie.displayDetails(); // Displays default values
  }
}
```

**Output:**
```
Title: null, Duration: 0 mins
```

#### 2. Parameterized Constructor

A parameterized constructor takes arguments to initialize the object with specific values.

**Example:**

```java
class Movie {
  private String title;
  private int duration;

  // Parameterized constructor
  public Movie(String title, int duration) {
    this.title = title;
    this.duration = duration;
  }
  public void displayDetails() {
    System.out.println("Title: " + title + ", Duration: " + duration + " mins");
  }
}

public class Main {
  public static void main(String[] args) {
    Movie movie = new Movie("Inception", 148); // Parameterized constructor is called
    movie.displayDetails();
  }
}
```

**Output:**
```
Title: Inception, Duration: 148 mins
```

#### 3. Copy Constructor

A copy constructor initializes an object using another object of the same class.

**Example:**

```java
class Movie {
  private String title;
  private int duration;

  // Parameterized constructor
  public Movie(String title, int duration) {
    this.title = title;
    this.duration = duration;
  }

  // Copy constructor
  public Movie(Movie other) {
    this.title = other.title;
    this.duration = other.duration;
  }
  public void displayDetails() {
    System.out.println("Title: " + title + ", Duration: " + duration + " mins");
  }
}

public class Main {
  public static void main(String[] args) {
    Movie original = new Movie("Inception", 148);
    Movie copy = new Movie(original); // Copy constructor is called
    copy.displayDetails();
  }
}
```

**Output:**
```
Title: Inception, Duration: 148 mins
```

#### 4. Private Constructor

A private constructor is used to restrict object creation from outside the class. It is commonly used in Singleton Design Pattern.

**Example:**

```java
class Singleton {
  private static Singleton instance;
  // Private constructor
  private Singleton() {}
  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}

public class Main {
  public static void main(String[] args) {
    Singleton s1 = Singleton.getInstance();
    Singleton s2 = Singleton.getInstance();
    System.out.println(s1 == s2); // Output: true, as both references point to the same instance
  }
}
```

**Output:**
```
true
```

### 🔑 Key Points to Remember:

1. **A class can have multiple constructors through overloading**, but they must differ in parameter lists.
2. **Constructors can call other constructors in the same class** using `this()`.
3. **Constructors can call parent class constructors** using `super()` in Java.
4. **Always use constructors to ensure objects are in a consistent and valid state**.
5. **Utilize copy constructors carefully** to avoid shallow copying when deep copying is required.
6. **Leverage private constructors for Singleton patterns or utility classes**.

## 📝 Interview Questions

### 1. Can a constructor be final, static, or abstract? Why or why not?

**Answer:** No, constructors cannot be final, static, or abstract because:
- **final:** A constructor cannot be inherited, so final is irrelevant.
- **static:** Constructors belong to objects, not the class itself.
- **abstract:** A constructor must be concrete as it initializes an object.

### 2. What happens if you explicitly define a constructor with arguments but no default constructor?

**Answer:** The default constructor is not automatically provided. Attempting to create an object with no arguments will result in a compilation error.

**Example:**

```java
class Example {
  public Example(int a, int b) {
    this.a = a;
    this.b = b;
  }

  public static void main(String[] args) {
    Example example = new Example(); // This will cause compilation error
    System.out.println(example);
  }
}
```

**Output:**
```
Compilation Error
```

### 3. What happens if you create an object of a subclass? Which constructor is called first?

**Answer:** The parent class constructor is called first, followed by the subclass constructor. This ensures proper initialization.

### 4. What happens if a constructor is synchronized?

**Answer:** A synchronized constructor is allowed but makes no sense, as object-level synchronization is not applicable before the object is fully created.

### 5. Can a constructor be inherited?

**Answer:** No, constructors are not inherited, but a subclass can call the superclass constructor using `super()`.

### 6. Can a constructor have a return statement?

**Answer:** No, constructors cannot return a value, but they can have a return statement to exit early (without a value).

**Example:**

```java
class Example {
  private int value;
  // Constructor with a return statement
  public Example(int value) {
    if (value < 0) {
      System.out.println("Invalid value! Constructor exiting early.");
      return; // Exits the constructor early
    }
    this.value = value; // Initializes the value if valid
  }
  public void display() {
    System.out.println("Value: " + value);
  }
}

public class Main {
  public static void main(String[] args) {
    Example obj1 = new Example(10); // Valid value
    obj1.display();
    Example obj2 = new Example(-5); // Invalid value, constructor exits early
    obj2.display();
  }
}
```

**Output:**
```
Value: 10
Invalid value! Constructor exiting early.
Value: 0
```

## 🎯 Conclusion

Constructors are essential for initializing objects in object-oriented programming. Understanding their types and use cases enables developers to write clean, efficient, and maintainable code. 🧑‍💻

With proper usage of constructors, you can ensure object consistency and design robust systems. 

---

## This Keyword in OOPS

**Topic Tags:**
- OOP
- System Design
- LLD

 Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

### Introduction

The "this" keyword is a fundamental concept in Object-Oriented Programming (OOP) that provides a reference to the current object.

Its behavior and usage are crucial in Java programming for building robust and maintainable applications. 

### This Keyword in Java:

The "this" keyword in Java is a built-in reference to the current object within a class's methods or constructors. It allows access to the object's properties, methods, and other members. 

### Why Is the "this" Keyword Important?

- **Disambiguation:** Resolves naming conflicts between instance variables and parameters or local variables. 
- **Chaining:** Enables method chaining or constructor chaining, improving code readability and structure. 
- **Passing References:** Passes the current object as a parameter to other methods or functions. 
- **Dynamic Binding:** Refers to the current object dynamically during runtime in polymorphic scenarios. 

### How "this" Works in Java:

#### 1. Referring to the Instance Object

The "this" keyword is crucial when differentiating between instance variables and method or constructor parameters that share the same name. In such cases, it explicitly refers to the instance variable of the current object, avoiding ambiguity. This is particularly helpful in constructors and setter methods where parameters often shadow instance variables. 

**Example:**

```java
class Person {
  private String name;

  // Constructor
  Person(String name) {
    this.name = name; // Resolves conflict between instance variable and parameter
  }
  void display() {
    System.out.println("Name: " + this.name);
  }
}

public class Main {
  public static void main(String[] args) {
    Person p = new Person("Alice");
    p.display();
  }
}
```

#### 2. Constructor Chaining: 

Using the "this" keyword for constructor chaining allows one constructor to invoke another constructor within the same class. This reduces code duplication and centralizes initialization logic, making the code easier to maintain. It also ensures that all constructors eventually delegate to a common initialization point

**Example:**

```java
class Person {
  private String name;
  private int age;

  // Constructor 1
  Person(String name) {
    this(name, 0); // Calls Constructor 2
  }

  // Constructor 2
  Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  void display() {
    System.out.println("Name: " + name + ", Age: " + age);
  }
}

public class Main {
  public static void main(String[] args) {
    Person p = new Person("Alice");
    p.display();
  }
}
```

#### 3. Returning the Current Object:
The "this" keyword can be used to return the current instance of a class. This approach is commonly used in implementing fluent interfaces, where multiple methods are called on the same object in a single statement. This pattern enhances code readability and provides a seamless way to chain method calls

**Example:**

```java
class Person {
  private String name;
  Person setName(String name) {
    this.name = name;
    return this; // Enables method chaining
  }
  void display() {
    System.out.println("Name: " + this.name);
  }
}

public class Main {
  public static void main(String[] args) {
    Person p = new Person();
    p.setName("Bob").display();
  }
}
```

#### 4. Passing the Current Object
"this" can be used to pass the current object as a parameter to another method.

**Example:**

```java
class Person {
  void greet(Person person) {
    System.out.println("Hello, " + person);
  }
  void introduce() {
    greet(this); // Passes the current object
  }
  @Override
  public String toString() {
    return "I am a Person instance.";
  }
}

public class Main {
  public static void main(String[] args) {
    Person p = new Person();
    p.introduce();
  }
}
```

### Advantages and Disadvantages of the "this" Keyword

#### Advantages

1. **Enhanced Readability and Maintainability**
Using "this" improves code clarity by explicitly referencing the current object, making it easier to understand and maintain.

2. **Enables Constructor and Method Chaining**
The ability to chain constructors and methods through "this" reduces redundancy and centralizes initialization or method logic, leading to more streamlined code.

3. **Object Reference Passing**
Facilitates passing the current object as a parameter, enabling seamless interaction between methods or classes.

#### Disadvantages

1. **Limited to Instance Context**
"this" cannot be used in static methods or contexts, which can be restrictive in certain situations.

**Example:**

```java
class Example {
  private String message = "Hello, World!";
  // Static method
  public static void displayMessage() {
    // Attempting to use 'this' in a static context will cause a compilation error
    System.out.println(this.message); // ERROR: Cannot use 'this' in a static context
  }
  public void displayInstanceMessage() {
    System.out.println(this.message); // Valid: 'this' refers to the current instance
  }
}

public class Main {
  public static void main(String[] args) {
    // Calling static method
    Example.displayMessage(); // This would cause a compilation error
    // Creating an instance to call an instance method
    Example example = new Example();
    example.displayInstanceMessage(); // Works fine
  }
}
```

Explanation:
1. this refers to the current instance of the class.

2. Static methods do not belong to any specific instance; they are associated with the class itself.

3. Since there is no instance in a static context, using this leads to a compilation error.

‍

2. Overuse Can Reduce Clarity:

Excessive or unnecessary use of "this" can make code verbose and harder to read, especially when there is no risk of variable shadowing.

‍

### 🎯 Conclusion

The 'this' keyword is very useful in OOP because it allows us to access the properties and methods of the current object without having to specify its name. This makes the code more flexible and adaptable to different situations. 🔄 Instead of writing custom methods for every object, this approach allows us to reuse the existing code. ♻️ It also enables developers to write clean, modular, and efficient code. 💻🧹

Its role in resolving conflicts, supporting chaining, and passing references is indispensable in building robust applications. 🏗️ By understanding its usage and nuances, Java developers can leverage "this" to enhance their code's clarity and functionality. ✨

---

## Polymorphism and Its Types

**Topic Tags:**
- OOP
- System Design
- LLD

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

### Introduction

Polymorphism is one of the core principles of Object-Oriented Programming (OOP), enabling objects to take on multiple forms. It allows the same operation to behave differently on different classes, enhancing code flexibility and reusability.

A real-life example of polymorphism is a person who at the same time can have different characteristics. A man at the same time is a father, a husband, and an employee. So the same person exhibits different behavior in different situations. This is called polymorphism. 👨‍👩‍👧‍👦💼

In Java, polymorphism can be broadly classified into two main types:

- **Compile-time (or Static) Polymorphism**
- **Runtime (or Dynamic) Polymorphism**

### Types of Polymorphism

#### 1. Compile-time (Static) Polymorphism

Compile-time or Static polymorphism occurs when the method to be executed is determined at compile time. It is achieved using method overloading or operator overloading. 🛠️

**Method Overloading:**
When there are multiple functions with the same name but different parameters, then the functions are said to be overloaded, hence this is known as Function or Method Overloading. Functions can be overloaded by changing the number of arguments or/and changing the type of arguments. 🔢🎚️

**Example 1: Changing the number of arguments**

```java
class Vehicle {
  // Method to start a vehicle with basic information
  void start(String vehicleType) {
    System.out.println("Starting a " + vehicleType);
  }

  // Overloaded method to start a vehicle with extra information
  void start(String vehicleType, int speed) {
    System.out.println(
        "Starting a " + vehicleType + " with speed: " + speed + " km/h");
  }
}

public class Main {
  public static void main(String[] args) {
    Vehicle vehicle = new Vehicle();

    // Calls method with one argument
    vehicle.start("Car");

    // Calls overloaded method with two arguments
    vehicle.start("Bike", 60);
  }
}
```

#### 2. Changing the type of arguments :

```java
Java
class Vehicle {
  // Method to start a vehicle with a string parameter
  void start(String vehicleType) {
    System.out.println("Starting a " + vehicleType);
  }

  // Overloaded method to start a vehicle with an integer parameter
  void start(int vehicleId) {
    System.out.println("Starting a vehicle with ID: " + vehicleId);
  }
}


public class Main {
  public static void main(String[] args) {
    Vehicle vehicle = new Vehicle();

    // Calls method with a string argument
    vehicle.start("Truck");

    // Calls overloaded method with an integer argument
    vehicle.start(101);
  }
}
‍Article image

‍

Key Features:

• Method resolution happens at compile time.

• Provides better readability and cleaner code by allowing methods with the same name to perform similar actions.

‍

2. Runtime (Dynamic) Polymorphism : 
Runtime polymorphism occurs when the method to be executed is determined during runtime. It is achieved through method overriding and is closely tied to inheritance.

‍

Method Overriding : 
Method overriding allows a subclass to provide a specific implementation for a method already defined in its parent class. The overridden method in the subclass has the same name, return type, and parameters as the method in the parent class.

‍

Example : 

‍

Java
// Parent class
class Vehicle {
  void start() {
    System.out.println("Starting a generic vehicle");
  }
}

// Subclasses overriding the start method
class Car extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a car");
  }
}

class Bike extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a bike");
  }
}

class Truck extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a truck");
  }
}

public class Main {
  public static void main(String[] args) {
    Vehicle myVehicle;
    // Assign a Car object to the Vehicle reference
    myVehicle = new Car();
    myVehicle.start(); // Output: Starting a car

    // Assign a Bike object to the Vehicle reference
    myVehicle = new Bike();
    myVehicle.start(); // Output: Starting a bike

    // Assign a Truck object to the Vehicle reference
    myVehicle = new Truck();
    myVehicle.start(); // Output: Starting a truck
  }
}
‍Article image

‍

Key Features:

• Method resolution happens at runtime based on the actual object type.

• Supports dynamic method dispatch, enabling the Java Virtual Machine (JVM) to determine the appropriate method implementation.

‍

Advantages of Polymorphism:
1. Code Reusability:
○ Encourages writing generic and reusable code by allowing a single interface to handle multiple types.

‍

Example : 

Java
// Interface
interface Vehicle {
  void start(); // Abstract method
}

// Implementing classes
class Car implements Vehicle {
  @Override
  public void start() {
    System.out.println("Starting the car");
  }
}

class Bike implements Vehicle {
  @Override
  public void start() {
    System.out.println("Starting the bike");
  }
}

class Truck implements Vehicle {
  @Override
  public void start() {
    System.out.println("Starting the truck");
  }
}

public class Main {
  public static void main(String[] args) {
    Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};
    for (Vehicle vehicle : vehicles) {
      vehicle.start(); // Polymorphic behavior
    }
  }
}
‍

The Vehicle interface allows you to reuse a single loop (for (Vehicle vehicle : vehicles)) to handle different implementations.

2. Flexibility:
○ Provides flexibility in program design by enabling dynamic method behavior.

‍

Example : 

Java
public class Main {
  public static void main(String[] args) {
    Vehicle vehicle;
    // Flexible: Dynamically assign different types of vehicles
    vehicle = new Car();
    vehicle.start(); // Output: Starting the car
    vehicle = new Bike();
    vehicle.start(); // Output: Starting the bike
    vehicle = new Truck();
    vehicle.start(); // Output: Starting the truck
  }
}
Using the Vehicle interface, we dynamically switch between different implementations (Car, Bike, Truck) at runtime.

‍

3. Extensibility:
Allows easy extension of code by adding new classes / methods or overriding existing ones.

‍

Example : 

Java
// Adding a new type of Vehicle
class Bus implements Vehicle {
  @Override
  public void start() {
    System.out.println("Starting the bus");
  }
}

public class Main {
  public static void main(String[] args) {
    // Extensible: Add new vehicle types without changing existing code
    Vehicle[] vehicles = {new Car(), new Bike(), new Truck(), new Bus()};
    for (Vehicle vehicle : vehicles) {
      vehicle.start(); // Polymorphic behavior handles the new type seamlessly
    }
  }
}
‍

Adding new vehicle types (Bus) is seamless and requires no changes to the existing code because all new classes just implement the Vehicle interface.

‍

Disadvantages of Polymorphism:
1. Complex Debugging:
Runtime polymorphism can make debugging difficult due to dynamic method resolution.

‍

Example:
Java
import java.util.ArrayList;
import java.util.List;

// Base class
class Vehicle {
  void start() {
    System.out.println("Starting a generic vehicle");
  }
}

// Subclasses overriding the start method
class Car extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a car");
  }
}

class Bike extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a bike");
  }
}

class Truck extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a truck");
  }
}

public class Main {
  public static void main(String[] args) {
    // List containing various types of vehicles
    List<Vehicle> vehicleList = new ArrayList<>();
    vehicleList.add(new Car());
    vehicleList.add(new Bike());
    vehicleList.add(new Truck());
    vehicleList.add(new Vehicle());
    // Debugging challenge: What type of vehicle is being started?
    for (Vehicle vehicle : vehicleList) {
      vehicle.start(); // Runtime determines which start() method is called
    }
  }
}
When iterating through a list of Vehicle objects, it’s unclear during debugging which specific subclass (Car, Bike, Truck, or Vehicle) is being called without stepping through the code or inspecting runtime variables.

‍

If the list comes from an external API, file, or database, the actual type of each object isn’t clear from the source code, making it difficult to debug.

‍

2. Performance Overhead:
Dynamic method dispatch introduces slight overhead as the JVM resolves the method during runtime.

‍

Example : 

Java
class Vehicle {
  void start() {
    System.out.println("Starting a generic vehicle");
  }
}

class Car extends Vehicle {
  @Override
  void start() {
    System.out.println("Starting a car");
  }
}

public class Main {
  public static void main(String[] args) {
    Vehicle myVehicle;
    long startTime = System.nanoTime();
    // Dynamic method dispatch
    myVehicle = new Car();
    myVehicle.start(); // JVM resolves method implementation dynamically
    long endTime = System.nanoTime();
    System.out.println("Time taken for method dispatch: "
        + (endTime - startTime) + " nanoseconds");
  }
}
‍

🎯Conclusion :
Polymorphism is a powerful feature in OOPs that promotes flexibility, modularity, and reusability. Understanding its types—compile-time and runtime—is essential for mastering OOP principles and designing robust applications. 💡

‍

By leveraging polymorphism effectively, developers can write cleaner, more maintainable code, ensuring their applications are scalable and adaptable to change. 🔄💻

<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Inheritance and It's types

Topic Tags:
OOP
System Design
LLD
🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Inheritance is a cornerstone of Object-Oriented Programming (OOP) that facilitates code reuse and establishes a hierarchical relationship between classes. By inheriting properties and methods from a parent class, a subclass can extend or override functionalities, enabling efficient and scalable application development. ⚙️🔄

‍

This promotes code reuse, reduces redundancy, and supports polymorphism, making applications easier to develop and maintain. 💻✨

‍

Types of Inheritance:
1. Single Inheritance:
In single inheritance, a subclass inherits from a single parent class. This is the simplest form of inheritance and is widely used in Java.

‍

Example : 

Java
class Animal {
  void eat() {
    System.out.println("This animal eats food.");
  }
}

class Dog extends Animal {
  void bark() {
    System.out.println("The dog barks.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Inherited method
    dog.bark();
  }
}
‍

Article image

🔑Key Features:

• A single subclass derives from one superclass.

• Promotes simplicity and clarity in the inheritance hierarchy.

‍

2. Multilevel Inheritance : 
In multilevel inheritance, a class inherits from a parent class, and another class further inherits from this child class, forming a chain.

‍

Example : 

Java
class Animal {
  void eat() {
    System.out.println("This animal eats food.");
  }
}

class Mammal extends Animal {
  void walk() {
    System.out.println("This mammal walks.");
  }
}

class Dog extends Mammal {
  void bark() {
    System.out.println("The dog barks.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Inherited from Animal
    dog.walk(); // Inherited from Mammal
    dog.bark();
  }
}
‍

Article image

‍

🔑 Key Features:

• Establishes a chain of inheritance.

• Enables deeper specialization of classes.

‍

3. Hierarchical Inheritance :
In hierarchical inheritance, multiple subclasses inherit from a single parent class. This allows different classes to share common properties and behaviors defined in the superclass.

‍

Example : 

Java
class Animal {
  void eat() {
    System.out.println("This animal eats food.");
  }
}

class Dog extends Animal {
  void bark() {
    System.out.println("The dog barks.");
  }
}

class Cat extends Animal {
  void meow() {
    System.out.println("The cat meows.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat();
    dog.bark();
    Cat cat = new Cat();
    cat.eat();
    cat.meow();
  }
}
‍

Article image

‍

🔑 Key Features:

• Multiple subclasses share common properties from a single superclass.

• Promotes code reuse and modularity.

‍

4. Multiple Inheritance : 
Java does not support Multiple inheritance directly due to the diamond problem, but it can be achieved using interfaces. In Multiple inheritance, A single class can inherit properties from multiple interfaces.

‍
💎 What is the Diamond Problem?
The diamond problem arises in languages that allow multiple inheritance with classes. Imagine a scenario where a class inherits from two parent classes that both have a method with the same name. If the child class does not override the method, it creates ambiguity as to which implementation the child class should inherit. This leads to confusion and potential conflicts in the program.

‍

Problem Example : 

Java
class Animal {
  public void sound() {
    System.out.println("Animal makes a sound");
  }
}

class Dog extends Animal {
  @Override
  public void sound() {
    System.out.println("Dog barks");
  }
}

class Cat extends Animal {
  @Override
  public void sound() {
    System.out.println("Cat meows");
  }
}

// Not supported in Java
public class HybridAnimal extends Dog, Cat {
  public static void main(String[] args) {
    HybridAnimal hybrid = new HybridAnimal();
    hybrid.sound(); // Creates ambiguity: Should it call Dog's sound() or Cat's
                    // sound()?
  }
}
‍

Article image

‍

🧠 How Java Resolves This?

Java avoids this problem by not allowing multiple inheritance with classes. Instead, Java provides interfaces as a way to achieve multiple inheritance.

‍

When a class implements multiple interfaces, it must provide implementations for the methods defined in the interfaces. This eliminates ambiguity since the child class explicitly defines the behavior of inherited methods.

‍

Solution Example : 

Java
interface Dog {
  void sound();
}

interface Cat {
  void sound();
}

public class HybridAnimal implements Dog, Cat {
  @Override
  public void sound() {
    // You can define custom logic to decide which sound to make
    Dog.super.sound(); // Calls Dog's sound()
    // Cat.super.sound();  // Or you can choose to call Cat's sound()
  }

  public static void main(String[] args) {
    HybridAnimal hybrid = new HybridAnimal();
    hybrid.sound(); // Calls Dog's sound
  }
}
‍

Article image

‍

🔑Key Features:

• Achieved using interfaces to avoid ambiguity caused by multiple inheritance.

• Combines the benefits of various inheritance types.

‍

5. Hybrid Inheritance : 
Hybrid inheritance is a combination of more than one type of inheritance. It can involve both single inheritance and multiple inheritance.

‍

In Java, hybrid inheritance is achieved by combining classes and interfaces. Since Java doesn't support multiple inheritance with classes (to avoid the diamond problem), this type of inheritance can only be implemented using interfaces alongside class inheritance.

‍‍

Example :
Java
// Single inheritance
class Animal {
  void eat() {
    System.out.println("The animal eats food.");
  }
}

// Interface for multiple inheritance
interface Mammal {
  void walk();
}

// Interface for multiple inheritance
interface Pet {
  void play();
}

// Hybrid inheritance using a combination of class and interfaces
class Dog extends Animal implements Mammal, Pet {
  @Override
  void eat() {
    System.out.println("The dog eats food.");
  }
  @Override
  public void walk() {
    System.out.println("The dog walks.");
  }
  @Override
  public void play() {
    System.out.println("The dog plays fetch.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat();
    dog.walk();
    dog.play();
  }
}
‍

Article image

‍

👍🏼Advantages of Inheritance : 
1. Code Reusability:
Enables reuse of existing code, reducing redundancy and effort.

‍

Example :
Java
class Animal {
  public void eat() {
    System.out.println("Animal is eating");
  }
}

class Dog extends Animal {
  // Inherits eat() method from Animal
}

class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Reuses the eat method from Animal
  }
}
‍

2. Ease of Maintenance:
Centralizes common functionality, making updates and bug fixes more manageable.

‍

Example :
Java
class Animal {
  public void eat() {
    System.out.println("Animal is eating");
  }
}

class Dog extends Animal {
  // Inherits eat() method from Animal
}

class Cat extends Animal {
  // Inherits eat() method from Animal
}

public class Main {
  public static void main(String[] args) {
    // If we need to fix a bug in eat()
    // or improve it, we only need to do it in Animal
    Animal animal = new Dog();
    animal.eat(); // Animal is eating
    animal = new Cat();
    animal.eat(); // Animal is eating
  }
}
‍

If we need to fix a bug in start() method or refactor it, we only need to do it in the Vehicle Class and it will be implemented for all the sub classes of the Vehicle Class.

‍

3. Extensibility:
Allows developers to extend functionality without altering existing code.

‍

Example : 

Java
class Animal {
  public void sleep() {
    System.out.println("Animal is sleeping");
  }
}

class Dog extends Animal {
  @Override
  public void sleep() {
    System.out.println("Dog is sleeping in its kennel");
  }
}

class Cat extends Animal {
  @Override
  public void sleep() {
    System.out.println("Cat sleeps in a tree");
  }
}

class Main {
  public static void main(String[] args) {
    Animal myAnimal = new Animal();
    Animal myDog = new Dog();
    Animal myCat = new Cat();
    myAnimal.sleep(); // Animal is sleeping
    myDog.sleep(); // Dog is sleeping in its kennel
    myCat.sleep(); // Cat sleeps in a tree
  }
}
‍

In this example, Dog and Cat both inherit the sleep() method from Animal. You can extend the functionality of the sleep() method in each subclass by overriding it to add specific behavior for each subclass (Dog sleeping in a kennel, Cat sleeping in a tree). 

‍

This is an example of extensibility—you don’t need to modify the Animal class itself to extend its behavior for each subclass. You only need to add new behavior or change behavior in the subclasses as needed.

‍

Inheritance makes it easier to add new types of animals with different sleeping behaviors by extending Animal without changing the original Animal class.

‍

4. Supports Polymorphism:
Facilitates runtime polymorphism, enabling dynamic behaviour.

‍

Example : 

Java
class Animal {
  public void sound() {
    System.out.println("Animal makes a sound");
  }
}

class Dog extends Animal {
  @Override
  public void sound() {
    System.out.println("Dog barks");
  }
}

class Cat extends Animal {
  @Override
  public void sound() {
    System.out.println("Cat meows");
  }
}

class Main {
  public static void main(String[] args) {
    Animal myAnimal = new Dog();
    myAnimal.sound(); // Dog barks

    myAnimal = new Cat();
    myAnimal.sound(); // Cat meows
  }
}
‍

👎🏼Disadvantages of Inheritance : 
1. Increased Coupling:
Creates a tightly coupled relationship between classes, making changes in the superclass impact all subclasses.

‍

Example : 

Java
class Animal {
  public void eat() {
    System.out.println("Animal eats");
  }
}

class Dog extends Animal {
  // Inherits eat() method from Animal
}

class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Reuses the eat method from Animal
  }
}
‍

If we change Animal's eat method, it could break Dog's functionality leading to an Exception      which shows how a change in one particular method of the Parent class can break the properties of it's subclasses.

‍

2. Complexity:
Overuse of inheritance can lead to overly complex and hard-to-maintain hierarchies.

‍

Example :
Java
class Animal {}
class Mammal extends Animal {}
class Dog extends Mammal {}
class Bulldog extends Dog {}

class Main {
  public static void main(String[] args) {
    Bulldog bulldog = new Bulldog();
  }
}
‍

Understanding this deep nested level of inheritance structure may be difficult in larger systems to maintain and may require refactoring to make the structure more maintainable and scalable.

‍

3. Reduced Flexibility:
A subclass is heavily dependent on the implementation of its superclass, which may limit customization.

‍

Example :
Java
class Animal {
  public void sleep() {
    System.out.println("Animal is sleeping");
  }
}

class Dog extends Animal {
  // The dog inherits the sleep method from Animal
}

class Cat extends Animal {
  // Similarly, the Cat class inherits the sleep method from Animal
}

class Main {
  public static void main(String[] args) {
    Animal dog = new Dog();
    Animal cat = new Cat();

    dog.sleep(); // Animal is sleeping (not customized for Dog)
    cat.sleep(); // Animal is sleeping (not customized for Cat)
  }
}
‍

In this example, the Dog and Cat classes are both inheriting the sleep() method from Animal. However, suppose you wanted to make Dog sleep in a specific way, for instance, "Dog is sleeping in its kennel" 🐶🏠 and Cat sleep in another way, "Cat sleeps in a tree" 🐱🌳.

‍

You would be forced to modify the sleep() method in the parent class, Animal, or override it in each subclass. This reduces flexibility because you can't change or extend sleep() behavior independently for each subclass without affecting the others. 🔄

‍

🎯Conclusion :
Inheritance is a fundamental feature of Java that enhances code reuse, modularity, and scalability. By understanding its types—single, multilevel, hierarchical, and hybrid—developers can design robust and maintainable applications. 💻

‍

Proper use of inheritance fosters efficient development while avoiding common pitfalls such as over-coupling and unnecessary complexity. 🛠️

<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Encapsulation

Topic Tags:
OOP
System Design
LLD
🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Encapsulation is one of the fundamental principles of Object-Oriented Programming (OOP). It involves bundling data (variables) and methods (functions) that operate on the data into a single unit called a class.

‍

Encapsulation also restricts direct access to certain components, ensuring controlled interaction through methods. This prevents unauthorized or accidental interference with the object’s data and ensures better control over the data flow in a program. 🔒

‍

In Java, encapsulation is typically achieved by:

• Declaring class variables as private. 🔐

• Providing public getter and setter methods to access and modify these variables. 🏷️

‍

🔑 Key Features of Encapsulation:
1. Data Hiding:

○ Prevents direct access to sensitive data, ensuring that changes can only be made through controlled methods. 🛡️

‍

2. Modularity:

○ Promotes modular design by separating data and behavior, making the code easier to manage and debug. 🧩

‍

3. Security:

○ Protects the integrity of the data by restricting unwanted modifications. 🔒

‍

4. Flexibility:

○ Allows developers to change the internal implementation of a class without affecting external code. 🔄

‍

Example :
Java
class BankAccount {
  // Private variables (data hiding)
  private String accountNumber;
  private double balance;

  // Constructor
  public BankAccount(String accountNumber, double initialBalance) {
    this.accountNumber = accountNumber;
    this.balance = initialBalance;
  }

  // Public getter method
  public String getAccountNumber() {
    return accountNumber;
  }

  // Public getter method
  public double getBalance() {
    return balance;
  }

  // Public setter method for deposit
  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Deposited: " + amount);
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  // Public setter method for withdrawal
  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Withdrawn: " + amount);
    } else {
      System.out.println("Invalid withdrawal amount.");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount("12345", 1000.00);
    System.out.println("Account Number: " + account.getAccountNumber());
    System.out.println("Initial Balance: " + account.getBalance());
    account.deposit(500.00);
    System.out.println("Updated Balance: " + account.getBalance());
    account.withdraw(200.00);
    System.out.println("Final Balance: " + account.getBalance());
  }
}
‍

📃Explanation:
1. Private Variables:

○ The variables accountNumber and balance are private, restricting direct access from outside the class.

‍

2. Public Methods:

○ Getter methods provide controlled access to the variables.

○ Setter methods ensure valid updates to the variables.

‍

3. Data Integrity:

○ The class ensures that only valid operations are performed on its data.

‍

👍🏼Advantages of Encapsulation : 
1. Improved Data Security:

○ Prevents unauthorized access and modifications to the data.

‍

Example : 

Java
class BankAccount {
  private String accountNumber; // Data is hidden
  private double balance; // Data is hidden
  public BankAccount(String accountNumber, double initialBalance) {
    this.accountNumber = accountNumber;
    this.balance = initialBalance;
  }

  // Public getter for balance (read-only access)
  public double getBalance() {
    return balance;
  }

  // Public method for deposit (controlled access)
  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Deposited: " + amount);
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  // Public method for withdrawal (controlled access)
  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Withdrawn: " + amount);
    } else {
      System.out.println("Invalid withdrawal amount.");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount("12345", 1000.00);

    // Accessing balance only through public methods
    account.deposit(500.00);
    account.withdraw(300.00);

    // Unauthorized access prevented: No direct modification
    System.out.println("Account Balance: " + account.getBalance());
  }
}
‍

In this example, direct access to balance and accountNumber is not allowed, ensuring that sensitive information is not exposed or modified arbitrarily. This enhances data security and prevents accidental errors.

‍

Ease of Maintenance:
○ Encapsulated code is easier to modify and debug without affecting external components.

‍

Example : 

Java
class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  // Centralized logic for deposit and withdrawal
  public void updateBalance(double amount, boolean isDeposit) {
    if (isDeposit) {
      balance += amount;
      System.out.println("Deposited: " + amount);
    } else if (amount <= balance) {
      balance -= amount;
      System.out.println("Withdrawn: " + amount);
    } else {
      System.out.println("Invalid transaction.");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount(1000.00);
    // Maintenance: Any bug in updateBalance can be fixed in one place
    account.updateBalance(500.00, true); // Deposit
    account.updateBalance(300.00, false); // Withdraw
    System.out.println("Final Balance: " + account.getBalance());
  }
}
In the above example, the updateBalance method is the single point of truth for both deposits and withdrawals. Any changes, such as adding transaction limits or fees, can be made in this method without affecting other parts of the code.

‍

Increased Flexibility:
○ Internal implementation details can be changed without impacting external code.

‍

Example : 

Java
class BankAccount {
  private double balance;
  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }
  public double getBalance() {
    return balance;
  }

  // Internal implementation can be updated (e.g., adding transaction fee)
  public void withdraw(double amount) {
    double transactionFee = 2.00; // New feature
    if (amount > 0 && (amount + transactionFee) <= balance) {
      balance -= (amount + transactionFee);
      System.out.println("Withdrawn: " + amount + ", Fee: " + transactionFee);
    } else {
      System.out.println("Invalid withdrawal amount.");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount(1000.00);
    // External code remains unchanged despite internal changes
    account.withdraw(200.00);
    System.out.println("Remaining Balance: " + account.getBalance());
  }
}
‍

The internal implementation of the withdraw method was updated to include a transaction fee, but external code (e.g., the Main class) didn’t need any modifications. This ensures flexibility and backward compatibility.

‍

4. Enhanced Readability:

○ Clearly defined interfaces improve code readability and usability.

‍

Example : 

Java
class BankAccount {
  private String accountNumber;
  private double balance;

  public BankAccount(String accountNumber, double initialBalance) {
    this.accountNumber = accountNumber;
    this.balance = initialBalance;
  }

  // Clear and readable methods
  public String getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Deposited: " + amount);
    }
  }

  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Withdrawn: " + amount);
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount("12345", 1000.00);

    // Clearly defined interfaces for readability
    System.out.println("Account Number: " + account.getAccountNumber());
    System.out.println("Initial Balance: " + account.getBalance());

    account.deposit(500.00);
    account.withdraw(200.00);
  }
}
‍

The clear interface (getAccountNumber, deposit, and withdraw) makes it easy for developers to understand how to use the BankAccount class, even if they are unfamiliar with its internal workings.

‍

👎🏼Disadvantages of Encapsulation : 
1. Slight Overhead:

○ Encapsulation introduces additional boilerplate code (getters and setters), which can increase verbosity.

‍

Example :
Java
class BankAccount {
  private String accountNumber;
  private double balance;
  // Boilerplate getters and setters
  public String getAccountNumber() {
    return accountNumber;
  }
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
  public double getBalance() {
    return balance;
  }
  public void setBalance(double balance) {
    this.balance = balance;
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    // Verbose code for simple tasks
    account.setAccountNumber("12345");
    account.setBalance(1000.00);
    System.out.println("Account Number: " + account.getAccountNumber());
    System.out.println("Balance: " + account.getBalance());
  }
}
‍

Although encapsulation provides benefits, it can lead to more verbose code, especially when getters and setters are used extensively without adding significant value.

‍

2. Complexity:

○ Beginners may find it challenging to understand and implement encapsulation effectively.

‍

Example : 

Java
class BankAccount {
  private double balance;
  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }
  public double getBalance() {
    return balance;
  }
  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
    }
  }
  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount(1000.00);
    // Beginners may find concepts like access modifiers and getters challenging
    account.deposit(500.00);
    account.withdraw(200.00);
    System.out.println("Final Balance: " + account.getBalance());
  }
}
‍

For beginners, understanding why direct access to fields is restricted and why getters/setters are used can be confusing. This might make it harder for them to implement encapsulation correctly.

‍

🎯Conclusion : 
Encapsulation is a vital aspect of OOP that fosters secure, modular, and maintainable code. By controlling data access through encapsulation, developers can ensure data integrity and create flexible, robust applications. Understanding and applying encapsulation effectively is essential for building scalable Java programs.

<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Abstraction

Topic Tags:
OOP
System Design
LLD
🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Abstraction is a core concept of Object-Oriented Programming (OOP) that focuses on exposing only the essential details of an object while hiding the implementation details. It enables developers to interact with objects at a higher level, focusing on what an object does rather than how it does it. 🧠🔍

‍

For example, when you use a car, you interact with its accelerator, brake, and steering wheel without needing to know how the engine works internally. This is an example of abstraction. 🚗⚙️

In Java, abstraction is achieved using abstract classes and interfaces. 🏗️💡

‍

Problem Without Abstraction
Imagine we want to create multiple animal types (Dog, Cat, Bird, etc.) where each animal has unique behaviors, such as making sounds. Without abstraction, we might end up writing repetitive and tightly coupled code. 🐕🐈🐦

‍

For example : 

Java
class Dog {
  void makeSound() {
    System.out.println("Bark");
  }
  void sleep() {
    System.out.println("Sleeping...");
  }
}

class Cat {
  void makeSound() {
    System.out.println("Meow");
  }
  void sleep() {
    System.out.println("Sleeping...");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.makeSound(); // Outputs: Bark
    dog.sleep(); // Outputs: Sleeping...
    Cat cat = new Cat();
    cat.makeSound(); // Outputs: Meow
    cat.sleep(); // Outputs: Sleeping...
  }
}
‍

The problems in the Above Code which doesn't uses the Concept of Abstraction are as follows :
1. Repetitive Code: Both Dog and Cat have the sleep() method, resulting in duplicate logic.

‍

No Common Structure: If a new animal is added, the same methods need to be redefined, leading to code redundancy.
‍

3. Lack of Flexibility: You can't refer to all animals in a common way (e.g., treating a Dog and Cat as Animal).

‍

4. Tightly Coupled Code: You must directly interact with individual classes (Dog, Cat), making the code less reusable and harder to maintain.

‍

Solution Using Abstraction : 
Abstraction allows us to focus on defining the what of an object (its behavior) while hiding the how of the object (its implementation).

To Solve the above Problems, we can define a common structure for all animals, specifying the essential behaviors they must have while leaving the specific implementations to the subclasses.

‍

This approach not only reduces code redundancy by centralizing shared logic but also enhances the flexibility and scalability of the system. Instead of creating tightly coupled classes for each animal type, abstraction allows us to treat all animals in a unified way, making the code more maintainable and easier to extend.

‍

Without abstraction, the code would become repetitive, harder to maintain, and prone to errors as new animal types are added.

‍

Abstraction helps to:
• Create a scalable design where adding new animals only involves defining a new subclass or implementing a new interface.

• Ensure consistency across all animal types by enforcing common methods like makeSound() and sleep().

• Facilitate polymorphism, enabling us to write code that can interact with any animal in a generic way, regardless of its specific type.

‍

Ways to Achieve Abstraction : 
1. Abstract Class 📦
An abstract class acts as a blueprint for other classes, providing a foundation for shared behavior while allowing subclasses to define specific implementations.

‍

It can include both abstract methods (declared but not implemented) and concrete methods (implemented with logic). By combining these, abstract classes strike a balance between enforcing a common structure and enabling flexibility. Importantly, abstract classes cannot be instantiated directly, meaning they are designed solely to be extended by other classes.

‍

Java
// Abstract Class Animal
abstract class Animal {
  // Abstract method for unique behaviors
  abstract void makeSound();
  // Concrete method for shared behaviors
  void sleep() {
    System.out.println("Sleeping...");
  }
}

// Specific implementation for Dog
class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
}

// Specific implementation for Cat
class Cat extends Animal {
  @Override
  void makeSound() {
    System.out.println("Meow");
  }
}

public class Main {
  public static void main(String[] args) {
    Animal myDog = new Dog(); // Treating Dog as an Animal
    myDog.makeSound(); // Outputs: Bark
    myDog.sleep(); // Outputs: Sleeping...
    Animal myCat = new Cat(); // Treating Cat as an Animal
    myCat.makeSound(); // Outputs: Meow
    myCat.sleep(); // Outputs: Sleeping...
  }
}
👍🏼 Advantages of Abstract classes : 
Improved Code Maintainability:
By focusing on essential details, abstraction makes code easier to maintain and understand.

‍

Example : 

Java
abstract class Animal {
  abstract void makeSound();
  void eat() {
    System.out.println("Animal is eating...");
  }
}

class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
}

public class Main {
  public static void main(String[] args) {
    Animal myDog = new Dog();
    myDog.eat(); // Outputs: Animal is eating...
    myDog.makeSound(); // Outputs: Bark
  }
}
‍

Here, the eat() method is defined in the abstract class Animal, and any update to this method will reflect across all subclasses (Dog, Cat, etc.), improving maintainability.

‍

2. Enhanced Flexibility:

Changes to the internal implementation do not affect the external interface, allowing developers to modify or extend functionality easily.

‍

Example : 

Java
abstract class Animal {
  void makeSound();
}

class Dog extends Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
}

public class Main {
  public static void main(String[] args) {
    Animal myDog = new Dog();
    myDog.makeSound(); // Outputs: Bark
  }
}
‍

The Animal abstract class provides a consistent way to interact with animals. Even if the Dog class changes its internal logic, the external interface (makeSound()) remains unchanged.

‍

3. Better Code Reusability:

Abstract classes and interfaces promote code reuse by defining common behaviors that can be shared across multiple classes.

‍

Java
abstract class Animal {
  void eat() {
    System.out.println("Animal is eating...");
  }
}

class Dog extends Animal {
  // Inherits the eat() method
}

class Cat extends Animal {
  // Inherits the eat() method
}

public class Main {
  public static void main(String[] args) {
    Dog myDog = new Dog();
    myDog.eat(); // Outputs: Animal is eating...
    Cat myCat = new Cat();
    myCat.eat(); // Outputs: Animal is eating...
  }
}
‍‍

The eat() method is defined once in the abstract class Animal and reused by all subclasses (Dog, Cat), eliminating code duplication.

‍

4. Increased Security:

Hiding implementation details reduces the risk of accidental interference with internal workings.

‍

Example : 

Java
abstract class Animal {
  private String secret = "Sensitive data";
  abstract void makeSound();
  protected String getSecret() {
    return secret; // Controlled access to sensitive data
  }
}

class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
    System.out.println("Accessing secret: " + getSecret());
  }
}

public class Main {
  public static void main(String[] args) {
    Animal myDog = new Dog();
    myDog.makeSound();
  }
}
‍

The private field secret in Animal is hidden from external access. Subclasses can access it through controlled methods like getSecret(), ensuring security.

‍

👎🏼 Disadvantages of Abstract Classes : 
Complexity in Design:
Designing abstract classes and interfaces requires careful planning and understanding of the system's requirements.

‍

Example: Imagine you're designing an abstraction for animals. If the abstraction is not carefully planned, it can lead to confusion or redundant code.

‍

Java
abstract class Animal {
  abstract void makeSound();
  // Poorly thought-out abstraction: Adding unrelated behaviors
  abstract void fly(); // Not all animals can fly
  abstract void swim(); // Not all animals can swim
}

class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
  @Override
  void fly() {
    throw new UnsupportedOperationException("Dogs can't fly");
  }
  @Override
  void swim() {
    System.out.println("Dog is swimming");
  }
}
‍

❓Why this is problematic:

Irrelevant Methods: The fly() method is irrelevant for animals like dogs and causes unnecessary implementation overhead.
2. Confusion: Subclasses must implement methods that don’t make sense for them, leading to poor readability and maintainability.

3. Errors: Using UnsupportedOperationException introduces runtime errors.

‍

💥 Better Design : 

‍

Java
abstract class Animal {
  abstract void makeSound();
}

interface Flyable {
  void fly();
}

interface Swimmable {
  void swim();
}

class Dog extends Animal implements Swimmable {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void swim() {
    System.out.println("Dog is swimming");
  }
}
‍‍

💭 Why this is better:

• Only animals that can fly or swim implement the relevant interfaces, avoiding irrelevant methods in unrelated classes.

• This keeps the abstraction focused and reduces unnecessary complexity.

‍

2. Overhead:

Abstraction introduces additional layers of complexity and method calls. This can slightly impact performance and make debugging harder, especially when abstractions are overused or unnecessary.

‍

Example: Let’s say you’re building a simple system to play animal sounds. Using abstraction for such a simple use case can introduce unnecessary overhead.

‍

Java
interface Animal {
  void makeSound();
}

class Dog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
}

class Cat implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Meow");
  }
}

public class Main {
  public static void main(String[] args) {
    Animal dog = new Dog();
    dog.makeSound(); // Outputs: Bark
    Animal cat = new Cat();
    cat.makeSound(); // Outputs: Meow
  }
}
‍

❓ Why this is problematic:

Overhead: Introducing the Animal interface for such a simple scenario adds an unnecessary level of indirection.
2. Performance: The method calls go through the interface, adding a minor runtime overhead.

3. Readability: For small and straightforward programs, this abstraction makes the code harder to follow.

‍

💥 Simpler Solution Without Abstraction : 

‍

Java
class Dog {
  void makeSound() {
    System.out.println("Bark");
  }
}

class Cat {
  void makeSound() {
    System.out.println("Meow");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.makeSound(); // Outputs: Bark
    Cat cat = new Cat();
    cat.makeSound(); // Outputs: Meow
  }
}
‍

💭 Why this is better:

• For small and simple programs, directly using concrete classes makes the code more straightforward.

• There’s no need for additional abstraction if you don’t anticipate future changes or extensions.

‍

2. Interface 🔌
An interface defines a contract or a set of rules that a class must adhere to. It contains abstract methods that specify what a class should do, without dictating how it should be done.

‍

Unlike abstract classes, interfaces focus purely on behavior and do not include state (fields). Starting from Java 8, interfaces can also include default and static methods, enabling the addition of shared logic without breaking existing implementations.

‍

Interfaces are a powerful tool for achieving abstraction and ensuring consistency across unrelated classes.

‍

Example : 

Java
// Interface Animal
interface Animal {
  void makeSound(); // Abstract method
  void sleep(); // Abstract method
}

// Specific implementation for Dog
class Dog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void sleep() {
    System.out.println("Sleeping...");
  }
}

// Specific implementation for Cat
class Cat implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Meow");
  }
  @Override
  public void sleep() {
    System.out.println("Sleeping...");
  }
}

public class Main {
  public static void main(String[] args) {
    Animal myDog = new Dog();
    myDog.makeSound(); // Outputs: Bark
    myDog.sleep(); // Outputs: Sleeping...
    Animal myCat = new Cat();
    myCat.makeSound(); // Outputs: Meow
    myCat.sleep(); // Outputs: Sleeping...
  }
}
‍

👍🏼Advantages of Interface:
Decoupling: Interfaces ensure that implementation details are completely separate from the method definitions.
‍

Java
interface Animal {
  void makeSound();
  void sleep();
}

class Dog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void sleep() {
    System.out.println("Dog is sleeping");
  }
}

class Cat implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Meow");
  }
  @Override
  public void sleep() {
    System.out.println("Cat is sleeping");
  }
}
‍

By using interfaces, the Dog and Cat classes are decoupled from the Animal interface, allowing for flexible and interchangeable implementations.

‍

2.Extensibility: Any class that implements Animal must provide its own implementation for makeSound() and sleep().

‍

Java
class Bird implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Chirp");
  }

  @Override
  public void sleep() {
    System.out.println("Bird is sleeping");
  }
}
‍

New animal types like Bird can be easily added by implementing the Animal interface, without modifying existing code.

‍

3. Standardization: Interfaces define a contract, ensuring that all implementing classes behave consistently.

‍

Java
public class Zoo {
  public static void main(String[] args) {
    Animal dog = new Dog();
    Animal cat = new Cat();
    Animal bird = new Bird();
    dog.makeSound();
    cat.makeSound();
    bird.makeSound();
  }
}
‍

The Zoo class can interact with any Animal implementation, ensuring consistent behavior across different animal types.

‍

👎🏼Disadvantages of Interface:
Complexity: Using interfaces can introduce additional complexity, especially in small projects where the benefits of decoupling and extensibility may not be as significant.
‍

Java
interface Animal {
  void makeSound();
  void sleep();
}

class Dog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void sleep() {
    System.out.println("Dog is sleeping");
  }
}

class Cat implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Meow");
  }
  @Override
  public void sleep() {
    System.out.println("Cat is sleeping");
  }
}
‍

In small projects, the added complexity of defining and implementing interfaces may not be justified.

‍

2. Overhead: Implementing multiple interfaces can lead to overhead in terms of code maintenance and readability, especially if the interfaces are not well-designed.

‍

Java
interface Animal {
  void makeSound();
  void sleep();
}

interface Pet {
  void play();
}

class Dog implements Animal, Pet {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void sleep() {
    System.out.println("Dog is sleeping");
  }
  @Override
  public void play() {
    System.out.println("Dog is playing");
  }
}
‍

Implementing multiple interfaces can make the code harder to read and maintain, especially if the interfaces are complex. 🧩🔄

‍

Abstract Class vs Interface in Java:
Abstraction is a fundamental concept in object-oriented programming that allows us to focus on the essential details while hiding unnecessary complexities.

‍

Both abstract classes and interfaces are tools used to achieve abstraction, but they serve different purposes and have distinct use cases. ⚙️

‍

📦 Abstract Class:
1. Definition:

An abstract class is a class that cannot be instantiated directly. It serves as a blueprint for other classes to derive from. 🏗️

‍

2. Method Implementation:

An abstract class can contain both abstract methods (methods without an implementation) and concrete methods (methods with an implementation). 🔨

‍

3. Variables:

Abstract classes can have member variables, including final, non-final, static, and non-static variables. 📜

‍

4. Constructors:

Abstract classes can have constructors, which can be used to initialize variables in the abstract class when it is instantiated by a subclass. 🛠️

‍

🔌 Interface:
1. Definition:

An interface is a reference type in Java, it is similar to a class, and it is a collection of abstract methods and static constants. 🔗

‍

2. Method Implementation:

All methods in an interface are by default abstract and must be implemented by any class that implements the interface. From Java 8, interfaces can have default and static methods with concrete implementations. From Java 9, interfaces can also have private methods. 🏗️

‍

3. Variables:

Variables declared in an interface are by default public, static, and final (constants). 🔑

‍

4. Constructors:

Interfaces are purely designed to define a contract for classes to implement. They cannot have constructors because they do not manage or hold any state, and constructors are used to initialize an object's state. This design aligns with the principle that interfaces focus solely on defining behavior, leaving the implementation details to the implementing classes. 🧳

‍

🤔 When to use what?
🎀 Consider using abstract classes if any of these statements apply to your situation:

• In the Java application, there are some related classes that need to share some lines of code, then you can put these lines of code within the abstract class, and this abstract class should be extended by all these related classes. 🔄

‍

Java
abstract class Animal {
  void eat() {
    System.out.println("Eating...");
  }
  abstract void makeSound();
}

class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
}

class Cat extends Animal {
  @Override
  void makeSound() {
    System.out.println("Meow");
  }
}
‍

The Animal abstract class contains shared code for eat(), and both Dog and Cat extend this class and provide their own implementation for makeSound().

‍

• You can define the non-static or non-final field(s) in the abstract class so that via a method you can access and modify the state of the object to which they belong.

‍

Java
abstract class Animal {
  protected String name;
  abstract void makeSound();
  void setName(String name) {
    this.name = name;
  }
  String getName() {
    return name;
  }
}

class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Bark");
  }
}
‍

The Animal abstract class has a non-static field name and methods to access and modify it. The Dog     class extends Animal and provides its own implementation for makeSound().

‍

• You can expect that the classes that extend an abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).

‍

Java
abstract class Animal {
  protected int age;
  abstract void makeSound();
  void setAge(int age) {
    this.age = age;
  }
  int getAge() {
    return age;
  }
}

class Cat extends Animal {
  @Override
  void makeSound() {
    System.out.println("Meow");
  }
}
• The Animal abstract class has a protected field age and methods to access and modify it. The Cat class extends Animal and provides its own implementation for makeSound().

‍

🎀 Consider using interfaces if any of these statements apply to your situation:  

It is a total abstraction, all methods declared within an interface must be implemented by the class(es) that implements this interface.
‍

Java
interface Animal {
  void makeSound();
  void sleep();
}

class Dog implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void sleep() {
    System.out.println("Dog is sleeping");
  }
}
‍

The Animal interface defines the methods makeSound() and sleep(), and the Dog class implements these methods.

‍

• A class can implement more than one interface. It is called multiple inheritances.

Java
interface Animal {
  void makeSound();
}

interface Pet {
  void play();
}

class Dog implements Animal, Pet {
  @Override
  public void makeSound() {
    System.out.println("Bark");
  }
  @Override
  public void play() {
    System.out.println("Dog is playing");
  }
}
‍

The Dog class implements both Animal and Pet interfaces, providing implementations for makeSound() and play() methods.

‍

You want to specify the behavior of a data type without worrying about its implementation.
‍

Java
interface Animal {
  void makeSound();
  void sleep();
}

class Cat implements Animal {
  @Override
  public void makeSound() {
    System.out.println("Meow");
  }
  @Override
  public void sleep() {
    System.out.println("Cat is sleeping");
  }
}
‍

The Animal interface specifies the behavior for makeSound() and sleep(), and the Cat class provides the implementation for these methods.

‍
When to Use Abstraction?
• When multiple objects share common behavior but have different implementations.

• To define a template or a standard for other classes to follow.

• To hide implementation details and expose only relevant functionalities to the users.

‍

👩🏼‍🎓Interview Questions : 
1. What is the difference between an abstract class and an interface in Java? When would you use one over the other?

✨ Answer:

Abstract classes are used when classes share common functionality and state, whereas interfaces are used to define a contract for unrelated classes. Use abstract classes when you need shared code and interfaces for behavior enforcement.

‍

Java
abstract class Animal {
  String name;
  Animal(String name) {
    this.name = name;
  }
  abstract void sound();
}

interface Pet {
  void play();
}

class Dog extends Animal implements Pet {
  Dog(String name) {
    super(name);
  }
  @Override
  void sound() {
    System.out.println(name + " barks.");
  }
  @Override
  public void play() {
    System.out.println(name + " plays fetch.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog("Buddy");
    dog.sound(); // Output: Buddy barks.
    dog.play(); // Output: Buddy plays fetch.
  }
}
‍

2. Can an abstract class implement an interface? If yes, why would you do it?

✨Answer:

Yes, an abstract class can implement an interface to provide partial implementation. This is useful when some methods in the interface have common logic that can be shared across subclasses.

‍

Java
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
    System.out.println(name + " plays.");
  }
}

class Dog extends Animal {
  Dog(String name) {
    super(name);
  }
  @Override
  void sound() {
    System.out.println(name + " barks.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog("Buddy");
    dog.sound(); // Output: Buddy barks.
    dog.play(); // Output: Buddy plays.
  }
}
‍

3. Why can’t we instantiate an abstract class? What would be the consequences if it were allowed?

✨Answer:

Abstract classes are incomplete blueprints meant to be extended. Allowing instantiation would violate the principle of abstraction, as abstract methods lack implementation.

‍

Java
abstract class Animal {
  abstract void sound();
}

class Dog extends Animal {
  @Override
  void sound() {
    System.out.println("Dog barks.");
  }
}

public class Main {
  public static void main(String[] args) {
    // Animal animal = new Animal(); // Compile-time error
    Dog dog = new Dog();
    dog.sound(); // Output: Dog barks.
  }
}
‍

4. What are the limitations of using abstract classes over interfaces?

✨ Answer:

Abstract classes allow single inheritance only, whereas interfaces can be implemented by multiple classes, offering more flexibility.

‍

Abstract Class Example (Multiple Inheritance Error):
Java
abstract class Animal {
  abstract void sound();
  void commonMethod() {
    System.out.println("Animal common method.");
  }
}

abstract class Mammal {
  abstract void eat();
  void commonMethod() {
    System.out.println("Mammal common method.");
  }
}

class Dog extends Animal, Mammal { // This will cause a compile-time error
  @Override
  void sound() {
    System.out.println("Dog barks.");
  }
  @Override
  void eat() {
    System.out.println("Dog eats.");
  }
  @Override
  void commonMethod() {
    // Which commonMethod() to call? This causes ambiguity.
  }
}
‍

Interface Example (No Error):
Java
interface Animal {
  void sound();
  default void commonMethod() {
    System.out.println("Animal common method.");
  }
}

interface Pet {
  void play();
  default void commonMethod() {
    System.out.println("Pet common method.");
  }
}

class Dog implements Animal, Pet {
  @Override
  public void sound() {
    System.out.println("Dog barks.");
  }
  @Override
  public void play() {
    System.out.println("Dog plays fetch.");
  }
  @Override
  public void commonMethod() {
    Animal.super.commonMethod();
    Pet.super.commonMethod();
    System.out.println("Dog's own common method.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.sound(); // Output: Dog barks.
    dog.play(); // Output: Dog plays fetch.
    dog.commonMethod(); // Output: Animal common method.
                        //         Pet common method.
                        //         Dog's own common method.
  }
}
‍

5. When should you not use an interface? Provide a practical example.

✨Answer:

Avoid interfaces when the implementing classes share common functionality or state. For example, if Dog and Cat both need an eat() method with shared logic, an abstract class like Animal is more appropriate than an interface.

‍

Java
abstract class Animal {
  void eat() {
    System.out.println("Eating...");
  }
}

class Dog extends Animal {
  void sound() {
    System.out.println("Dog barks.");
  }
}

class Cat extends Animal {
  void sound() {
    System.out.println("Cat meows.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    Cat cat = new Cat();
    dog.eat(); // Output: Eating...
    dog.sound(); // Output: Dog barks.
    cat.eat(); // Output: Eating...
    cat.sound(); // Output: Cat meows.
  }
}
‍‍

6. What are default methods in Java interfaces? Why were they introduced?

✨ Answer:

Default methods are methods in interfaces that have a body (implementation). They were introduced in Java 8 to provide backward compatibility. This allows interfaces to evolve by adding new methods without breaking existing implementations of the interface.

‍

Java
interface Animal {
  void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Dog implements Animal {
  // No need to override sound
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.sound(); // Output: This is a default animal sound.
  }
}
‍

7. What is the difference between abstract methods and default methods in an interface?

✨Answer:

Abstract methods have no body and must be implemented by a class that implements the interface. Default methods have a body and can be optionally overridden by implementing classes.

‍

Java
interface Animal {
  void eat(); // Abstract method
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Dog implements Animal {
  @Override
  public void eat() {
    System.out.println("Dog is eating.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Output: Dog is eating.
    dog.sound(); // Output: This is a default animal sound.
  }
}
‍‍

8. Why do we need default methods in Java? Couldn’t we achieve the same with abstract classes?

✨Answer:

Default methods allow interfaces to add new behavior without forcing all implementing classes to change. Abstract classes cannot achieve this because Java does not allow multiple inheritance of classes. Interfaces with default methods enable flexibility while avoiding the diamond problem.

‍

Java
interface Animal {
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

abstract class Mammal {
  abstract void eat();
}

class Dog extends Mammal implements Animal {
  @Override
  void eat() {
    System.out.println("Dog is eating.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat(); // Output: Dog is eating.
    dog.sound(); // Output: This is a default animal sound.
  }
}
‍‍

9. Can a class implement an interface without overriding its default methods?

✨Answer:

Yes, a class can implement an interface without overriding its default methods. The default implementation will be inherited. However, the class can override the method if it needs custom behavior.

‍

Java
interface Animal {
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Dog implements Animal {
  // No need to override sound
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.sound(); // Output: This is a default animal sound.
  }
}
‍‍

10. What happens if a class implements an interface with a default method and also inherits the same method from a superclass? Which one gets priority?

✨Answer:

The method from the superclass takes priority over the default method in the interface. The class will inherit the superclass's method unless it explicitly overrides it.

‍

Java
interface Animal {
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Mammal {
  public void sound() {
    System.out.println("This is a mammal sound.");
  }
}

class Dog extends Mammal implements Animal {
  // No need to override sound
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.sound(); // Output: This is a mammal sound.
  }
}
‍

11. What happens if a class implements two interfaces that have a default method with the same name?

✨Answer:

If a class implements two interfaces with the same default method, it must override the method to resolve the ambiguity explicitly.

‍

🫎 Example Scenario: Animal Sounds

Imagine two interfaces, DogBehaviour and CatBehaviour, both of which have a makeSound() default method. A class AnimalProcessor implements both interfaces and needs to resolve the conflict explicitly.

‍

Java
interface DogBehavior {
  default void makeSound() {
    System.out.println("Dog barks.");
  }
}

interface CatBehavior {
  default void makeSound() {
    System.out.println("Cat meows.");
  }
}

class AnimalProcessor implements DogBehavior, CatBehavior {
  // Resolving the conflict by overriding the method
  @Override
  public void makeSound() {
    System.out.println(
        "Resolving conflict between DogBehavior and CatBehavior:");

    // Call the default method from DogBehavior
    DogBehavior.super.makeSound();

    // Call the default method from CatBehavior
    CatBehavior.super.makeSound();

    // Adding custom behavior
    System.out.println(
        "Custom behavior: AnimalProcessor decides which sound to make.");
  }
}

public class Main {
  public static void main(String[] args) {
    AnimalProcessor processor = new AnimalProcessor();
    processor.makeSound();
  }
}
‍

Explanation:
○ Default Methods Conflict:

Both DogBehaviour and CatBehaviour define a makeSound() default method. When AnimalProcessor implements both interfaces, the compiler cannot determine which version to use.

‍

○ Conflict Resolution:

To resolve the conflict, AnimalProcessor explicitly overrides the makeSound() method and uses InterfaceName.super.methodName() to call the specific default method from each interface.

‍

○ Custom Logic:

The makeSound() method in AnimalProcessor adds custom behavior after calling the default methods from the interfaces.

‍

12. Is it possible to override a default method and make it abstract in a subclass or interface? Why or why not?

✨Answer:

No, a default method cannot be overridden and made abstract. Once a default method is defined, overriding implementations must provide a concrete implementation.

‍

Java
interface Animal {
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Dog implements Animal {
  @Override
  public void sound() {
    System.out.println("Dog barks.");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.sound(); // Output: Dog barks.
  }
}
‍

13. Can default methods access instance variables of the implementing class? Why or why not?

✨Answer:

No, default methods cannot access instance variables of the implementing class because interfaces do not have state. Default methods are stateless and only work with parameters and their internal logic.

‍

Java
interface Animal {
  default void sound() {
    System.out.println("This is a default animal sound.");
  }
}

class Dog implements Animal {
  private String name = "Buddy";
  public void printName() {
    System.out.println("Dog's name is " + name);
  }
}

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.printName(); // Output: Dog's name is Buddy
    dog.sound(); // Output: This is a default animal sound.
  }
}
‍

14. What are some limitations of default methods in comparison to methods in abstract classes?

✨Answer:

• Default methods cannot have instance variables.

• They cannot use super to refer to the implementing class’s parent.

• Abstract classes can have constructors and fields, but interfaces cannot.

‍

Java
// Abstract class example showcasing fields, constructors, and abstract methods
abstract class Animal {
  String name; // Instance variable
  // Constructor to initialize the name
  Animal(String name) {
    this.name = name;
  }
  // Abstract method
  abstract void sound();
  // Non-abstract method to demonstrate additional functionality
  void eat() {
    System.out.println(name + " is eating.");
  }
}

// Interface example showcasing default methods and their limitations
interface Playable {
  // Default method
  default void play() {
    System.out.println("Playing with the animal.");
  }
  // Attempt to declare an instance variable (not allowed in interfaces)
  String name = "Buddy";
  // Interfaces can only contain static final variables, which are essentially
  // constants. Since it is static you cannot call it instance variable.
  default void setName(String name) {
    this.name = name; // Error: Interfaces cannot have instance variables
  }
}

// Dog class extends abstract class Animal and implements interface Playable
class Dog extends Animal implements Playable {
  // Constructor calling the abstract class constructor
  Dog(String name) {
    super(name);
  }

  // Overriding the abstract method
  @Override
  void sound() {
    System.out.println(name + " barks.");
  }

  // Uncommenting the following code will cause an error because default methods
  // cannot use super to refer to parent methods

  @Override
  public void play() {
    super.play(); // Error: Cannot use super to refer to a parent method in an
                  // interface
  }
}

public class Main {
  public static void main(String[] args) {
    // Abstract class functionality
    Dog dog = new Dog("Buddy");
    dog.sound(); // Output: Buddy barks.
    dog.eat(); // Output: Buddy is eating.

    // Interface functionality
    dog.play(); // Output: Playing with the animal.
  }
}
‍

🎉 Conclusion : 
Abstraction is a powerful tool in OOP that simplifies code by focusing on what an object does rather than how it does it. By using abstract classes and interfaces, developers can create flexible, reusable, and maintainable code. Mastering abstraction is crucial for designing robust and scalable Java applications.


<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Generics and Wildcards

🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Generics and wildcards are essential features in Java's type system, introduced to enable type safety and reusability in code. These features allow developers to write generic classes and methods that operate on different types while maintaining compile-time type safety.

📦What Are Generics?
Generics provide a way to parameterize types in Java. They enable classes, interfaces, and methods to operate on various data types without the need to specify the exact type at compile time. By using generics, developers can ensure type safety, reduce code duplication, and improve readability.

‍

Types of Java Generics : 
1. Generic Method:
Generic Java method takes a parameter and returns some value after performing a task. It is exactly like a normal function, however, a generic method has type parameters that are cited by actual type. This allows the generic method to be used in a more general way. The compiler takes care of the type of safety which enables programmers to code easily since they do not have to perform long, individual type castings.

‍

Example : 

Java
class Test {
  // A Generic method example
  static <T> void genericDisplay(T element) {
    System.out.println(element.getClass().getName() + " = " + element);
  }

  // Driver method
  public static void main(String[] args) {
    // Calling generic method with Integer argument
    genericDisplay(11);

    // Calling generic method with String argument
    genericDisplay("CodeWithAryan");

    // Calling generic method with double argument
    genericDisplay(1.0);
  }
}
‍

📤 Output : 

java.lang.Integer = 11
java.lang.String = CodeWithAryan
	java.lang.Double = 1.0‍
‍

🧩 Explanation : 

In the provided code snippet, the genericDisplay() method is a generic method, which means it can operate on objects of various types while maintaining type safety. The <T> syntax indicates that T is a type parameter, allowing the method to accept any type of argument. This flexibility is what makes the method generic. The method prints the class name and value of the passed argument, demonstrating how it can handle different data types (Integer, String, and Double) without requiring multiple method overloads.

‍

The genericDisplay() method is declared as static to allow it to be called without creating an instance of the Test class. This is useful for utility methods that don't rely on instance variables. By making the method static, it can be directly invoked using the class name, as shown in the main method.

‍

2. Generic Classes:
A generic class is implemented exactly like a non-generic class. The only difference is that it contains a type parameter section. There can be more than one type of parameter, separated by a comma. The classes, which accept one or more parameters, are known as parameterized classes or parameterized types.

‍

Example : 

Java
// We use < > to specify Parameter type
class Test<T> {
  // An object of type T is declared
  T obj;
  Test(T obj) {
    this.obj = obj;
  } // constructor
  public T getObject() {
    return this.obj;
  }
}

// Driver class to test above
class Main {
  public static void main(String[] args) {
    // instance of Integer type
    Test<Integer> iObj = new Test<Integer>(15);
    System.out.println(iObj.getObject());
    // instance of String type
    Test<String> sObj = new Test<String>("CodeWithAryan");
    System.out.println(sObj.getObject());
  }
}
‍

📤 Output : 

15
CodeWithAryan
🧩 Explanation : 

the Test<T> class is a generic class that can handle any type specified by the user. The <T> syntax indicates that T is a type parameter, allowing the class to operate on objects of various types while maintaining type safety. This means that the class can be used with different data types without requiring multiple class definitions. The constructor Test(T obj) initializes the object of type T, and the getObject() method returns this object. This flexibility makes the code more reusable and easier to maintain.

‍

In the Main class, instances of Test are created with Integer and String types, demonstrating the versatility of generics. The iObj instance is created with an Integer type, and the sObj instance is created with a String type. The getObject() method is called on both instances, and the output shows the values 15 and CodeWithAryan, respectively. This confirms that the generic class can handle different data types seamlessly, providing a powerful tool for developers to write more flexible and type-safe code.

‍

NOTE : We can also pass multiple Type parameters in Generic classes.

‍

Example : 

Java
// We use < > to specify Parameter type
class Test<T, U> {
  T obj1; // An object of type T
  U obj2; // An object of type U

  // constructor
  Test(T obj1, U obj2) {
    this.obj1 = obj1;
    this.obj2 = obj2;
  }

  // To print objects of T and U
  public void print() {
    System.out.println(obj1);
    System.out.println(obj2);
  }
}

// Driver class to test above
class Main {
  public static void main(String[] args) {
    Test<String, Integer> obj = new Test<String, Integer>(15, CodeWithAryan);
    obj.print();
  }
}
📤 Output : 

15
CodeWithAryan
🧩 Explanation : 

the Test<T, U> class demonstrates the use of multiple type parameters. The class can handle two different types specified by the user, indicated by <T, U>. This allows the class to operate on objects of two different types while maintaining type safety. The constructor Test(T obj1, U obj2) initializes objects of types T and U, and the print() method displays these objects. This example highlights the versatility of generics in handling multiple types, making the code more reusable and type-safe.

‍

In the Main class, an instance of Test is created with String and Integer types. The obj instance is created with String and Integer types, and the print() method is called on this instance. The output shows the values 15 and CodeWithAryan, confirming that the generic class can handle multiple data types simultaneously. This demonstrates the power of generics in Java, allowing developers to write more flexible and reusable code that can handle different data types without requiring multiple class definitions.

‍

NOTE : Generics Work Only with Reference Types:

‍

When we declare an instance of a generic type, the type argument passed to the type parameter must be a reference type. We cannot use primitive data types like int, char, boolean etc.

Test<int> obj = new Test<int>(20);
The above line results in a compile-time error that can be resolved using type wrappers to encapsulate a primitive type. For example, we can use the Integer wrapper class instead of int:

Test<Integer> obj = new Test<Integer>(20);
But primitive type arrays can be passed to the type parameter because arrays are reference types.

ArrayList<int[]> a = new ArrayList<>();
‍

💥Benefits of Generics : 
1. Type Safety:
○ Generics prevent runtime type errors by catching them at compile time.

‍

 Example :

Java
// We use < > to specify Parameter type
class Test<T> {
  // An object of type T is declared
  T obj;
  Test(T obj) {
    this.obj = obj;
  } // constructor
  public T getObject() {
    return this.obj;
  }
}

// Driver class to test above
class Main {
  public static void main(String[] args) {
    // instance of Integer type
    Test<Integer> iObj = new Test<Integer>(15);
    System.out.println(iObj.getObject());

    // instance of String type
    Test<String> sObj = new Test<String>("GeeksForGeeks");
    System.out.println(sObj.getObject());
    iObj = sObj; // This results an error
  }
}
‍

Even though iObj and sObj are of type Test, they are the references to different types because their type parameters differ. Generics add type safety through this and prevent errors.

‍

2. Code Reusability:
○ Generic classes and methods can work with any data type, reducing code duplication.

‍

Example : 

Java
public class GenericReusability {
  // A generic method to print elements of any type
  public static <T> void printArray(T[] array) {
    for (T element : array) {
      System.out.print(element + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Integer[] intArray = {1, 2, 3, 4};
    String[] stringArray = {"Apple", "Banana", "Cherry"};
    printArray(intArray); // Works with Integer
    printArray(stringArray); // Works with String
  }
}
‍

3. Readability and Maintainability:
○ Explicit type declarations make the code easier to understand and maintain.

‍

Example : 

Java
// Custom Generic ArrayList
class MyArrayList<T> {
  private Object[] elements;
  private int size = 0;
  public MyArrayList() {
    elements = new Object[10]; // Default capacity
  }

  public void add(T element) {
    if (size == elements.length) {
      resize();
    }
    elements[size++] = element;
  }

  public T get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    return (T) elements[index];
  }

  private void resize() {
    Object[] newElements = new Object[elements.length * 2];
    System.arraycopy(elements, 0, newElements, 0, elements.length);
    elements = newElements;
  }
}

// Without explicit type declaration
MyArrayList list1 = new MyArrayList();
list1.add("Hello");
list1.add("World");
list1.add(1);
list1.add('a');

// With explicit type declaration
MyArrayList<String> list2 = new MyArrayList<>();
list2.add("Hello");
list2.add("World");
‍

In the first example, the type of list is not immediately clear, which can make the code harder to understand and maintain. In the second example, the explicit type declaration ArrayList<String> makes it clear that list is an ArrayList of String objects, improving readability and maintainability.

‍

4. Elimination of Type Casting:
○ Generics make errors to appear compile time than at run time (It’s always better to know problems in your code at compile time rather than making your code fail at run time).

Suppose you want to create an ArrayList that store name of students, and if by mistake the programmer adds an integer object instead of a string, the compiler allows it. But, when we retrieve this data from ArrayList, it causes problems at runtime.

‍

❓Problem : 

Java
import java.util.*;

class Test {
  public static void main(String[] args) {
    // Creating an ArrayList without any type specified
    MyArrayList list = new MyArrayList();
    list.add("Sachin");
    list.add("Rahul");
    list.add(10); // Compiler allows this
    String s1 = (String) list.get(0);
    String s2 = (String) list.get(1);
    // Causes Runtime Exception
    String s3 = (String) list.get(2);
  }
}
‍

📤 Output :

Exception in thread "main" java.lang.ClassCastException: 
 java.lang.Integer cannot be cast to java.lang.String
🧩 Solution : 

When defining ArrayList, we can specify that this list can take only String objects.

‍

Java
import java.util.*;

class Test {
  public static void main(String[] args) {
    // Creating a an ArrayList with String specified
    MyArrayList<String> list = new MyArrayList<String>();
    list.add("Sachin");
    list.add("Rahul");
    list.add(10);

    // Typecasting is not needed
    String s1 = list.get(0);
    String s2 = list.get(1);
    String s3 = list.get(2);
  }
}
‍

📤 Output: 

15: error: no suitable method found for add(int)
    al.add(10);
‍

🌪️ What Are Wildcards in Generics?
Wildcards are special symbols used in generics to represent an unknown type. They provide flexibility when working with generic types and allow developers to define relationships between different types.

‍

Example : 

Java
import java.util.List;

public class WildcardExample {
  public static void printList(List<?> list) {
    for (Object item : list) {
      System.out.println(item);
    }
  }

  public static void main(String[] args) {
    List<String> stringList = List.of("Apple", "Banana", "Cherry");
    printList(stringList);
    List<Integer> intList = List.of(1, 2, 3);
    printList(intList);
  }
}
Types of Wildcards : 
1. Unbounded Wildcard (?) : 
• Represents an unknown type.

• Useful when the type is not relevant to the logic.

‍

Example:
Java
import java.util.List;

public class UnboundedWildcardExample {
  public static void printList(List<?> list) {
    for (Object item : list) {
      System.out.println(item);
    }
  }

  public static void main(String[] args) {
    List<String> stringList = List.of("Apple", "Banana", "Cherry");
    printList(stringList);
    List<Integer> intList = List.of(1, 2, 3);
    printList(intList);
  }
}
‍

2. Upper-Bounded Wildcard (? extends Type) : 
• Restricts the type to Type or its subclasses.

• Useful for read-only operations where the specific type is not required.

‍

Example:
Java
import java.util.List;

public class UpperBoundedWildcardExample {
  public static void printNumbers(List<? extends Number> list) {
    for (Number number : list) {
      System.out.println(number);
    }
  }

  public static void main(String[] args) {
    List<Integer> intList = List.of(1, 2, 3);
    printNumbers(intList);
    List<Double> doubleList = List.of(1.1, 2.2, 3.3);
    printNumbers(doubleList);
    List<String> stringList = List.of("a", "b", "c");
    printNumbers(stringList);
    // Error: incompatible types: List<String> cannot be converted to List<?
    // extends Number>
  }
}
‍

3. Lower-Bounded Wildcard (? super Type)
• Restricts the type to Type or its superclasses.

• Useful for write operations.

‍

Example:
Java
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcardExample {
  public static void addNumbers(List<? super Integer> list) {
    list.add(42);
  }
  public static void main(String[] args) {
    List<Number> numberList = new ArrayList<>();
    addNumbers(numberList);
    System.out.println(numberList);
  }
}
💭 Generics vs Wildcards : 
Article image

‍

🤔 When to Use what ? 
The decision to use generics or wildcards in Java largely depends on the context of your code and how you plan to interact with the objects.

‍

Let’s break this down into specific method use cases and scenarios where one is more suitable than the other.

🏷️Why Use a Generic Instead of a Wildcard? 
1. When You Need Type Consistency Across the Method : 

If a method must ensure that all arguments or returned values are of the same specific type, use generics. Generics explicitly define the type and provide compile-time type safety.

‍

Java
import java.util.ArrayList;
import java.util.List;

// Utility class for list operations
public class ListUtils {
  // Generic method to add two elements of the same type to a list
  public static <T> void addElementsToList(
      List<T> list, T firstElement, T secondElement) {
    list.add(firstElement);
    list.add(secondElement);
    System.out.println(
        "Elements added: " + firstElement + ", " + secondElement);
  }
}

// A simple Person class for custom object example
class Person {
  private String name;
  public Person(String name) {
    this.name = name;
  }
  @Override
  public String toString() {
    return name;
  }
}

public class Main {
  public static void main(String[] args) {
    // Example with a list of Strings
    List<String> stringList = new ArrayList<>();
    ListUtils.addElementsToList(stringList, "Apple", "Banana");
    System.out.println("Updated String List: " + stringList);

    // Example with a list of Integers
    List<Integer> intList = new ArrayList<>();
    ListUtils.addElementsToList(intList, 10, 20);
    System.out.println("Updated Integer List: " + intList);

    // Example with a list of Doubles
    List<Double> doubleList = new ArrayList<>();
    ListUtils.addElementsToList(doubleList, 1.5, 2.5);
    System.out.println("Updated Double List: " + doubleList);

    // Example with a list of custom objects
    List<Person> personList = new ArrayList<>();
    ListUtils.addElementsToList(
        personList, new Person("Alice"), new Person("Bob"));
    System.out.println("Updated Person List: " + personList);
  }
}
‍

2. When You’re Creating or Adding to a Collection

Generics are required when you’re adding elements to a collection. Wildcards (?) don’t allow additions because the compiler cannot guarantee the type safety for the unknown type.In the above example, we have three lists: intList, doubleList, and personList.

For each list, we are adding an element of the same data type, which is made possible through the use of Generics. 

‍

Java
public static void main(String[] args) {
  // Example with a list of Strings
  List<String> stringList = new ArrayList<>();
  ListUtils.addElementsToList(stringList, "Apple", "Banana");
  System.out.println("Updated String List: " + stringList);

  // Example with a list of Integers
  List<Integer> intList = new ArrayList<>();
  ListUtils.addElementsToList(intList, 10, 20);
  System.out.println("Updated Integer List: " + intList);

  // Example with a list of Doubles
  List<Double> doubleList = new ArrayList<>();
  ListUtils.addElementsToList(doubleList, 1.5, 2.5);
  System.out.println("Updated Double List: " + doubleList);

  // Example with a list of custom objects
  List<Person> personList = new ArrayList<>();
  ListUtils.addElementsToList(
      personList, new Person("Alice"), new Person("Bob"));
  System.out.println("Updated Person List: " + personList);
}
‍

Example with Wildcards (Doesn't Work for Adding):

Java
public void addToWildcardList(List<?> list, Object element) {
     list.add(element);  // Compile-time error: Cannot add to a List<?>
}
‍

Here, generics are essential if you need to add elements, as they provide a known type, while wildcards (?) are read-only for safety.

‍

3. When You Need a Specific Type in Return : 

If a method needs to return an object of a specific type, use generics. Wildcards (?) make the type unknown, which is not useful for returned values.Extending our Previous example, if we were to create a method to get the first element of the list, we would use generics for the same, as the compiler will be able to determine the return type of the function which would not be possible in case of wildcards

‍

Java
public <T> T getFirstElement(List<T> list) {
  return list.size() == 0 ? null : list.get(0); // Compiler knows the type of T
}

public static void main(String[] args) {
  // Example with a list of Strings
  List<String> stringList = new ArrayList<>();
  ListUtils.addElementsToList(stringList, "Apple", "Banana");
  System.out.println("Updated String List: " + stringList);
  String firstString = getFirstString(stringList);

  // Example with a list of Integers
  List<Integer> intList = new ArrayList<>();
  ListUtils.addElementsToList(intList, 10, 20);
  System.out.println("Updated Integer List: " + intList);
  Integer firstInteger = getFirstElement(intList);
}
‍

Using a wildcard would not allow the compiler to determine the type of the returned object, making it less flexible.

‍

This approach requires returning an Object every time from the function using wildcards, which is not ideal. Although wrapper classes inherit from the Object class in Java, the return type should be the actual wrapper class and not the Object type. This ensures type safety and clarity in the code, allowing for more precise and maintainable implementations.

‍

Java
// Wildcard method that gets the first element
public Object getFirstElementWithWildcard(List<?> list) {
  return list.get(0); // Returns as Object because the type is unknown
}

public static void main(String[] args) {
  List<Integer> numbers = List.of(1, 2, 3);
  Object firstElement =
      getFirstElementWithWildcard(numbers); // Returns as Object
  System.out.println("First Element (Object): " + firstElement);

  // Must cast manually to Integer
  int firstNumber = (Integer) firstElement;
  System.out.println("First Number (int): " + firstNumber);
}
‍

In order to get the actual type while using wildcards, we will have to manually perform type casting, which is not an efficient practice. This approach can lead to potential runtime errors and reduces the clarity and maintainability of the code. 

‍

🌪️ Why Use a Wildcard Instead of a Generic ? 
1. When You’re Only Reading from a Collection

If a method only needs to read from a collection and doesn’t care about the exact type, use wildcards. Wildcards provide flexibility and are less restrictive than generics.

‍

Java
public class Main {
  // Generic method that requires a specific type
  public <T> void printList1(List<T> list) {
    for (T item : list) {
      System.out.println(item); // Can read items of type T
    }
  }
  // Wildcard method that doesn't require a specific type
  public void printList2(List<?> list) {
    for (Object item : list) {
      System.out.println(item); // Can read all items
    }
  }

  public static void main(String[] args) {
    List<String> strings = List.of("A", "B", "C");
    List<Integer> integers = List.of(1, 2, 3);
    List newList =
        List.of(1, 2, 3, 'a', 'b', 'c', "Code", "With", "Aryan", 1.23, 2.5);
    // Works because the generic type matches the list type
    printList1(strings); // T is inferred as String
    printList1(integers); // T is inferred as Integer
    printList1(newList); // doesn't work because the type is not specified in
                         // the newList
    printList2(
        strings); // works because wildcards do not care about exact types
    printList2(
        integers); // works because wildcards do not care about exact types
    printList2(
        newList); //  works because wildcards do not care about exact types
  }
}
‍

If you were to use generics instead of wildcards in the printList method, the method would require a specific type when called. This makes the method less flexible, as it can no longer handle lists of different types without specifying the type explicitly.

‍

🌟 Best Practices : 
1. Use generics for strongly typed classes and methods.

2. Use wildcards when flexibility in type is needed, especially in method parameters.

3. Use upper-bounded wildcards for read-only operations and lower-bounded wildcards for write operations.

4. Avoid overusing wildcards to keep the code readable and maintainable.

‍

🎉 Conclusion : 
Generics and wildcards are powerful tools that enhance type safety and flexibility in OOP. By understanding their differences and use cases, developers can write more reusable, maintainable, and efficient code. Mastery of generics and wildcards is essential for handling collections, frameworks, and APIs effectively.

<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Access Modifiers

Topic Tags:
oop
system design
lld
‍‍🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Access modifiers 🔒 are fundamental in Object-Oriented Programming (OOP) 💻 as they control the visibility 👀 and accessibility of classes, methods, and variables in a program. They play a crucial role in encapsulation 🔐 by restricting or allowing access to certain parts of the code based on the specified modifier.

‍

Access modifiers prevent data members or functions of one class from tampering 🛑 with another class while restricting its access. It allows us to select which members can be accessed directly by outside functions and which are not. ✅

🏗️ Project Structure Overview:
Before diving into the types of access modifiers 🔒, let me walk you through the project structure that will be used to demonstrate their behavior.

Below is an image 🖼️ of the project setup:

‍

Project Structure:
Article image

• src/:

The root folder containing all the code files.

‍

• demoPackage/:

This package contains the DemoClass, which we will use to demonstrate the usage of different access modifiers in Java.

‍

• utilityClasses/:

This package contains the following classes:

PublicClassExample: Demonstrates the use of the public access modifier.
PrivateClassExample: Demonstrates the use of the private access modifier.
ProtectedClassExample: Demonstrates the use of the protected access modifier.
DefaultClassExample: Demonstrates the default (package-private) access modifier.
‍

• Main.java:

The main entry point for running the project.

‍

This structure allows us to clearly show how access modifiers work across:
1. Classes within the same package (demoPackage).

2. Classes in different packages (utilityClasses).

‍

Each of these access modifiers—public, private, protected, and default—will be explored using these classes and their relationships.

‍

Types of Access Modifiers in Java : 
Java provides four main types of access modifiers:

1. Public : 
🌐 Scope:
A public class, method, or variable is accessible from anywhere in the application, whether it’s within the same package or from a different package.

‍

🛠️ Usage:
The public modifier is used when you want the element (class, method, or variable) to be globally accessible. In the context of our project structure, the PublicClassExample in the utilityClasses package is accessible to other classes such as DemoClass in the demoPackage package.

‍

💻 Code Example: Demonstrating the Public Modifier
Here’s how we use the PublicClassExample to demonstrate the public modifier:

‍

PublicClassExample.java : 

• Located in the utilityClasses package:

‍

Java
package utilityClasses; // Declared in the utilityClasses package
public class PublicClassExample {
  public void display() {
    System.out.println("This is a public method in PublicClassExample.");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the public method:

Java
package demoPackage; // Declared in the demoPackage package
import utilityClasses.PublicClassExample; // Importing the PublicClassExample from another package

public class DemoClass {
  public static void main(String[] args) {
    // Creating an object of PublicClassExample
    PublicClassExample example = new PublicClassExample();

    // Calling the public method
    example.display(); // Accessible because 'display()' is public
  }
}
Article image

‍
🧩 Explanation : 
The display() method in PublicClassExample is declared as public, making it accessible across packages. By importing utilityClasses.PublicClassExample in DemoClass, we can use the method display() without any access restrictions.

‍

When you run the DemoClass, the output will be:

This is a public method.
Article image

‍
2. Private : 
🌐 Scope: 
A private class, method, or variable is not accessible from anywhere in the application. It is only accessible within the same class and not from outside the class, whether in the same package, a child class, or a different package. It will be accessible only within the same class.

🛠️ Usage: 
The private modifier is used when you want an element (class, method, or variable) to be accessible only within the same class. In the context of our project structure, the PrivateClassExample in the utilityClasses package is accessible to other classes because it is declared public. However, its methods won't be accessible to the DemoClass in the demoPackage package, as all the methods defined are private, which will result in a compile-time error.

‍

💻 Code Example: Demonstrating the Private Modifier
Here’s how we use the PrivateClassExample to demonstrate the public modifier:

‍

PrivateClassExample.java : 

Located in the utilityClasses package

Java
package utilityClasses;

public class PrivateClassExample {
  private String secret = "Hidden Message";
  private void displaySecret() {
    System.out.println(secret);
  }
  private void show() {
    displaySecret(); // Accessible within the same class
  }
}
Article image

‍

DemoClass.java : 

Located in the demoPackage package, demonstrating the usage of the private method

Java
package demoPackage;
import utilityClasses.PrivateClassExample;

public class DemoClass {
  public static void main(String[] args) {
    PrivateClassExample example = new PrivateClassExample();
    example.show();
  }
}
‍

The show() method in PrivateClassExample is declared as private, making it accessible only within the PrivateClassExample class. By importing utilityClasses.PrivateClassExample in DemoClass, we cannot use the show() method.

Whenever we try to access the show() method of the PrivateClassExample, since it's declared private, the compiler will give us a warning stating that 'show() has private access in utilityClasses.PrivateClassExample'.

‍

Even if you forcefully run the DemoClass, the output will be a compile-time error, which will be printed in the terminal:

java: show() has private access in utilityClasses.PrivateClassExample
Article image

‍

Article image

‍
3. Protected
🌐 Scope: 
The protected modifier allows access members within the same package and from subclasses in other packages. It offers more restricted access compared to public, but it is broader than private. By using protected, you enable controlled inheritance, allowing child classes to reuse and extend parent class functionality while keeping it hidden from unrelated classes.

‍

🛠️ Usage:
The protected modifier is used in scenarios where inheritance is a key design pattern. For example, you might define reusable methods or fields in a superclass that should only be accessed or overridden by its subclasses. It is commonly used in frameworks or libraries to expose specific functionality to derived classes while restricting general access.

‍

💻 Code Example: Demonstrating the Protected Modifier
Here’s how we use the ProtectedClassExample to demonstrate the public modifier:

‍

ProtectedClassExample.java : 

Located in the utilityClasses package

Java
package utilityClasses;

public class ProtectedClassExample {
  protected void display() {
    System.out.println("Hello from Parent class!");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the protected method:

Java
package demoPackage;
import utilityClasses.ProtectedClassExample;

public class DemoClass {
  public static void main(String[] args) {
    ProtectedClassExample example = new ProtectedClassExample();
    example.display();
  }
}
‍

The display() method in ProtectedClassExample is declared as protected when no access modifier is assigned, making it accessible only within the classes present in the same package and also in the classes present in different packages if they are a subclass of the ProtectedClassExample. By importing utilityClasses.ProtectedClassExample in DemoClass, we cannot use the display() method, as the DemoClass is not a subclass of the ProtectedClassExample.

‍

Whenever we try to access the display() method of the ProtectedClassExample, since it's declared protected, the compiler will give us a warning stating that:

'display()' has protected access in 'utilityClasses.ProtectedClassExample'
Article image

‍

In order to access the parent class method, we can make DemoClass extend the ProtectedClassExample class, thereby making DemoClass a subclass of ProtectedClassExample.

This will allow us to access the display() method of the ProtectedClassExample.

‍

Code : 

Java
package demoPackage;
import utilityClasses.ProtectedClassExample;

public class DemoClass extends ProtectedClassExample {
  public static void main(String[] args) {
    ProtectedClassExample example = new ProtectedClassExample();
    example.display();
  }
}
‍

The output in the Terminal will be : 

Hello from Parent class!
Article image

‍
4. Default (Package-Private)
🌐Scope: 
When no access modifier is specified, the default (package-private) access modifier is applied. Members with this access modifier are accessible only within the same package but not from outside it. This ensures that the functionality is available for closely related classes within the package while being hidden from other parts of the application.

🛠️ Usage: 
Default access is used when you want to limit access to package-level components. It is ideal for internal helper classes, methods, or variables that do not need to be exposed to external packages. This access level supports modularity by grouping related classes and ensuring that their interactions remain encapsulated within the package.

💻 Code Example: Demonstrating the Default Modifier : 
Here’s how we use the DefaultClassExample to demonstrate the public modifier:

‍

DefaultClassExample.java : 

• Located in the utilityClasses package:

Java
package utilityClasses;

public class DefaultClassExample {
  void display() {
    System.out.println("This is a default access method.");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the default method:

Java
package demoPackage;
import utilityClasses.DefaultClassExample;

public class DemoClass {
  public static void main(String[] args) {
    DefaultClassExample example = new DefaultClassExample();
    example.display();
  }
}
‍

The display() method in DefaultClassExample is by default declared as default when no access modifier is assigned, making it accessible only within the classes present in the same package. By importing utilityClasses.DefaultClassExample in DemoClass, we cannot use the display() method, as the DemoClass is present in a different package, which is demoPackage.

‍

Whenever we try to access the display() method of the DefaultClassExample, since it's declared default, the compiler will give us a warning stating that:‍

'display()' is not public in 'utilityClasses.DefaultClassExample'. Cannot be accessed from outside package.
Article image

‍

Now, even if we try to run the file forcefully, it will give us a compile-time error in the terminal, stating:

java: display() is not public in utilityClasses.DefaultClassExample; cannot be accessed from outside package
Article image

‍

Now that we have seen we cannot access the default access modifier methods in different packages, if we create a new class, let's say Main, in the same utilityClasses package, add the same code as we did in the DemoClass and try to access the display() method of the DefaultClassExample, it will not give us any compile-time warnings and will work absolutely fine.

‍

The output in the Terminal will be : 

This is a default access method.
Article image

‍

📝 Summary of Access Modifiers : 
Article image

‍

🎉 Conclusion : 
Access modifiers are essential tools that provide control over the visibility and accessibility of classes, methods, and variables. By understanding and using access modifiers effectively, developers can create secure, modular, and maintainable applications. Mastery of access modifiers is key to writing robust and encapsulated programs.

<codeWithAryan>

Articles

Interview Experiences

Questions
DSA Sheets

Crash Course
System Design

Contest Solutions

Contact Us

Feature Releases

<codeWithAryan>
Search articles, questions or tech-blogs...

Access Modifiers

Topic Tags:
oop
system design
lld
‍‍🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

‍

Access modifiers 🔒 are fundamental in Object-Oriented Programming (OOP) 💻 as they control the visibility 👀 and accessibility of classes, methods, and variables in a program. They play a crucial role in encapsulation 🔐 by restricting or allowing access to certain parts of the code based on the specified modifier.

‍

Access modifiers prevent data members or functions of one class from tampering 🛑 with another class while restricting its access. It allows us to select which members can be accessed directly by outside functions and which are not. ✅

🏗️ Project Structure Overview:
Before diving into the types of access modifiers 🔒, let me walk you through the project structure that will be used to demonstrate their behavior.

Below is an image 🖼️ of the project setup:

‍

Project Structure:
Article image

• src/:

The root folder containing all the code files.

‍

• demoPackage/:

This package contains the DemoClass, which we will use to demonstrate the usage of different access modifiers in Java.

‍

• utilityClasses/:

This package contains the following classes:

PublicClassExample: Demonstrates the use of the public access modifier.
PrivateClassExample: Demonstrates the use of the private access modifier.
ProtectedClassExample: Demonstrates the use of the protected access modifier.
DefaultClassExample: Demonstrates the default (package-private) access modifier.
‍

• Main.java:

The main entry point for running the project.

‍

This structure allows us to clearly show how access modifiers work across:
1. Classes within the same package (demoPackage).

2. Classes in different packages (utilityClasses).

‍

Each of these access modifiers—public, private, protected, and default—will be explored using these classes and their relationships.

‍

Types of Access Modifiers in Java : 
Java provides four main types of access modifiers:

1. Public : 
🌐 Scope:
A public class, method, or variable is accessible from anywhere in the application, whether it’s within the same package or from a different package.

‍

🛠️ Usage:
The public modifier is used when you want the element (class, method, or variable) to be globally accessible. In the context of our project structure, the PublicClassExample in the utilityClasses package is accessible to other classes such as DemoClass in the demoPackage package.

‍

💻 Code Example: Demonstrating the Public Modifier
Here’s how we use the PublicClassExample to demonstrate the public modifier:

‍

PublicClassExample.java : 

• Located in the utilityClasses package:

‍

Java
package utilityClasses; // Declared in the utilityClasses package
public class PublicClassExample {
  public void display() {
    System.out.println("This is a public method in PublicClassExample.");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the public method:

Java
package demoPackage; // Declared in the demoPackage package
import utilityClasses.PublicClassExample; // Importing the PublicClassExample from another package

public class DemoClass {
  public static void main(String[] args) {
    // Creating an object of PublicClassExample
    PublicClassExample example = new PublicClassExample();

    // Calling the public method
    example.display(); // Accessible because 'display()' is public
  }
}
Article image

‍
🧩 Explanation : 
The display() method in PublicClassExample is declared as public, making it accessible across packages. By importing utilityClasses.PublicClassExample in DemoClass, we can use the method display() without any access restrictions.

‍

When you run the DemoClass, the output will be:

This is a public method.
Article image

‍
2. Private : 
🌐 Scope: 
A private class, method, or variable is not accessible from anywhere in the application. It is only accessible within the same class and not from outside the class, whether in the same package, a child class, or a different package. It will be accessible only within the same class.

🛠️ Usage: 
The private modifier is used when you want an element (class, method, or variable) to be accessible only within the same class. In the context of our project structure, the PrivateClassExample in the utilityClasses package is accessible to other classes because it is declared public. However, its methods won't be accessible to the DemoClass in the demoPackage package, as all the methods defined are private, which will result in a compile-time error.

‍

💻 Code Example: Demonstrating the Private Modifier
Here’s how we use the PrivateClassExample to demonstrate the public modifier:

‍

PrivateClassExample.java : 

Located in the utilityClasses package

Java
package utilityClasses;

public class PrivateClassExample {
  private String secret = "Hidden Message";
  private void displaySecret() {
    System.out.println(secret);
  }
  private void show() {
    displaySecret(); // Accessible within the same class
  }
}
Article image

‍

DemoClass.java : 

Located in the demoPackage package, demonstrating the usage of the private method

Java
package demoPackage;
import utilityClasses.PrivateClassExample;

public class DemoClass {
  public static void main(String[] args) {
    PrivateClassExample example = new PrivateClassExample();
    example.show();
  }
}
‍

The show() method in PrivateClassExample is declared as private, making it accessible only within the PrivateClassExample class. By importing utilityClasses.PrivateClassExample in DemoClass, we cannot use the show() method.

Whenever we try to access the show() method of the PrivateClassExample, since it's declared private, the compiler will give us a warning stating that 'show() has private access in utilityClasses.PrivateClassExample'.

‍

Even if you forcefully run the DemoClass, the output will be a compile-time error, which will be printed in the terminal:

java: show() has private access in utilityClasses.PrivateClassExample
Article image

‍

Article image

‍
3. Protected
🌐 Scope: 
The protected modifier allows access members within the same package and from subclasses in other packages. It offers more restricted access compared to public, but it is broader than private. By using protected, you enable controlled inheritance, allowing child classes to reuse and extend parent class functionality while keeping it hidden from unrelated classes.

‍

🛠️ Usage:
The protected modifier is used in scenarios where inheritance is a key design pattern. For example, you might define reusable methods or fields in a superclass that should only be accessed or overridden by its subclasses. It is commonly used in frameworks or libraries to expose specific functionality to derived classes while restricting general access.

‍

💻 Code Example: Demonstrating the Protected Modifier
Here’s how we use the ProtectedClassExample to demonstrate the public modifier:

‍

ProtectedClassExample.java : 

Located in the utilityClasses package

Java
package utilityClasses;

public class ProtectedClassExample {
  protected void display() {
    System.out.println("Hello from Parent class!");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the protected method:

Java
package demoPackage;
import utilityClasses.ProtectedClassExample;

public class DemoClass {
  public static void main(String[] args) {
    ProtectedClassExample example = new ProtectedClassExample();
    example.display();
  }
}
‍

The display() method in ProtectedClassExample is declared as protected when no access modifier is assigned, making it accessible only within the classes present in the same package and also in the classes present in different packages if they are a subclass of the ProtectedClassExample. By importing utilityClasses.ProtectedClassExample in DemoClass, we cannot use the display() method, as the DemoClass is not a subclass of the ProtectedClassExample.

‍

Whenever we try to access the display() method of the ProtectedClassExample, since it's declared protected, the compiler will give us a warning stating that:

'display()' has protected access in 'utilityClasses.ProtectedClassExample'
Article image

‍

In order to access the parent class method, we can make DemoClass extend the ProtectedClassExample class, thereby making DemoClass a subclass of ProtectedClassExample.

This will allow us to access the display() method of the ProtectedClassExample.

‍

Code : 

Java
package demoPackage;
import utilityClasses.ProtectedClassExample;

public class DemoClass extends ProtectedClassExample {
  public static void main(String[] args) {
    ProtectedClassExample example = new ProtectedClassExample();
    example.display();
  }
}
‍

The output in the Terminal will be : 

Hello from Parent class!
Article image

‍
4. Default (Package-Private)
🌐Scope: 
When no access modifier is specified, the default (package-private) access modifier is applied. Members with this access modifier are accessible only within the same package but not from outside it. This ensures that the functionality is available for closely related classes within the package while being hidden from other parts of the application.

🛠️ Usage: 
Default access is used when you want to limit access to package-level components. It is ideal for internal helper classes, methods, or variables that do not need to be exposed to external packages. This access level supports modularity by grouping related classes and ensuring that their interactions remain encapsulated within the package.

💻 Code Example: Demonstrating the Default Modifier : 
Here’s how we use the DefaultClassExample to demonstrate the public modifier:

‍

DefaultClassExample.java : 

• Located in the utilityClasses package:

Java
package utilityClasses;

public class DefaultClassExample {
  void display() {
    System.out.println("This is a default access method.");
  }
}
Article image

‍

DemoClass.java : 

• Located in the demoPackage package, demonstrating the usage of the default method:

Java
package demoPackage;
import utilityClasses.DefaultClassExample;

public class DemoClass {
  public static void main(String[] args) {
    DefaultClassExample example = new DefaultClassExample();
    example.display();
  }
}
‍

The display() method in DefaultClassExample is by default declared as default when no access modifier is assigned, making it accessible only within the classes present in the same package. By importing utilityClasses.DefaultClassExample in DemoClass, we cannot use the display() method, as the DemoClass is present in a different package, which is demoPackage.

‍

Whenever we try to access the display() method of the DefaultClassExample, since it's declared default, the compiler will give us a warning stating that:‍

'display()' is not public in 'utilityClasses.DefaultClassExample'. Cannot be accessed from outside package.
Article image

‍

Now, even if we try to run the file forcefully, it will give us a compile-time error in the terminal, stating:

java: display() is not public in utilityClasses.DefaultClassExample; cannot be accessed from outside package
Article image

‍

Now that we have seen we cannot access the default access modifier methods in different packages, if we create a new class, let's say Main, in the same utilityClasses package, add the same code as we did in the DemoClass and try to access the display() method of the DefaultClassExample, it will not give us any compile-time warnings and will work absolutely fine.

‍

The output in the Terminal will be : 

This is a default access method.
Article image

‍

📝 Summary of Access Modifiers : 
Article image

‍

🎉 Conclusion : 
Access modifiers are essential tools that provide control over the visibility and accessibility of classes, methods, and variables. By understanding and using access modifiers effectively, developers can create secure, modular, and maintainable applications. Mastery of access modifiers is key to writing robust and encapsulated programs.

