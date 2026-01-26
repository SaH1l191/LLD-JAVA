# Memento Design Pattern

**Topic Tags:** System Design, LLD
🐈‍⬛ Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode
‍

Hey there, fellow coder! 😊👋

Today, I want to take you on an exciting journey into the world of the Memento Design Pattern in Java. Imagine you're using a text editor where you often make changes, but sometimes you need to hit "undo" to go back to a previous version. Instead of having your text editor cluttered with backup logic, wouldn’t it be awesome if you could simply save a “snapshot” of your text at any moment and restore it when needed? That’s exactly what the Memento Pattern does—it captures and externalizes an object’s internal state so that the object can be restored later without violating encapsulation. Let’s dive in! 🚀

‍

The Memory Keeper: What’s in a Name? 🧠
The pattern is called Memento because it acts like a memory or reminder. In our case, it remembers the state of our text editor at a given moment. Just like you might keep a photo album to remember a special day, a memento holds the state of an object so you can go back to it later. How cool is that? 😎

‍

A Scenario: The Text Editor with Undo Feature ✍️🔙
Imagine our text editor where a user is typing and making changes. The user wants to be able to undo mistakes and revert to a previous version of the text. Traditionally, you might try to handle this by manually storing previous states within the text editor class—but as the application grows, this approach gets messy and hard to maintain. Let’s see what that looks like.

‍

The Traditional Way: The Messy Backup Approach 😬
In the traditional method, you might try something like this:

‍

Java
class TextEditorTraditional {
  private String text;
  public TextEditorTraditional(String text) {
    this.text = text;
  }
  public void setText(String text) {
    // Directly update the text
    this.text = text;
  }
  // A makeshift "undo" that takes a previous state manually
  public void undo(String previousState) {
    this.text = previousState;
  }
  public void showText() {
    System.out.println("Current text: " + text);
  }
}

public class TraditionalEditorDemo {
  public static void main(String[] args) {
    TextEditorTraditional editor = new TextEditorTraditional("Hello");
    editor.showText(); // Output: Hello
    // User makes a change
    String backup = "Hello"; // Manually keeping the backup 😅
    editor.setText("Hello, World!");
    editor.showText(); // Output: Hello, World!
    // Undo the change by manually restoring the backup
    editor.undo(backup);
    editor.showText(); // Output: Hello
  }
}
‍

Explanation:

• We try to simulate an undo by manually saving the previous state in a variable.

• Every time you need to add a new feature (like multiple undo steps or more operations), you must add more backup logic, leading to duplicated and tangled code. Yikes! 😬

‍

The Interviewer Asks: “This Code Looks Messy. How Can You Improve It?” 😮
Imagine you’re in an interview and the interviewer points out the clutter—so many manual backups and direct state manipulations! They ask, “How would you clean this up and make it more maintainable?” That’s when you realize the need for a better solution.

‍

The Ugly Reality: Messy Code That’s Hard to Scale 😖
As features are added, the traditional method turns into something like this:

‍

Java
public class TextEditorUgly {
  private String text;
  // Imagine if we have multiple backups for multi-level undo!
  private String backup1;
  private String backup2;
  // ... and so on

  public void setText(String text) {
    // Before changing, we update backups manually
    backup2 = backup1;
    backup1 = this.text;
    this.text = text;
  }

  public void undo() {
    // Only supports one level of undo correctly
    this.text = backup1;
  }

  public void showText() {
    System.out.println("Current text: " + text);
  }
}
‍

Explanation:

• This code quickly becomes unmanageable as you try to support multi-level undos and additional state-related operations.

• The backup logic is scattered, and every new feature forces you to modify the core class—a nightmare for maintainability! 😵💥

‍
Enter Our Savior: The Memento Pattern to the Rescue! 😇🚀
Time to bring in our hero—the Memento Pattern! Instead of manually handling state backups inside the editor, we create separate objects (mementos) to hold the state. Our text editor (the Originator) can create a memento when needed, and a caretaker (like a history manager) can store these mementos to enable undo operations.

‍

