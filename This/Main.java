 

 //A source file can have at most ONE public class, and its name MUST match the filename.
  class Person{

    private String name ;
    private int age ;


    //constructor chaining:calling other constructor 
    Person(String name){
        this(name, 0);
    }

    Person(String name, int age){
        this.name = name ;
        this.age = age ;
    }

    void display(){
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
    }


    
}
// That public class (Main) matches the filename
public class Main{
    public static void main(String args[]){
        Person p = new Person("Alice");
        p.display();
    }
}