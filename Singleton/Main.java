 

//  private constructor 
// A Singleton is a design pattern that ensures:
// Only ONE object of a class can ever exist, and
// Everyone uses the same object.

// A Singleton:
// Allows only one instance of a class
// Think of
// President of a country

// Creating multiple objects would cause problems
// You want to share state or resources
// You need controlled access to a single object

// Private constructor → no one can use new
// Private static variable → holds the single instance
// Public static method → returns the instance


// Same idea, different level:
// Getter/setter → controls variables
// Singleton → controls object creation


class Singleton {

    private static Singleton instance;   
    //singleton only initializes once ever !! 
    public static Singleton getInstance(){
        if(instance  == null){
            instance  = new Singleton();
        }
        return instance ;
    }

}

// Java program execution flow:
// You run the program
// JVM starts
// JVM searches for
public class Main{
    public static void main(String args[]){
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}