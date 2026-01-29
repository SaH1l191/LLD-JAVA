# Decorator Design Pattern

**Topic Tags:**
- System Design
- LLD

🐈‍⬛ **Github Codes Link:** https://github.com/aryan-0077/CWA-LowLevelDesignCode

## Problem Statement: Adding Features Dynamically 🎨

Imagine you're developing a coffee shop ordering system. Your goal is to allow customers to order different types of coffee with various additional toppings like milk, sugar, whipped cream, and caramel syrup.

Each coffee has a base price, and each topping adds extra cost and functionality. For example:

- **Espresso** - $2.00 (base coffee)
- **Latte** - $3.00 (base coffee with milk)
- **Cappuccino** - $3.50 (base coffee with milk and foam)
- **Mocha** - $4.00 (base coffee with chocolate syrup)

Additional toppings:
- **Milk** - +$0.50
- **Sugar** - +$0.25
- **Whipped Cream** - +$1.00
- **Caramel Syrup** - +$0.75

### The Problem

Customers want to customize their coffee orders with multiple combinations of toppings. Creating a separate class for each possible combination (EspressoWithMilkAndSugar, LatteWithWhippedCreamAndCaramel, etc.) would lead to a class explosion and make the system impossible to maintain.

### The Challenge

How can you create a flexible system that allows adding new features (toppings) to objects dynamically without modifying existing code or creating an exponential number of classes?

## Solving It the Traditional Way: A Messy Solution 🛠️

Let's look at a naive approach to solving this problem:

### Coffee.java

```java
public abstract class Coffee {
    public abstract String getDescription();
    public abstract double getCost();
}
```

### Espresso.java

```java
public class Espresso extends Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }
    
    @Override
    public double getCost() {
        return 2.00;
    }
}
```

### Latte.java

```java
public class Latte extends Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }
    
    @Override
    public double getCost() {
        return 3.00;
    }
}
```

### EspressoWithMilk.java

```java
public class EspressoWithMilk extends Coffee {
    @Override
    public String getDescription() {
        return "Espresso with Milk";
    }
    
    @Override
    public double getCost() {
        return 2.50; // Espresso + Milk
    }
}
```

### CoffeeShop.java

```java
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee espresso = new Espresso();
        Coffee latte = new Latte();
        Coffee espressoWithMilk = new EspressoWithMilk();
        
        System.out.println(espresso.getDescription() + ": $" + espresso.getCost());
        System.out.println(latte.getDescription() + ": $" + latte.getCost());
        System.out.println(espressoWithMilk.getDescription() + ": $" + espressoWithMilk.getCost());
    }
}
```

### The Problems with This Approach:

1. **Class Explosion**: For each combination of coffee and toppings, we need a new class.
2. **Code Duplication**: Similar logic is repeated across multiple classes.
3. **Poor Maintainability**: Adding a new topping requires creating multiple new classes.
4. **Inflexibility**: Cannot add toppings at runtime.

## Interviewer's Follow-up Questions: Can We Improve the Code? 🤔

An interviewer might ask:

1. What if we need to add a new topping like vanilla syrup?
2. How can we support multiple toppings on the same coffee?
3. Can we add or remove toppings dynamically during runtime?
4. How can we calculate the total cost without hardcoding combinations?

These questions highlight the need for a better design that allows dynamic addition of features.

## Ugly Code: When We Realize the Code Needs Restructuring 🤦‍♂️

Let's say we try to handle multiple toppings with inheritance:

```java
public class EspressoWithMilkAndSugar extends Coffee {
    @Override
    public String getDescription() {
        return "Espresso with Milk and Sugar";
    }
    
    @Override
    public double getCost() {
        return 2.75; // Espresso + Milk + Sugar
    }
}

public class EspressoWithMilkAndSugarAndWhippedCream extends Coffee {
    @Override
    public String getDescription() {
        return "Espresso with Milk, Sugar, and Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return 3.75; // Espresso + Milk + Sugar + Whipped Cream
    }
}

public class LatteWithCaramelAndWhippedCream extends Coffee {
    @Override
    public String getDescription() {
        return "Latte with Caramel Syrup and Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return 4.75; // Latte + Caramel + Whipped Cream
    }
}
```

### Why is This Code Problematic?

1. **Exponential Growth**: The number of classes grows exponentially with each new topping.
2. **Maintenance Nightmare**: Changing the price of a topping requires updating multiple classes.
3. **Rigid Structure**: Cannot create custom combinations at runtime.
4. **Violation of Open/Closed Principle**: Adding new features requires modifying existing code.

## The Savior: Decorator Design Pattern 🎭

The Decorator Design Pattern is the perfect solution for this problem. It allows adding new functionality to objects dynamically without altering their structure. This pattern creates a decorator class that wraps the original class and provides additional functionality while keeping the class interface unchanged.

In our coffee shop example, decorators represent toppings that can be added to any coffee dynamically.

## How the Decorator Pattern Works 🔧

