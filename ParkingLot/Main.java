
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK
}

abstract class Vehicle {
    public VehicleType vehicleType;
    public String licensePlate;

    public Vehicle(VehicleType vehicleType, String licensePlate) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType; 
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
class HourlyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {
        int billableHours = (int) Math.ceil(hours);
        double ratePerHour;
        switch (vehicle.getVehicleType()) {
            case CAR:
                ratePerHour = 2.0;
                break;
            case MOTORCYCLE:
                ratePerHour = 1.0;
                break;
            case TRUCK:
                ratePerHour = 3.0;
                break;
            default:
                throw new AssertionError();
        }
        return billableHours * ratePerHour;
    }
}
class DailyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {
        int billableDays = (int) Math.ceil(hours / 24.0);
        double ratePerDay;
        switch (vehicle.getVehicleType()) {
            case CAR:
                ratePerDay = 500.0;
                break;
            case MOTORCYCLE:
                ratePerDay = 200.0;
                break;
            case TRUCK:
                ratePerDay = 800.0;
                break;
            default:
                ratePerDay = 500.0;
        }
        return ratePerDay * billableDays;
    }
}

class EntryGate {
    public ParkingTicket generateTicket(Vehicle vehicle) {
        System.out.println("Entry gate opened.");
        ParkingTicket parkingTicket = new ParkingTicket(vehicle);
        closeGate();
        return parkingTicket; 
    }
    public void openGate(){
        System.out.println("Entry gate opened.");
    }
    public void closeGate() {
        System.out.println("Entry gate closed.");
    }
}
class ExitGate {
    public ParkingTicket processExit(ParkingTicket parkingTicket, PricingStrategy pricingStrategy,
     PaymentStrategy paymentStrategy) {
        parkingTicket.closeTicket();
        double amount = parkingTicket.calculateAmount(pricingStrategy);
        paymentStrategy.pay(amount);
        parkingTicket.markPaid();
        closeGate();
        return parkingTicket;
    }
    public void openGate() {
        System.out.println("Exit gate opened.");
    }
    public void closeGate() {
        System.out.println("Exit gate closed.");
    }
}
enum Status {
    ACTIVE,
    PAID
}

class ParkingTicket {
    private static final AtomicInteger ticketCounter = new AtomicInteger(0);
    private final int ticketId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Status status;

    public ParkingTicket(Vehicle vehicle) {
        this.ticketId = ticketCounter.getAndIncrement();
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }
    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
    }
    public int getParkingDuration() {
        if (exitTime == null) {
            throw new IllegalStateException("Exit time not recorded.");
        }
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return (int) Math.ceil(minutes / 60.0);
    }
    public double calculateAmount(PricingStrategy pricingStrategy) {
        int hours = getParkingDuration();
        return pricingStrategy.calculatePrice(vehicle,hours);
    }
    public int getTicketId() {
        return ticketId;
    }
    public void markPaid() {
        this.status = Status.PAID;
    }
    public String getParkingDetails(){
        return "Ticket ID: " + ticketId +
                "\nVehicle: " + vehicle.getVehicleType() +
                "\nLicense: " + vehicle.licensePlate +
                "\nEntry: " + entryTime +
                "\nExit: " + exitTime +
                "\nStatus: " + status;
    }
}
class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleType spotType;
    public ParkingSpot(VehicleType spotType) {
        this.spotType = spotType;
    }
    public boolean isAvailable() {
        return parkedVehicle == null; 
    }
    public synchronized void synchronizedassignVehicle(Vehicle vehicle) {
        if(vehicle.getVehicleType() != this.spotType) {
            throw new IllegalArgumentException("Vehicle type not compatible with spot type");
        }
        this.parkedVehicle = vehicle;
        System.out.println("Vehicle assigned to parking spot.");
    }
    public void removeVehicle() {
        this.parkedVehicle = null;
        System.out.println("Vehicle removed from parking spot.");
    }
    public Vehicle getParkedVehicle() {
        return parkedVehicle; 
    }
}
class ParkingFloor {
    List<ParkingSpot> parkingSpots;

    public ParkingFloor() {
        parkingSpots = new ArrayList<ParkingSpot>();
    }
    public synchronized void addParkingSpots(ParkingSpot[] parkingSpotsArray) {
        for (ParkingSpot spot : parkingSpotsArray) {
            parkingSpots.add(spot);
        }
    }
    public void displayBoard() {
        System.out.println("Parking Floor Board:");
        int spotNumber = 1;
        for (ParkingSpot spot : parkingSpots) {
            String status = spot.isAvailable() ? "Available" : "Occupied by " + spot.getParkedVehicle().licensePlate;
            System.out.println("Spot " + spotNumber + " [" + spot.getParkedVehicle() + " / " + spot.getParkedVehicle() + " ] "
                               + "Type: " + spot.spotType + " | Status: " + status);
            spotNumber++;
        }

        // Optional summary
        long freeSpots = parkingSpots.stream().filter(ParkingSpot::isAvailable).count();
        System.out.println("Total spots: " + parkingSpots.size() + ", Free spots: " + freeSpots);
        System.out.println("----------------------------------------");
    }
}
class ParkingLot {
    List<ParkingFloor> parkingFloors;

    public ParkingLot() {
        parkingFloors = new ArrayList<ParkingFloor>();
    }
    public void addParkingFloor(ParkingFloor[] parkingFloorsArray) {
        for (ParkingFloor floor : parkingFloorsArray) {
            parkingFloors.add(floor);
        }
    }
    public void displayBoard() {
        System.out.println("Parking Lot Board:");
        int floorNumber = 1;
        for (ParkingFloor floor : parkingFloors) {
            System.out.println("Floor " + floorNumber + ":");
            floor.displayBoard();
            floorNumber++;
        }
        System.out.println("========================================");
    }
    //more methods can be available or added as code grows
}


class Main {
    public static void main(String[] args) {
        
    }
}
