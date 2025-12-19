

//so basically this can be returned and so used by primarily 
// the functions 
class Person {

    private String name; 
    // see the return type 
    Person setName(String name) {
        this.name = name;
        return this;
    }

    void display() {
        System.out.println("Name : " + this.name);
    }


    void introduce(){
        greet(this);
    }

    void greet(Person p){
        System.out.println("Hello " + p);
    }

}

public class Main {

    public static void main(String args[]) {
        Person p = new Person();
        p.setName("Alice").display();


        Person pp = new Person();
        pp.introduce();
    }
}


// one careful case : 
// this cannot be used for a static keyword 

// class Example {
//     private String message = "Hello, World!";
//     // Static method
//     public static void displayMessage() {
//         // ERROR: Cannot use 'this' in a static context
//         System.out.println(this.message);
//     }
//     public void displayInstanceMessage() {
//         // Valid: 'this' refers to the current instance
//         System.out.println(this.message);
//     }
// }


//Static methods and variables belong to the class itself, not to any specific object (instance).
// Instance methods and variables belong to a specific object created from the class.
// this refers to the current object instance.
// In a static method, there is no current object because static methods belong to the class, not any object.
// So, this does not exist in static methods and causes a compilation error if used there.