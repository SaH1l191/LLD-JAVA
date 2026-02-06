# Abstract Factory Pattern

**Topic Tags:** System Design, LLD

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

## Managing Families of Related Objects with Ease

### 1. The Problem: Managing Different Car Brands 🚗

Imagine you're building a car dealership application that needs to create cars. Each car is a different type and comes from a different manufacturer, like Honda, Toyota, or BMW. Now, let's say you need to create multiple car brands dynamically based on user input or some configuration.

You might think, "I'll just create the car and move on," but as the system grows and the number of car brands increases, the code starts to get messy. You'll find yourself repeating the logic of creating each type of car in multiple places, making the code hard to maintain.

### 2. Solving the Problem with the Factory Method 🔧

Let's start by using the Factory Method pattern to solve the problem. In the Factory Method, we define a method for creating objects but let the subclasses decide which type of object to instantiate.

Here's how we might do this for car brands:

```java
// Vehicle.java - Common Interface
public interface Vehicle {
  void start();
  void stop();
}

// Concrete Classes for Car Brands
public class Honda implements Vehicle {
  public void start() {
    System.out.println("Honda Car is starting");
  }
  public void stop() {
    System.out.println("Honda Car is stopping");
  }
}

public class Toyota implements Vehicle {
  public void start() {
    System.out.println("Toyota Car is starting");
  }
  public void stop() {
    System.out.println("Toyota Car is stopping");
  }
}

public class BMW implements Vehicle {
  public void start() {
    System.out.println("BMW Car is starting");
  }
  public void stop() {
    System.out.println("BMW Car is stopping");
  }
}

// Factory Method to Create Vehicles
public class CarFactory {
  public Vehicle createVehicle(String brand) {
    if (brand.equals("Honda")) {
      return new Honda();
    } else if (brand.equals("Toyota")) {
      return new Toyota();
    } else if (brand.equals("BMW")) {
      return new BMW();
    } else {
      throw new IllegalArgumentException("Unknown car brand");
    }
  }
}

// Main Method
public class Main {
  public static void main(String[] args) {
    CarFactory factory = new CarFactory();
    Vehicle vehicle = factory.createVehicle("Honda");
    vehicle.start();
    vehicle.stop();
  }
}
```

### 3. The Interviewer's Follow-up Questions: Can We Improve This? 🤔

An interviewer might ask:

- What if we need to add more car brands later?
- Is there a better way to manage the growing number of car brands and avoid repeating the createVehicle logic?

As you scale the application, the Factory Method becomes cumbersome. You have to go back to the CarFactory and modify the createVehicle method every time you want to add a new car brand. This leads to code duplication and hard-to-maintain code.

### 4. The Ugly Truth: Our Code Needs Restructuring 😓

Let's say we decide to add a few more brands like Ford and Chevrolet. If we keep adding more if statements inside the createVehicle method, it starts to look ugly and hard to maintain:

```java
public Vehicle createVehicle(String brand) {
  if (brand.equals("Honda")) {
    return new Honda();
  } else if (brand.equals("Toyota")) {
    return new Toyota();
  } else if (brand.equals("BMW")) {
    return new BMW();
  } else if (brand.equals("Ford")) {
    return new Ford();
  } else if (brand.equals("Chevrolet")) {
    return new Chevrolet();
  } else {
    throw new IllegalArgumentException("Unknown car brand");
  }
}
```

This approach is difficult to extend. Every time a new car brand is introduced, you must modify this method, violating the Open-Closed Principle (open for extension, closed for modification).

### 5. Introducing Our Savior: The Abstract Factory Pattern 💡

To solve this, we introduce the Abstract Factory Design Pattern. Unlike the Factory Method, the Abstract Factory allows us to handle the creation of related objects (like different car brands) without specifying their concrete classes directly.

The Abstract Factory helps us manage families of related objects. Instead of adding new conditions to the createVehicle method every time a new car brand is introduced, we can create separate factories for each car brand that encapsulate their creation.

#### Why is it Called the "Abstract Factory"? 🤔

The name "Abstract Factory" comes from the concept of abstraction in programming. In simple terms, abstraction is the process of hiding the complex details of a system and exposing only the necessary parts.

In the Abstract Factory pattern, the "Abstract" part refers to the fact that the client code doesn’t know about the specific classes of objects being created. Instead of directly interacting with the concrete classes (like Honda, Toyota, or BMW), the client only knows about the factory interfaces (like VehicleFactory), which provide a method for creating objects without exposing the actual classes behind them.

Think of it like ordering a car from a dealership. As a customer, you don’t need to know the intricate details of how each car is built or which parts are used. You just choose the type of car you want (Honda, Toyota, BMW), and the factory (dealership) handles the rest. This is the abstraction at play: you only deal with the abstract factory interface, not the specific car details.