1. **Component**: Define the common interface for both concrete objects and decorators.
2. **Concrete Component**: Implement the base object (e.g., Espresso, Latte).
3. **Decorator**: Maintain a reference to a component object and define an interface that conforms to the component's interface.
4. **Concrete Decorator**: Add responsibilities to the component (e.g., Milk, Sugar, Whipped Cream).

## Solving the Problem with Decorator Design Pattern 🌐

### Step 1: Define the Coffee Component Interface

This interface provides the contract for all coffee objects and decorators:

### Coffee.java

```java
// Coffee.java - Component interface
public interface Coffee {
    String getDescription();
    double getCost();
}
```

### Step 2: Create Concrete Coffee Components

These are the base coffee types that can be decorated:

### SimpleCoffee.java

```java
// SimpleCoffee.java - Concrete component
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double getCost() {
        return 1.50;
    }
}
```

### Espresso.java

```java
// Espresso.java - Concrete component
public class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }
    
    @Override
    public double getCost() {
        return 2.00;
    }
}
```

### Latte.java

```java
// Latte.java - Concrete component
public class Latte implements Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }
    
    @Override
    public double getCost() {
        return 3.00;
    }
}
```

### Step 3: Create the Abstract Decorator Class

This class implements the Coffee interface and holds a reference to a Coffee object:

### CoffeeDecorator.java

```java
// CoffeeDecorator.java - Abstract decorator
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
```

### Step 4: Create Concrete Decorators (Toppings)

These are the specific toppings that can be added to any coffee:

### MilkDecorator.java

```java
// MilkDecorator.java - Concrete decorator
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.50;
    }
}
```

### SugarDecorator.java

```java
// SugarDecorator.java - Concrete decorator
public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.25;
    }
}
```

### WhippedCreamDecorator.java

```java
// WhippedCreamDecorator.java - Concrete decorator
public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 1.00;
    }
}
```

### CaramelSyrupDecorator.java

```java
// CaramelSyrupDecorator.java - Concrete decorator
public class CaramelSyrupDecorator extends CoffeeDecorator {
    public CaramelSyrupDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Caramel Syrup";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.75;
    }
}
```

### Step 5: Use the Decorator Pattern in the Coffee Shop

Now we can create various combinations dynamically:

### CoffeeShop.java

```java
// CoffeeShop.java
public class CoffeeShop {
    public static void main(String[] args) {
        // Simple coffee
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + ": $" + simpleCoffee.getCost());
        
        // Espresso with milk and sugar
        Coffee espressoWithMilkAndSugar = new SugarDecorator(
            new MilkDecorator(new Espresso())
        );
        System.out.println(espressoWithMilkAndSugar.getDescription() + ": $" + espressoWithMilkAndSugar.getCost());
        
        // Latte with whipped cream and caramel syrup
        Coffee fancyLatte = new CaramelSyrupDecorator(
            new WhippedCreamDecorator(new Latte())
        );
        System.out.println(fancyLatte.getDescription() + ": $" + fancyLatte.getCost());
        
        // Complex order: Espresso with all toppings
        Coffee everythingEspresso = new CaramelSyrupDecorator(
            new WhippedCreamDecorator(
                new SugarDecorator(
                    new MilkDecorator(new Espresso())
                )
            )
        );
        System.out.println(everythingEspresso.getDescription() + ": $" + everythingEspresso.getCost());
    }
}
```

**Output:**
```
Simple Coffee: $1.5
Espresso, Milk, Sugar: $2.75
Latte, Whipped Cream, Caramel Syrup: $4.75
Espresso, Milk, Sugar, Whipped Cream, Caramel Syrup: $4.5
```

## Advantages of Using the Decorator Design Pattern 🏆

### 1. Flexibility

Add or remove features dynamically at runtime without modifying existing code.

### 2. Scalability

Easily add new decorators (toppings) without changing the component classes.

### 3. Single Responsibility

Each decorator has a single responsibility, making the code more maintainable.

### 4. Composition over Inheritance

Uses composition to extend functionality, avoiding class explosion.

### 5. Open/Closed Principle

Open for extension (new decorators) but closed for modification (existing components).

## Real-life Use Cases of the Decorator Pattern 🌎

### 1. GUI Frameworks

Adding scrollbars, borders, or behaviors to UI components dynamically.

### 2. I/O Streams

Java's I/O classes use decorators to add functionality like buffering, compression, or encryption.

### 3. Web Development

Middleware in web frameworks that adds features like authentication, logging, or caching.

### 4. Text Processing

Adding features like spell checking, formatting, or encryption to text editors.

### 5. Gaming

Adding power-ups, abilities, or equipment to game characters dynamically.

## Conclusion 🎯

The Decorator Design Pattern provides an elegant solution for adding functionality to objects dynamically without using inheritance. In our coffee shop example, it allows us to create unlimited combinations of coffee and toppings while keeping the code clean, maintainable, and extensible.

By wrapping objects with decorators, we can add features in a flexible and reusable way, making the system more adaptable to changing requirements. The Decorator Pattern is an essential tool for building systems where objects need to be enhanced with additional behaviors dynamically. 🌟