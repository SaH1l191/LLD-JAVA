



///one statement : tree hierarchy like structure 

import java.util.ArrayList;
import java.util.List;


interface SmartComponent{
 void turnOff();
 void turnOn();
}


class SmartLight implements  SmartComponent{
    private String name;

    public SmartLight(String name) {
        this.name = name;
    }
    @Override
    public void turnOff() {
        System.out.println(name + " Light is turned off");
    }
    @Override   
    public void turnOn() {
        System.out.println(name + " Light is turned on");
    }
}
class AirConditioner implements SmartComponent{
    private String name;

    public AirConditioner(String name) {
        this.name = name;
    }
    @Override
    public void turnOff() {
        System.out.println(name + " Air Conditioner is turned off");
    }
    @Override   
    public void turnOn() {
        System.out.println(name + " Air Conditioner is turned on");
    }
}

interface CompositeSmartComponent extends SmartComponent{
    void addComponent(SmartComponent component);
    void removeComponent(SmartComponent component);
}

class SmartLivingRoom implements CompositeSmartComponent{
    private List<SmartComponent> components = new ArrayList<>();
    @Override
    public void addComponent(SmartComponent component) { 
        components.add(component);
    }
    @Override
    public void removeComponent(SmartComponent component) { 
        components.remove(component); 
    }
    @Override
    public void turnOff() {
        for(SmartComponent c : components){
            c.turnOff();
        }
    }
    @Override
    public void turnOn() {
        for(SmartComponent c : components){
            c.turnOn();
        }
    }
}

public class Composite {
    public static void main(String[] args) {
        SmartLight light1 = new SmartLight("Living Room-light");
        AirConditioner ac1 = new AirConditioner("Living Room-AC");

        SmartLivingRoom livingRoom = new SmartLivingRoom();
        livingRoom.addComponent(light1);
        livingRoom.addComponent(ac1);

        System.out.println("Turning on all devices in the living room:");
        livingRoom.turnOn();

        System.out.println("\nTurning off all devices in the living room:");
        livingRoom.turnOff();
    }
}