Step-by-Step: Implementing the Memento Pattern
Step 1: The Originator – Our Text Editor
This class will have the current state (the text) and methods to save and restore its state.

‍

Java
class TextEditor {
  private String text;
  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  // Creates a memento (snapshot) of the current state
  public Memento save() {
    return new Memento(text);
  }

  // Restores the state from the given memento
  public void restore(Memento memento) {
    this.text = memento.getText();
  }
}
‍

Explanation:

• The TextEditor class holds the current text.

• The save() method creates a new memento capturing the current state.

• The restore() method uses a memento to revert to a previous state.

‍

Step 2: The Memento – The Snapshot of Our State
This class stores the state of the text editor.

‍

Java
class Memento {
  private final String text;
  public Memento(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
‍

Explanation:

• The Memento is a simple, immutable object that holds the state (in this case, the text).

• It encapsulates the state so that the TextEditor can restore itself without exposing internal details.

‍

Step 3: The Caretaker – The History Manager
This class manages the history of mementos to support multiple undo operations.

‍

Java
import java.util.Stack;
class EditorHistory {
  private Stack<Memento> history = new Stack<>();
  public void push(Memento memento) {
    history.push(memento);
  }

  public Memento pop() {
    if (!history.isEmpty()) {
      return history.pop();
    }
    return null;
  }
}
‍

Explanation:

• EditorHistory uses a stack to keep track of saved states.

• You can push new mementos onto the stack and pop them when an undo is needed.

‍‍

Step 4: Bringing It All Together – Using Our Memento Pattern
Let’s see our text editor with undo functionality in action!

Java
public class MementoPatternDemo {
  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    EditorHistory history = new EditorHistory();
    // Initial text
    editor.setText("Hello");
    System.out.println("Current text: " + editor.getText());
    history.push(editor.save());
    // User types something new
    editor.setText("Hello, World!");
    System.out.println("Current text: " + editor.getText());
    history.push(editor.save());
    // Another change
    editor.setText("Hello, World! Welcome to Memento Pattern.");
    System.out.println("Current text: " + editor.getText());
    // Undo the last change
    Memento previousState = history.pop();
    editor.restore(previousState);
    System.out.println("After undo, text: " + editor.getText());
    // Undo to the initial state
    previousState = history.pop();
    editor.restore(previousState);
    System.out.println("After second undo, text: " + editor.getText());
  }
}
‍

Explanation:

• We create an instance of TextEditor and an EditorHistory to manage our backups.

• Each time the text changes, we save a memento to our history.

• When an undo is required, we pop the last saved state and restore it.

• This design cleanly separates the state management from the text editor logic, making it easier to extend and maintain

‍

Here’s what you'll see when you run the Memento Pattern demo code:

Current text: Hello
Current text: Hello, World!
Current text: Hello, World! Welcome to Memento Pattern.
After undo, text: Hello, World!
After second undo, text: Hello
Explanation:
• The editor starts with "Hello" and that state is saved.

• The text is updated to "Hello, World!" and then saved.

• After a further change to "Hello, World! Welcome to Memento Pattern.", the undo operation restores the most recent saved state ("Hello, World!").

• A second undo then restores the initial state ("Hello").

‍

Follow-Up: Extending the Functionality 🔥💡
Suppose the interviewer asks,

“What if you want to add a redo feature or save more detailed state information?”

With the Memento Pattern, you can easily extend your caretaker to handle redo stacks or include additional data in your memento (like formatting). The key is that your core TextEditor class remains unchanged, and you manage state history externally.

‍

For example, you could add a redo stack in the EditorHistory class to support redoing undone changes, all without modifying the TextEditor or Memento classes. How neat is that? 😎

‍

Below is a short snippet that extends our Memento Pattern to include redo functionality. We modify the caretaker (EditorHistory) to manage two stacks—one for undo and one for redo.

Java
import java.util.Stack;
// Extended caretaker with redo support
class EditorHistory {
  private Stack<Memento> undoStack = new Stack<>();
  private Stack<Memento> redoStack = new Stack<>();

  // Save new state; clear redo stack when a new state is saved
  public void saveState(Memento memento) {
    undoStack.push(memento);
    redoStack.clear();
  }