#### Why Is This Helpful? 🤩

This level of abstraction brings several benefits:

• Flexibility:

You can add new products (car brands) by simply adding new factories. The client code doesn't need to be modified.

• Maintainability:

Changes to the creation process (like how a specific car is built) only need to happen inside the concrete factory, leaving the client code untouched.

• Decoupling:

The client doesn’t need to know the specifics of the objects it uses. It simply relies on the abstract factory, making the system more modular and easier to change.

In short, the Abstract Factory provides an easy way to create families of related objects, and abstracts the creation process, making your code cleaner, more flexible, and easier to maintain.

### 6. Solving the Problem Using Abstract Factory 🛠️

Let’s refactor the code to use the Abstract Factory pattern. We’ll define an Abstract Factory interface and create different concrete factories for each car brand.

```java
// Vehicle.java - Common Interface
public interface Vehicle {
  void start();
  void stop();
}

// Concrete Classes for Car Brands
public class Honda implements Vehicle {
  public void start() {
    System.out.println("Honda Car is starting");
  }
  public void stop() {
    System.out.println("Honda Car is stopping");
  }
}

public class Toyota implements Vehicle {
  public void start() {
    System.out.println("Toyota Car is starting");
  }
  public void stop() {
    System.out.println("Toyota Car is stopping");
  }
}

public class BMW implements Vehicle {
  public void start() {
    System.out.println("BMW Car is starting");
  }
  public void stop() {
    System.out.println("BMW Car is stopping");
  }
}

// Abstract Factory Interface
public interface VehicleFactory {
  Vehicle createVehicle();
}

// Concrete Factories for Each Car Brand
public class HondaFactory implements VehicleFactory {
  public Vehicle createVehicle() {
    return new Honda();
  }
}

public class ToyotaFactory implements VehicleFactory {
  public Vehicle createVehicle() {
    return new Toyota();
  }
}

public class BMWFactory implements VehicleFactory {
  public Vehicle createVehicle() {
    return new BMW();
  }
}

// Client Code
public class Main {
  public static void main(String[] args) {
    VehicleFactory hondaFactory = new HondaFactory();
    Vehicle honda = hondaFactory.createVehicle();
    honda.start();
    honda.stop();
    
    VehicleFactory toyotaFactory = new ToyotaFactory();
    Vehicle toyota = toyotaFactory.createVehicle();
    toyota.start();
    toyota.stop();
  }
}
```

### 7. Solving the Follow-up Questions with the Abstract Factory 🔍

• What if we need to add more car brands later?

With the Abstract Factory, adding a new car brand is simple. You only need to create a new concrete factory for the new car brand and implement the createVehicle method. No need to modify the client code or touch the existing factories.

‍

• How does the Abstract Factory handle the complexity of adding multiple related products?
The Abstract Factory helps you manage families of related products (like cars, trucks, or even different types of furniture) by grouping related creation logic into separate factories. This ensures that all objects created within a family are consistent and follow a unified design.

‍

8. Advantages of the Abstract Factory Pattern 🚀
• Easier to Extend:

Adding new car brands (or any other related products) is as simple as adding a new concrete factory. You don’t need to touch the client code or the existing factories.

‍

• Cleaner and More Maintainable:

Instead of modifying a large createVehicle method every time you need to add a new product, you encapsulate the logic in separate factory classes, making the system easier to maintain and extend.

‍

• Consistency:

All objects in a family are created in a consistent manner. Whether it’s creating vehicles or furniture, the Abstract Factory ensures that all products created by a particular factory are related and compatible.

‍

9. Real-life Use Cases and Examples 🏢
Here are a few places where the Abstract Factory pattern is commonly used:

• Cross-Platform UI Libraries:

If you’re developing a cross-platform application, you can use an Abstract Factory to create platform-specific UI elements (buttons, windows, textboxes) for Windows, Mac, or Android, ensuring consistency across platforms.

‍

• Database Connections:

In a multi-database system, you can use an Abstract Factory to create database connections for different databases like MySQL, PostgreSQL, or MongoDB.

‍

• Game Development:

In a game, you might have different families of objects like characters, weapons, and environments. The Abstract Factory ensures that all elements in a particular family (e.g., all weapons in a medieval game) are consistent.

‍

Factory Method vs. Abstract Factory
1. Purpose: 

○ Factory Method: Creates one type of object.

○ Abstract Factory: Creates families of related objects.

‍

2. Scope: 

○ Factory Method: Focuses on creating a single product.

○ Abstract Factory: Creates multiple related products.

‍

3. Abstraction Level: 

○ Factory Method: Deals with one product type at a time.

○ Abstract Factory: Deals with groups of related products.

‍

4. Example: 

