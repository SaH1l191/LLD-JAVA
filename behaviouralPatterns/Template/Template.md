# Template Design Pattern

**Topic Tags:** System Design, LLD
🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode
‍

Hey there, fellow coder! 😊

Today, I want to share a fun journey into the world of design patterns—a special one called the Template Design Pattern. Imagine you're following your favorite cookie recipe 🍪: the overall steps are always the same (mix ingredients, bake, cool, decorate), but the details (like the type of frosting) can vary. That’s exactly what the template design pattern does in our code—it defines a “template” or blueprint for an algorithm while letting subclasses fill in the details. Let’s dive in together!

‍

Once Upon a Code Adventure
Let’s set the stage with a simple, everyday scenario: making beverages. Whether it’s coffee ☕ or tea 🍵, the process is similar—you boil water, brew the drink, pour it into a cup, and add your favorite condiments. Initially, you might write separate code for coffee and tea that almost looks identical except for a few steps. Sound familiar? Let’s see how that might look.

‍

The Traditional (and Messy) Way
Imagine you’re coding this without any fancy design patterns. You might end up with something like this:

‍

Java
// Making Coffee the old-fashioned way
class Coffee {
  public void prepare() {
    boilWater();
    brewCoffee();
    pourInCup();
    addSugarAndMilk();
  }
  private void boilWater() {
    System.out.println("Boiling water...");
  }
  private void brewCoffee() {
    System.out.println("Brewing coffee...");
  }
  private void pourInCup() {
    System.out.println("Pouring into cup...");
  }
  private void addSugarAndMilk() {
    System.out.println("Adding sugar and milk...");
  }
}

// Making Tea the old-fashioned way
class Tea {
  public void prepare() {
    boilWater();
    steepTeaBag();
    pourInCup();
    addLemon();
  }
  private void boilWater() {
    System.out.println("Boiling water...");
  }
  private void steepTeaBag() {
    System.out.println("Steeping tea bag...");
  }
  private void pourInCup() {
    System.out.println("Pouring into cup...");
  }
  private void addLemon() {
    System.out.println("Adding lemon...");
  }
}

public class TraditionalBeverageDemo {
  public static void main(String[] args) {
    Coffee coffee = new Coffee();
    Tea tea = new Tea();
    System.out.println("Making coffee...");
    coffee.prepare();
    System.out.println("nMaking tea...");
    tea.prepare();
  }
}
‍

Now, while this works, notice how boiling water and pouring into a cup are repeated in both classes. An interviewer might ask, “How would you reduce this duplication?” And that’s when you start to wonder: isn’t there a cleaner way?

‍

🤔Why “Template”?
It’s called the Template Design Pattern because it provides a fixed “template” for an algorithm. The steps are defined in a base class, but the details (like brewing coffee vs. steeping tea) are left for the subclasses. This ensures that the overall process remains consistent while allowing flexibility for variations—just like following a cookie recipe and swapping out ingredients!

‍

The Interviewer’s Challenge
Imagine you’re in an interview, and the interviewer dives into your code:

Interviewer: “I see you have a lot of duplicated code. How would you refactor this so it’s more maintainable and less ugly?”

You start to explain that you can use the Template Design Pattern to abstract the common steps. Let’s check out what that “ugly” code looks like before we clean it up further.

‍

The Ugly Truth: Messy Code Duplication
Here’s a condensed version of our messy approach:

‍

Java
class Coffee {
  public void prepare() {
    System.out.println("Boiling water...");
    System.out.println("Brewing coffee...");
    System.out.println("Pouring into cup...");
    System.out.println("Adding sugar and milk...");
  }
}

class Tea {
  public void prepare() {
    System.out.println("Boiling water...");
    System.out.println("Steeping tea bag...");
    System.out.println("Pouring into cup...");
    System.out.println("Adding lemon...");
  }
}
‍

See how both classes repeat the “boiling water” and “pouring into cup” steps? Yikes! 😬

‍

Enter Our Saviour: The Template Method Pattern
Time to bring in our hero—the Template Design Pattern! We can create an abstract class that holds the common algorithm steps, and then let subclasses override the specific parts. Check out this cleaner approach:

‍

Java
// Our abstract template that defines the skeleton of beverage preparation
abstract class Beverage {
  // The template method - makes sure the algorithm steps are followed
  final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    addCondiments();
  }
  // Common methods
  void boilWater() {
    System.out.println("Boiling water...");
  }
  void pourInCup() {
    System.out.println("Pouring into cup...");
  }
  // Steps to be customized by subclasses
  abstract void brew();
  abstract void addCondiments();
}

