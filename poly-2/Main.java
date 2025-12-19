
// When a class promises to follow the contract described by an interface, it implements that interface.
// The class must then provide concrete code for all the methods declared in the interface.
// This ensures a standard set of behaviors across different classes, even if they do things differently internally.
// Simple analogy:
// Think of an interface as a job description.
// Any class that “takes the job” must fulfill the duties listed (implement the methods).
// Different classes can fulfill the job differently but must provide the same methods.
// Example interface:
// interface Vehicle {
//     void start();     
//     void stop();
// }
// extends means kind of inheritance 
// we write interface in which we define what to be expected (the data)
// interfaces don’t hold data (no instance variables).
// They only declare method signatures (the what, not the how).
// No method logic inside interfaces (at least traditionally).
interface Vehicle {

    void start();    // method with no body (abstract)

    void stop();
}

class Car implements Vehicle {

    @Override
    public void start() {
        System.out.println("Car starting");
    }

    @Override
    public void stop() {
        System.out.println("Car stopping");
    }
}

class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Bike starting");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopping");
    }
}

public class Main {

    public static void main(String args[]) {
        Vehicle v = new Car();
        v.start();
        Car c = new Car();
        c.start();
        Bike b = new Bike();
        b.start();
        //instead of doing : calling each obj explicitly 
        //we ahve flexibility 

        Vehicle[] vehicles = {new Car(), new Bike()};
        for (Vehicle vehicle : vehicles) {
            vehicle.start();
        }
    }
}
