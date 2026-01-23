
interface Vehicle {

    void start();

    void stop();
}
// Concrete Classes for Car Brands

class Honda implements Vehicle {

    public void start() {
        System.out.println("Honda Car is starting");
    }

    public void stop() {
        System.out.println("Honda Car is stopping");
    }
}

class Toyota implements Vehicle {

    public void start() {
        System.out.println("Toyota Car is starting");
    }

    public void stop() {
        System.out.println("Toyota Car is stopping");
    }
}

class BMW implements Vehicle {

    public void start() {
        System.out.println("BMW Car is starting");
    }

    public void stop() {
        System.out.println("BMW Car is stopping");
    }

}

//centralized facotry creation , loose coupling (if the logic in creating objects changes  or when smthng changes in the logic of creating objects then we need to change only one place)
//encapsulation 
class CarFactory {
// however this code can turn into building of code block // => yuse abstract factory design pattern 

    public Vehicle createVehicle(String type) {
        if (type.equals("Honda")) {
            return new Honda();
        } else if (type.equals("Toyota")) {
            return new Toyota();
        } else if (type.equals("BMW")) {
            return new BMW();
        } else {
            throw new IllegalArgumentException("Invalid Car Type");
        }
    }
}

public class factory {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        factory.createVehicle("Toyota").start();
        factory.createVehicle("BMW").stop();

    }

}
