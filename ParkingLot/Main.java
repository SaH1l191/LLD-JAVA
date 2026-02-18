
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK
}
enum Status {
    ACTIVE,
    PAID
}

abstract class Vehicle {
    private final VehicleType vehicleType;
    private  final String licensePlate;

    public Vehicle(VehicleType vehicleType, String licensePlate ) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType; 
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void displayDetails() {
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("License Plate: " + licensePlate);
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(VehicleType.CAR, licensePlate);
    }
}
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(VehicleType.MOTORCYCLE, licensePlate);
    }
}
class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(VehicleType.TRUCK, licensePlate);
    }
}

interface VehicleFactory {
    Vehicle createVehicle(String licensePlate);
}
class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String licensePlate) {
        return new Car(licensePlate);
    }
}
class MotorcycleFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String licensePlate) {
        return new Motorcycle(licensePlate);
    }
}
class TruckFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String licensePlate) {
        return new Truck(licensePlate);
    }
}
interface PaymentStrategy {
    void pay(double amount);
}
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Cash.");
    }
}
interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int hours);
}
 

class HourlyRateStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {
        int billableHours = (int) Math.ceil(hours);
        double ratePerHour = switch (vehicle.getVehicleType()) {
            case MOTORCYCLE -> 1.0;
            case CAR -> 2.0;
            case TRUCK -> 3.0;
            default -> throw new AssertionError();
        };  
        return billableHours * ratePerHour;
    }
}
class PremiumRateStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {
        int billableDays = (int) Math.ceil(hours / 24.0);
        double ratePerDay = switch (vehicle.getVehicleType()) {
            case CAR ->  10.0;
            case MOTORCYCLE ->20.0;
            case TRUCK ->30.0;
            default -> throw new AssertionError();
        };
        return ratePerDay * billableDays;
    }
}

class EntryGate {
    // public ParkingTicket generateTicket(Vehicle vehicle) {
    //     openGate();
    //     ParkingTicket parkingTicket = new ParkingTicket(vehicle);
    //     vehicle.displayDetails();
    //     return parkingTicket; 
    // }
    public void openGate(){
        System.out.println("Entry gate opened.");
    }
}
class ExitGate {
    // public ParkingTicket processExit(ParkingTicket parkingTicket, PricingStrategy pricingStrategy,
    //  PaymentStrategy paymentStrategy) {
    //     parkingTicket.closeTicket();

    //     int hours = parkingTicket.getParkingDuration();

    //     double amount = parkingTicket.calculateAmount(pricingStrategy);
    //     paymentStrategy.pay(amount);
    //     parkingTicket.markPaid();
    //     return parkingTicket;
    // }
    public void openGate() {
        System.out.println("Exit gate opened.");
    }
}



class ParkingSpot {
    private Vehicle parkedVehicle;
    private final VehicleType spotType;

    public ParkingSpot(VehicleType spotType) {
        this.spotType = spotType;
    }
    public boolean isAvailable() {
        return parkedVehicle == null; 
    }
    public synchronized boolean assignVehicle(Vehicle vehicle) {
        if(parkedVehicle != null || vehicle.getVehicleType() != this.spotType) {
            return false;
        }
        this.parkedVehicle = vehicle;
        System.out.println("Vehicle assigned to parking spot.");
        return true;
    }
    public synchronized  void removeVehicle() {
        this.parkedVehicle = null;
        System.out.println("Vehicle removed from parking spot.");
    }
    public Vehicle getParkedVehicle() {
        return parkedVehicle; 
    }
    public  VehicleType getSpotType() {
        return spotType;
    }
}
class ParkingFloor {
    List<ParkingSpot> parkingSpots  = new ArrayList<>();

    public synchronized void addParkingSpots(ParkingSpot[] parkingSpotsArray) {
        Collections.addAll(parkingSpots, parkingSpotsArray);
    }
    public List<ParkingSpot> getSpots() {
        return parkingSpots;
    }
}
class ParkingLot {
    List<ParkingFloor> parkingFloors =new ArrayList<>();
    
    private ParkingLot(List<ParkingFloor> floors) {
        this.parkingFloors = floors;
    }

