 
// Static methods do NOT need an object
class Car {
    static void info() {
        System.out.println("This is a car");
    }
}

class Counter {
    static int count = 0; 
    Counter() {
        count++;
    }
}


// new → creates objects
// non-static → needs object
// static → no object needed
// static ≠ single object

public class Main{
    //Program starts before any object exists
    // // Java needs a method it can run without creating objects
    public static void main(String args[]){
        Car.info();

        // Static = “belongs to the CLASS”
        // All objects share one thing
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter(); 
        System.out.println(Counter.count);

    }
}

