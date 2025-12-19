
//multiple forms 
//cmpile time(static) & run time (dyamic run time)
//  cmpile time : method overlaodign 
class Vehicle {

    void start() {
        System.out.println("vehicle started");
    }
}

class Car extends Vehicle {

    @Override
    void start() {
        System.out.println("car started");
        //super.start();
    }
}

class Bike extends Vehicle {

    @Override
    void start() {
        System.out.println("bike started");
    }
}
 

public class Main {

    public static void main(String args[]) {
        Vehicle v = new Vehicle();
        v.start();
        Car c = new Car();
        c.start();
        Bike b = new Bike();
        b.start();
    }
}