    public void addParkingFloor(ParkingFloor[] parkingFloorsArray) {
        Collections.addAll(parkingFloors, parkingFloorsArray);
    }

    public synchronized ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingFloor floor : parkingFloors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.getSpotType() == type && spot.isAvailable()) {
                    return spot;
                }
            }
        }
        return null;
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private final List<ParkingFloor> floors = new ArrayList<>();

        public Builder addFloor(int carSpots,int bikeSpots, int truckSpots){
            ParkingFloor floor = new ParkingFloor();
            List<ParkingSpot> spots = new ArrayList<>();

            for(int i=0;i<carSpots;i++) {
                spots.add(new ParkingSpot(VehicleType.CAR));
            }
            for(int i=0;i<bikeSpots;i++) {
                spots.add(new ParkingSpot(VehicleType.MOTORCYCLE));
            }
            for(int i=0;i<truckSpots;i++) {
                spots.add(new ParkingSpot(VehicleType.TRUCK));
            }
            floor.addParkingSpots(spots.toArray(new ParkingSpot[0]));
            floors.add(floor);
            return this;
        }
        public ParkingLot build() {
            return new ParkingLot(floors);
        }
    }
}

class ParkingTicket {
    private static final AtomicInteger ticketCounter = new AtomicInteger(1);
    private final int ticketId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private final ParkingSpot spot;
    private Status status;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = ticketCounter.getAndIncrement();
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.spot = spot;
    }
    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
    }
    public int getParkingDuration() {
        if (exitTime == null) {
            throw new IllegalStateException("Exit time not recorded.");
        }
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        int hours = (int) Math.ceil(minutes / 60.0);
        return Math.max(hours, 1);
    }
    public double calculateAmount(PricingStrategy strategy) {
        int hours = getParkingDuration();
        return strategy.calculatePrice(vehicle,hours);
    }
    public void markPaid() {
        this.status = Status.PAID;
    }
    public int getTicketId() {
        return ticketId;
    }
    public ParkingSpot getSpot() {
        return spot;
    }
    public Status getStatus() {
        return status;
    }
    
}

class ParkingService { 
    private final ParkingLot parkingLot;
    private final EntryGate entryGate;
    private final ExitGate exitGate;

    public ParkingService(ParkingLot parkingLot, EntryGate entryGate, ExitGate exitGate) {
        this.parkingLot = parkingLot;
        this.entryGate = entryGate;
        this.exitGate = exitGate;
    }
    public ParkingTicket enter(Vehicle vehicle){
        ParkingSpot spot = parkingLot.findAvailableSpot(vehicle.getVehicleType());
        if(spot == null || !spot.assignVehicle(vehicle)) {
            throw new RuntimeException("No available spot.");
        }
        entryGate.openGate();
        ParkingTicket ticket = new ParkingTicket(vehicle, spot);
        return ticket;
    }
    public void exit(ParkingTicket ticket,PricingStrategy pricingStrategy,
    PaymentStrategy paymentStrategy) {
        ticket.closeTicket();
        double amount = ticket.calculateAmount(pricingStrategy);
        paymentStrategy.pay(amount);
        ticket.getSpot().removeVehicle();
        ticket.markPaid();  
        exitGate.openGate();
    }

}

// public class Main {
//     public static void main(String[] args) {
//         // ParkingLot parkingLot = new ParkingLot();
//         // ParkingFloor floor1 = new ParkingFloor();
//         // ParkingFloor[] floors = new ParkingFloor[1];
//         // floors[0] = floor1;
//         // ParkingSpot[] spots = new ParkingSpot[4];
//         // spots[0] = new ParkingSpot(VehicleType.CAR);
//         // spots[1] = new ParkingSpot(VehicleType.MOTORCYCLE);
//         // spots[2] = new ParkingSpot(VehicleType.TRUCK);
//         // spots[3] = new ParkingSpot(VehicleType.CAR);
//         // floor1.addParkingSpots(spots);
//         // parkingLot.addParkingFloor(floors);

//         ParkingLot parkingLot = ParkingLot.build()
//         .addFloor(2, 1, 1)   // Floor 1
//         .addFloor(3, 2, 0)   // Floor 2
//         .build();


//         ParkingService service = new ParkingService(parkingLot,new EntryGate(),new ExitGate());

