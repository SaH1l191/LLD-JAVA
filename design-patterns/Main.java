



//abstract factory pattern
interface Vehicle {
    void start();
    void stop();
}
// open close principle 
class Car implements Vehicle {

    @Override
    public void start() {
        System.out.println("Car started");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped");
    }
}

class Bike implements Vehicle {

    @Override
    public void start() {
        System.out.println("Bike started");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }
}

interface VehicleFactory {
    Vehicle create();
}

class CarFactory implements  VehicleFactory{
    @Override
    public Vehicle create() {
        return new Car();
    }
}

class BikeFactory implements  VehicleFactory{
    @Override
    public Vehicle create() {
        return new Bike();
    }
}
// centralized obj creation 



public class Main {

    public static void main(String[] args) {
        VehicleFactory c1 = new CarFactory(); // encapsulation 
        c1.create().start(); //
    }
}