  // Undo operation: push current state to redo stack and return last state from
  // undo stack
  public Memento undo(Memento currentState) {
    if (!undoStack.isEmpty()) {
      redoStack.push(currentState);
      return undoStack.pop();
    }
    return null;
  }

  // Redo operation: push current state to undo stack and return last state from
  // redo stack
  public Memento redo(Memento currentState) {
    if (!redoStack.isEmpty()) {
      undoStack.push(currentState);
      return redoStack.pop();
    }
    return null;
  }
}
‍

And here’s how you might use this in your main program:

Java
public class MementoRedoDemo {
  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    EditorHistory history = new EditorHistory();
    // Initial state
    editor.setText("Hello");
    history.saveState(editor.save());
    // First change
    editor.setText("Hello, World!");
    history.saveState(editor.save());
    // Second change
    editor.setText("Hello, World! Welcome!");
    System.out.println("Current: " + editor.getText());
    // Undo the last change
    Memento previousState = history.undo(editor.save());
    if (previousState != null) {
      editor.restore(previousState);
      System.out.println("After undo: " + editor.getText());
    }
    // Redo the undone change
    Memento redoState = history.redo(editor.save());
    if (redoState != null) {
      editor.restore(redoState);
      System.out.println("After redo: " + editor.getText());
    }
  }
}
‍

Explanation:
• EditorHistory Class: 

○ Maintains two stacks: undoStack for saving past states and redoStack for states that have been undone.

○ saveState() pushes a new state onto the undo stack and clears the redo stack.

○ undo() moves the current state to the redo stack and returns the last saved state.

○ redo() moves the current state back to the undo stack and retrieves the state from the redo

stack.

‍

• Main Demo: 

○ The text editor saves states as the text changes.

○ An undo operation reverts to a previous state, and a subsequent redo restores the undone change.

This extension allows you to easily support both undo and redo operations without modifying your core TextEditor or Memento classes.

‍

Article image

‍

Explanation:
• TextEditor (Originator):

○ Holds the current text and can create a Memento via the save() method.

○ Restores its state using the restore(Memento m) method.

‍

• Memento:

○ Encapsulates the state of the TextEditor (i.e., the text) in an immutable way.

‍

• EditorHistory (Caretaker):

○ Manages two stacks (undoStack and redoStack) to support both undo and redo operations.

○ Provides methods to save new states and retrieve states for undo/redo without affecting the

TextEditor's internal logic.

‍

Memory Magic: Key Advantages of the Memento Pattern
• Encapsulated State 🤐: Safely stores an object's state without exposing its internals.

• Simplified Undo 🔙: Easily implements rollback functionality.

• Separation of Concerns 🧩: Keeps state management separate from core logic.

• Effortless Recovery 🚀: Enables quick restoration of previous states.

‍

Real-Life Use Cases and Examples 🌍✨
The Memento Pattern isn’t just for text editors! Here are some everyday scenarios where it shines:

• Game State Saving:

Save a game’s progress at critical checkpoints so players can resume from a previous state if they lose. 🎮💾

‍

• Form Data Recovery:

In web applications, store the state of a form so that if a user navigates away accidentally, they can restore their previous entries. 📝🔄

‍

• Configuration Management:

Save configurations or settings before making changes so that you can revert back if something goes wrong. ⚙️🔙

‍

• Financial Transactions:

Maintain snapshots of account states before transactions to support rollbacks in case of errors. 💰📉

‍

Wrapping Up Our Memory Journey 🚀🎉
The Memento Design Pattern is like having a personal memory bank for your objects—storing snapshots of their state so you can always roll back when needed. By separating state saving from your core logic, you keep your code clean, modular, and easy to maintain. Whether it’s for undo functionality in a text editor, saving game progress, or managing complex configurations, the Memento Pattern is an incredibly powerful tool in your design pattern toolkit.

‍

I hope this journey into the Memento Pattern has inspired you to think about state management in a whole new way. Keep coding, keep experimenting, and most importantly, have fun along the way! 😄👍 Happy coding!