//         VehicleFactory carFactory = new CarFactory();
//         Vehicle car = carFactory.createVehicle("MH12AB1234");
//         ParkingTicket ticket = service.enter(car);
//         try{
//             Thread.sleep(9000); 
//         }catch(InterruptedException e){
//             e.printStackTrace();
//         } 
//         service.exit(ticket,new HourlyRateStrategy(),new CashPayment());




//         VehicleFactory motoVehicleFactory = new MotorcycleFactory();
//         Vehicle motorcycle = motoVehicleFactory.createVehicle("MH12CD5678");

//         VehicleFactory truckFactory = new TruckFactory();
//         Vehicle truck = truckFactory.createVehicle("MH12EF9012");


//     }
// }


// testing ParkingService class with all testcases
 // testing ParkingService class with all testcases
class ParkingLotTester {

    private ParkingService service;
    private ParkingLot parkingLot;

    public ParkingLotTester() {
        setup();
    }

    private void setup() {

        // ✅ Use Builder instead of direct constructor
        parkingLot = ParkingLot.build()
                .addFloor(1, 1, 1)   // 1 CAR, 1 MOTORCYCLE, 1 TRUCK
                .build();

        service = new ParkingService(
                parkingLot,
                new EntryGate(),
                new ExitGate()
        );
    }

    /* =============================
       TEST CASES
    ============================== */

    public void runAllTests() {
        testSuccessfulEntryExit();
        testNoSpotAvailable();
        testSpotReleaseAfterExit();
        testMultipleVehicles();
    }

    private void printResult(String testName, boolean result) {
        System.out.println(testName + " : " + (result ? "PASS" : "FAIL"));
        System.out.println("-----------------------------------");
    }

    /* =============================
       TEST 1: Successful Entry & Exit
    ============================== */

    private void testSuccessfulEntryExit() {
        try {
            Vehicle car = new Car("TEST123");
            ParkingTicket ticket = service.enter(car);

            Thread.sleep(2000);

            service.exit(ticket,
                    new HourlyRateStrategy(),
                    new CashPayment());

            boolean passed = ticket.getStatus() == Status.PAID;
            printResult("Test Successful Entry & Exit", passed);

        } catch (Exception e) {
            printResult("Test Successful Entry & Exit", false);
        }
    }

    /* =============================
       TEST 2: No Spot Available
    ============================== */

    private void testNoSpotAvailable() {
        try {
            Vehicle car1 = new Car("CAR1");
            Vehicle car2 = new Car("CAR2");

            service.enter(car1);

            // Only 1 car spot exists → second should fail
            service.enter(car2);

            printResult("Test No Spot Available", false);

        } catch (RuntimeException e) {
            printResult("Test No Spot Available", true);
        }
    }

    /* =============================
       TEST 3: Spot Release After Exit
    ============================== */

    private void testSpotReleaseAfterExit() {
        try {
            Vehicle truck = new Truck("TRK1");
            ParkingTicket ticket = service.enter(truck);

            service.exit(ticket,
                    new HourlyRateStrategy(),
                    new CashPayment());

            boolean spotAvailable = ticket.getSpot().isAvailable();

            printResult("Test Spot Release After Exit", spotAvailable);

        } catch (Exception e) {
            printResult("Test Spot Release After Exit", false);
        }
    }

    /* =============================
       TEST 4: Multiple Vehicle Flow
    ============================== */

    private void testMultipleVehicles() {
        try {
            Vehicle moto = new Motorcycle("MOTO1");
            Vehicle truck = new Truck("TRUCK1");

            ParkingTicket t1 = service.enter(moto);
            ParkingTicket t2 = service.enter(truck);

            Thread.sleep(1000);

            service.exit(t1,
                    new HourlyRateStrategy(),
                    new CashPayment());

            service.exit(t2,
                    new PremiumRateStrategy(),
                    new CreditCardPayment());

            boolean passed =
                    t1.getStatus() == Status.PAID &&
                    t2.getStatus() == Status.PAID;

            printResult("Test Multiple Vehicles", passed);

        } catch (Exception e) {
            printResult("Test Multiple Vehicles", false);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLotTester tester = new ParkingLotTester();
        tester.runAllTests();
    }
}
