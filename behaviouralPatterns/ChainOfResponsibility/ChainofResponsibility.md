# Chain of Responsibility Design Pattern

**Topic Tags:** System Design, LLD

 Github Codes Link: https://github.com/aryan-0077/CWA-LowLevelDesignCode

Hey there, fellow coder! 

Today, I want to share a fun and practical design pattern called the Chain of Responsibility. Imagine you're in a relay race —each runner passes the baton along until one of them is best suited to finish the race. That's exactly how this pattern works: a request is passed along a chain of handlers until one of them takes care of it. Let's explore this pattern together in a simple scenario!

## A Real-Life Scenario: Leave Request Approval 

Picture this: an employee submits a leave request. Depending on how many days of leave are requested, different people can approve it. For example, a short leave is handled by a Supervisor, a moderate leave by a Manager, and a longer leave by a Director. Before we introduce our pattern, let's see how you might solve this problem the traditional way.

## The Traditional (Messy) Approach 

Using a traditional approach, you might write one giant function with nested if-else statements to check the number of leave days. Check out this simplified code:

```java
public class LeaveRequestTraditional {
    public static void main(String[] args) {
        int leaveDays = 10; // Employee requests 10 days off
        if (leaveDays <= 3) {
            System.out.println("Supervisor approved the leave.");
        } else if (leaveDays <= 7) {
            System.out.println("Manager approved the leave.");
        } else if (leaveDays <= 14) {
            System.out.println("Director approved the leave.");
        } else {
            System.out.println("Leave request denied. Too many days!");
        }
    }
}
```

### Explanation:
- We use nested if-else conditions to decide who approves the leave.
- This code is hard to extend if more rules or approvers are added. 

## The Interviewer's Challenge 

Imagine being in an interview when the interviewer asks, "How would you refactor this so it's more scalable and easier to maintain?" You'd quickly realize that our current approach is messy, hard to extend, and not very maintainable. This is where our design pattern hero steps in!

## The Ugly Truth: Messy Code Duplication 

As more conditions pile up, the code can become a tangled mess. Take a look:

```java
public class LeaveRequestUgly {
    public static void main(String[] args) {
        int leaveDays = 12;
        if (leaveDays > 0) {
            if (leaveDays <= 3) {
                System.out.println("Supervisor approved the leave.");
            } else {
                if (leaveDays <= 7) {
                    System.out.println("Manager approved the leave.");
                } else {
                    if (leaveDays <= 14) {
                        System.out.println("Director approved the leave.");
                    } else {
                        System.out.println("Leave request denied. Too many days!");
                    }
                }
            }
        } else {
            System.out.println("Invalid leave request.");
        }
    }
}
```

### Explanation:
- All those nested ifs make the code really hard to follow and maintain. 

## Enter Our Savior: Chain of Responsibility to the Rescue! 

Now, let's refactor our solution using the Chain of Responsibility pattern. Instead of a single big function, we'll create a series of handler classes. Each handler checks if it can process the leave request; if not, it passes the request along the chain.

### Step 1: Creating the Abstract Handler

First, we define an abstract class that sets up the chain.

```java
abstract class Approver {
    protected Approver nextApprover;
    
    // Set the next handler in the chain
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    
    // Abstract method to process the leave request
    public abstract void processLeaveRequest(int leaveDays);
}
```

### Explanation:
- The Approver class has a reference to the next handler.
- Each concrete handler will implement the processLeaveRequest method.

### Step 2: Creating the Concrete Handlers

Now, let's create concrete classes for each approver.

```java
class Supervisor extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 3) {
            System.out.println("Supervisor approved the leave.");
        } else if (nextApprover != null) {
            nextApprover.processLeaveRequest(leaveDays);
        }
    }
}

class Manager extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 7) {
            System.out.println("Manager approved the leave.");
        } else if (nextApprover != null) {
            nextApprover.processLeaveRequest(leaveDays);
        }
    }
}

class Director extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 14) {
            System.out.println("Director approved the leave.");
        } else if (nextApprover != null) { // Pass on if not handled
            nextApprover.processLeaveRequest(leaveDays);
        } else {
            System.out.println("Leave request denied. Too many days!");
        }
    }
}
```

### Explanation:
- Supervisor handles requests up to 3 days.
- Manager handles requests up to 7 days.
- Director handles requests up to 14 days and passes the request along if needed.

### Step 3: Putting It All Together

Set up the chain and process a leave request.

```java
public class LeaveRequestChainDemo {
    public static void main(String[] args) {
        // Create handler instances
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();
        
        // Set up the chain: Supervisor -> Manager -> Director
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        
        // Process a leave request
        int leaveDays = 10;
        System.out.println("Employee requests " + leaveDays + " days of leave.");
        supervisor.processLeaveRequest(leaveDays);
    }
}
```

### Explanation:
- We create the chain by linking the approvers together.
- The leave request is processed starting from the Supervisor, passing along until the appropriate handler approves it.

This modular approach is much cleaner and easier to extend! 