○ Factory Method: A CarFactory creates one type of car.

○ Abstract Factory: A VehicleFactory creates cars, trucks, and bikes of the same brand.

‍

5. Flexibility: 

○ Factory Method: Adding new products requires changing the factory.

○ Abstract Factory: Adding new families doesn't affect existing code.

‍

6. Use Case: 

○ Factory Method: When you need to create a single object (e.g., one car model).

○ Abstract Factory: When you need to create related objects (e.g., different vehicles from the same brand).

‍

🎉 Conclusion
The Abstract Factory Design Pattern provides a powerful way to manage the creation of related objects without specifying their concrete classes. It makes your system more scalable, maintainable, and easier to extend. Unlike the Factory Method, which works well for single products, the Abstract Factory is designed to handle families of related products with ease, making it an essential pattern in complex systems.




Simplification : 


🧠 First: One-line intuition

Factory Method solves “which object?”
Abstract Factory solves “which FAMILY of objects?”

1️⃣ What problem still exists with Factory Method?

You already used CarFactory with if-else:

public Vehicle createVehicle(String brand) {
    if (brand.equals("Honda")) return new Honda();
    if (brand.equals("Toyota")) return new Toyota();
    if (brand.equals("BMW")) return new BMW();
}


This fixes scattered new, but creates new problems.

❌ Problem 1: Open–Closed Principle violation
OCP says:

Open for extension, closed for modification

Reality with Factory Method:

Every new brand:

else if (brand.equals("Ford")) return new Ford();


👉 You modify existing code
👉 Risk of breaking existing behavior
👉 Large if-else grows endlessly

⚠️ Factory Method centralizes creation, but is NOT closed for modification

❌ Problem 2: Single Responsibility Principle violation
CarFactory responsibility:

❌ Decide which brand

❌ Know all brands

❌ Instantiate all vehicles

This class has too many reasons to change:

New brand

Constructor change

Brand-specific logic

👉 God Factory problem

❌ Problem 3: No concept of “family”
Today:
Honda
Toyota
BMW

Tomorrow:

Each brand has:

Car

Bike

Truck

Without Abstract Factory:

new HondaCar()
new ToyotaBike()
new BMWTruck()


💥 You can accidentally mix:

HondaCar + BMWBike


👉 Inconsistent product family

❌ Problem 4: Conditional explosion

Now imagine:

createVehicle(brand, type)

if (brand == "Honda" && type == "Car") ...
if (brand == "Honda" && type == "Bike") ...
if (brand == "Toyota" && type == "Car") ...


😵‍💫 combinatorial explosion

2️⃣ What Abstract Factory actually solves

Abstract Factory introduces one more abstraction layer.

✅ Key idea

Move brand decision OUT of logic and INTO polymorphism

No if-else
No switch
No string comparisons

3️⃣ How Abstract Factory fixes each problem
✅ Problem 1 solved: OCP
Add new brand = add new factory
class TeslaFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Tesla();
    }
}


✔ No existing code touched
✔ No if-else modified

OCP satisfied

✅ Problem 2 solved: SRP

Each factory:

HondaFactory → Honda only
ToyotaFactory → Toyota only
BMWFactory → BMW only


Each class has ONE reason to change.

✅ Problem 3 solved: Product family consistency
Abstract Factory (real power version)
interface VehicleFactory {
    Car createCar();
    Bike createBike();
}

HondaFactory
createCar() → HondaCar
createBike() → HondaBike


👉 You cannot mix brands accidentally

This is the real definition of Abstract Factory:

Creating related objects without specifying their concrete classes

✅ Problem 4 solved: Conditional explosion

No more:

if (brand.equals("Honda"))


Instead:

VehicleFactory factory = new HondaFactory();


Brand decision happens once, not everywhere.

4️⃣ What principles Abstract Factory enforces
Principle	How it helps
OCP	Add new families without modifying code
SRP	Each factory handles one brand
DIP	Client depends on VehicleFactory, not Honda
Encapsulation	Creation logic hidden
Consistency	Product families stay compatible
5️⃣ Why “Factory Method” was not enough
Factory Method	Abstract Factory
One product	Family of products
Central if-else	Polymorphism
Modifies factory	Adds new factory
Weak OCP	Strong OCP
6️⃣ Interview-ready explanation (short)

Factory Method centralizes object creation but violates OCP as new products require modifying the factory. Abstract Factory eliminates conditionals by using polymorphism, enabling the system to create consistent families of related objects while fully adhering to OCP and SRP.

7️⃣ One mental rule (VERY IMPORTANT)

If you see if-else on type → Factory Method
If you see if-else on family → Abstract Factory

8️⃣ When NOT to use Abstract Factory 🚫

Only 1 product

Very few variants

Over-engineering risk
