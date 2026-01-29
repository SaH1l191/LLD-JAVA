
//messy code : 

// • What if we need to add more devices in the future? For example,
//  a new SmartSpeaker or SecurityCamera.

// • What if the logic for interacting with devices changes? For instance, 
// what if the protocol for controlling a SmartLight switches from Wi-Fi to a 
// cloud-based API?


// => In such scenarios, managing the operations for each device type in the Main class
//  becomes complex. The code grows fragile, and adding or modifying device types requires
//   changes in multiple places, increasing the risk of introducing bugs.



// violates Open/Closed Principle,readability and maintainability. 

// public class Adapter {
    
//     public void controlDevice(String deviceType){
//         if(deviceType.equals("AirConditioner")){
//             System.out.println("Controlling Air Conditioner");
//         }else if(deviceType.equals("Heater")){
//             System.out.println("Controlling Heater");
//         }else if(deviceType.equals("Light")){
//             System.out.println("Controlling Light");
//         }else{
//             System.out.println("Unknown Device");
//         }
//     }
    
//     public static void main(String[] args) {
//         Adapter adapter = new Adapter();
//         adapter.controlDevice("AirConditioner");
//         adapter.controlDevice("Heater");
//         adapter.controlDevice("Light");
//         adapter.controlDevice("Fan");
//     }
// }



// It acts as a bridge between two incompatible interfaces,
// allowing them to work together seamlessly without modifying their code.

interface SmartDevice {
    void turnOn();
    void turnOff();
}

class AirConditioner {
    public void connectViaBluetooth() {
        System.out.println("Air Conditioner connected via Bluetooth");
    }
    public void startCooling() {
        System.out.println("Air Conditioner is cooling");
    }
    public void stopCooling() {
        System.out.println("Air Conditioner stopped cooling");
    }
    public void disconnect() {
        System.out.println("Air Conditioner disconnected");
    }
}

class AirConditionerAdapter implements SmartDevice {
    private AirConditioner airConditioner;

    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void turnOn() {
        airConditioner.connectViaBluetooth();
        airConditioner.startCooling();
    }

    @Override
    public void turnOff() {
        airConditioner.stopCooling();
        airConditioner.disconnect();
    }
}

public class Adapter{

    public static void main(String[] args) {
        SmartDevice acAdapter = new AirConditionerAdapter(new AirConditioner());
        acAdapter.turnOn();
        acAdapter.turnOff();

    }
}