## Follow-Up: Handling Unprocessed Requests with Extra Flexibility 🔥💡

Suppose the interviewer asks, "What if none of the handlers can process the request?" We can easily add an extra handler (like an HR department) at the end of the chain to catch such cases.

### Adding an HR Handler

```java
class HR extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        System.out.println("HR: Leave request requires further discussion.");
    }
}
```

### Explanation:
- The HR class acts as a catch-all handler for any requests that aren't processed by previous approvers.

### 🔗 Setting Up the Extended Chain

```java
public class LeaveRequestChainFollowUpDemo {
    public static void main(String[] args) {
        // Create handler instances
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();
        Approver hr = new HR();
        
        // Set up the chain: Supervisor -> Manager -> Director -> HR
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(hr); // Now HR handles any unprocessed request
        
        // Process a leave request that exceeds Director's approval limit
        int leaveDays = 20;
        System.out.println("Employee requests " + leaveDays + " days of leave.");
        supervisor.processLeaveRequest(leaveDays);
    }
}
```

### Explanation:
- Here, the request for 20 days is passed from Supervisor → Manager → Director, and finally, HR handles it.
- This follow-up code demonstrates the flexibility and scalability of the Chain of Responsibility pattern. 😊👌

## Chain Reaction of Benefits: Advantages of the Chain of Responsibility Pattern

### • Loose Coupling Between Sender and Handler 🤝:
The sender of a request doesn't need to know which handler will process it. This decoupling means you can change or add new handlers without impacting the sender's code.

### • Enhanced Flexibility & Scalability 🚀:
You can easily extend the chain by adding new handlers or reordering existing ones, making your system highly adaptable to changing requirements.

### • Improved Code Organization & Maintainability 🛠️:
Instead of managing complex if-else conditions, each handler encapsulates its own logic. This modularity leads to cleaner, more maintainable code.

### • Reusability of Handlers 🔄:
Handlers designed for one chain can be reused in other chains or contexts, reducing code duplication and increasing overall reusability.

### • Dynamic Request Handling 💡:
The pattern allows requests to be passed along the chain dynamically until a suitable handler is found. This ensures that each request is handled appropriately, even as the system evolves.

## Real-Life Magic with Chain of Responsibility 🌍✨

You might wonder, "Where else can I use this pattern?" Here are some everyday scenarios:

### • Technical Support:
A customer's issue is escalated from Level 1 support to higher levels until someone can resolve it. 📞🔧

### • Logging Systems:
Log messages pass through various loggers based on severity (INFO, DEBUG, ERROR). 📝📊

### • GUI Event Handling:
User events travel through a chain of UI components until one handles the event. 🖥️🎨

### • Authentication:
A request is passed through several filters to validate credentials and permissions. 🔐🛡

## Wrapping Up Our Journey 🚀🎉

The Chain of Responsibility design pattern is like passing a baton in a relay race—each handler gets its turn until the request is processed. By using this pattern, you avoid a tangled web of if-else statements, resulting in cleaner, modular, and extendable code. I hope this fun journey into the Chain of Responsibility pattern inspires you to apply it in your own projects. Keep coding, keep experimenting, and most importantly, have fun along the way! 😄👍 Happy coding!

## Reason of Using Abstract Instead of Interfaces

### What the abstract class is doing here

```java
abstract class Approver {
    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void processLeaveRequest(int leaveDays);
}
```

This class provides both:
- **State** → nextApprover
- **Behavior** → setNextApprover()
- **A contract** → processLeaveRequest()

That combination is the key reason.

### Why NOT just an interface? 🤔

#### 1️⃣ Interfaces (traditionally) can't hold state

The chain needs this:
```java
protected Approver nextApprover;
```

Each handler stores a reference to the next handler.

With an interface:
- You cannot have instance fields (only public static final constants)
- Every concrete class would have to redefine nextApprover

👉 **Result: code duplication**

```java
class Supervisor implements Approver {
    private Approver nextApprover;

    public void setNextApprover(Approver next) {
        this.nextApprover = next;
    }
}
```

Same code again in Manager, Director, HR… 😬

**Abstract class = write once, reuse everywhere**

#### 2️⃣ Abstract class enforces a shared base implementation

In Chain of Responsibility, all handlers:
- Belong to the same hierarchy
- Share common wiring logic
- Differ only in decision logic

That's textbook "is-a" relationship:
- Supervisor is an Approver
- Manager is an Approver

Abstract class models this perfectly.

#### 3️⃣ Cleaner, safer chaining logic

Because setNextApprover() lives in the abstract class:
- You guarantee every handler can be chained
- You avoid bugs where one handler forgets to implement chaining

This is template-style design:
- Abstract class defines the structure
- Subclasses fill in the details

### When WOULD an interface make sense? ✅

If:
- You don't need state
- You want multiple inheritance
- Each handler manages chaining differently

Example (Java 8+):
```java
interface Approver {
    void processLeaveRequest(int leaveDays);
}
```

Then each class handles chaining itself — more flexible, but more error-prone.