// Concrete implementation for Coffee
class CoffeeBeverage extends Beverage {
  @Override
  void brew() {
    System.out.println("Brewing coffee...");
  }
  @Override
  void addCondiments() {
    System.out.println("Adding sugar and milk...");
  }
}

// Concrete implementation for Tea
class TeaBeverage extends Beverage {
  @Override
  void brew() {
    System.out.println("Steeping tea bag...");
  }
  @Override
  void addCondiments() {
    System.out.println("Adding lemon...");
  }
}

public class BeverageTemplateDemo {
  public static void main(String[] args) {
    Beverage coffee = new CoffeeBeverage();
    Beverage tea = new TeaBeverage();
    System.out.println("Making coffee...");
    coffee.prepareRecipe();
    System.out.println("nMaking tea...");
    tea.prepareRecipe();
  }
}
‍

Notice how much cleaner and organized this code is? Now, the common steps (boiling water and pouring into a cup) are written just once in the Beverage class. The subclasses only worry about their unique steps. Neat, isn’t it? 😍.

‍

Article image

‍

This diagram shows that both CoffeeBeverage and TeaBeverage inherit from the abstract class Beverage, which defines the template method prepareRecipe() along with common methods like boilWater() and pourInCup(). The subclasses implement their own versions of the brew() and addCondiments() methods.

‍

Tackling Follow-Ups with Our New Approach
Suppose the interviewer then asks: “What if the customer sometimes doesn’t want any condiments? How would you handle that?” Easy-peasy! We can add a hook method to allow optional steps.

‍

Java
abstract class BeverageWithHook {
  // The template method with a hook
  final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    // Only add condiments if the customer wants them
    if (customerWantsCondiments()) {
      addCondiments();
    }
  }
  void boilWater() {
    System.out.println("Boiling water...");
  }
  void pourInCup() {
    System.out.println("Pouring into cup...");
  }
  abstract void brew();
  abstract void addCondiments();
  // Hook method with default behavior
  boolean customerWantsCondiments() {
    return true;
  }
}

class CustomCoffee extends BeverageWithHook {
  @Override
  void brew() {
    System.out.println("Brewing coffee...");
  }
  @Override
  void addCondiments() {
    System.out.println("Adding sugar and milk...");
  }
  // Suppose this customer doesn't want condiments
  @Override
  boolean customerWantsCondiments() {
    return false;
  }
}
public class BeverageWithHookDemo {
  public static void main(String[] args) {
    BeverageWithHook coffee = new CustomCoffee();
    System.out.println("Making custom coffee...");
    coffee.prepareRecipe();
  }
}
‍

This follow-up shows how flexible and advantageous the Template Design Pattern is. Not only do we eliminate duplicate code, but we also allow for easy modifications and optional steps without changing the algorithm’s structure.

‍

🌟 Top 5 Perks of the Template Design Pattern 🌟
🙅‍♂️ Reduces Code Duplication:

The pattern centralizes common algorithm steps in the base class, so you don’t have to write the same code over and over in different subclasses.

‍

✅ Enforces Consistency:

By defining the overall structure of the algorithm in one place, all subclasses follow the same process, ensuring uniformity across implementations.

‍

🛠 Improves Maintainability:

Changes to shared logic only need to be made in the template method, making your code easier to update and less prone to errors.

‍

♻️ Enhances Code Reusability:

Common functionality is implemented once in the abstract class and reused by multiple subclasses, promoting a DRY (Don't Repeat Yourself) codebase.

‍

🌟 Provides Flexibility:

Subclasses can override specific parts of the algorithm to customize behavior without altering the overall structure, allowing for easy modifications and extensions.

‍

Real-Life Magic with Template Patterns 🌟
You might wonder, “Where else can I use this pattern?” Here are some day-to-day scenarios:

• Cooking Recipes 🍳:

Just like our beverage example, many recipes follow a standard process with customizable ingredients.

‍

• Game Development 🎮:

Think of a game loop where the framework handles common tasks (updating, rendering) while letting you override specific game mechanics.

‍

• Document Processing 📄:

Parsing various types of documents (like CSV, XML, JSON) can follow a common structure with differences in the parsing logic.

‍

• User Interface Rendering 🖥️:

A base UI component might define the general layout, but specific components can override how they render content.

‍

Wrapping Up Our Journey
The Template Design Pattern is like that secret recipe 📝 you always keep in your notebook. It provides a reliable blueprint for algorithms, ensuring consistency while still allowing for flexibility. By using this pattern, you can write cleaner, more maintainable code and impress those interviewers with your deep understanding of design principles! 🚀

I hope this little adventure into templates has inspired you to try out this pattern in your own projects. Keep coding, keep experimenting, and most importantly, have fun along the way! 😄 Happy coding!

