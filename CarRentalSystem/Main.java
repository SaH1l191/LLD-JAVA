import java.util.*;

enum VehicleType {
    ECONOMY,
    LUXURY,
    SUV,
    SuperCar
}

enum VehicleStatus {
    AVAILABLE,
    RENTED,
    MAINTENANCE
}

enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED
}

enum ReservationStatus {
    PENDING,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}


interface PaymentStrategy {
    void processPayment(double amount);
}
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount); 
        // Logic for credit card payment processing would go here
    }
}
// class CashPayment implements PaymentStrategy {
//     @Override
//     public void processPayment(double amount) {
//         System.out.println("Processing cash payment of $" + amount);
//         // Logic for cash payment processing would go here
//     }
// }
class PaymentProcessor {
    public boolean processPayment(double amount, PaymentStrategy paymentStrategy) {
        paymentStrategy.processPayment(amount);
        return true; // Assume payment is successful for simplicity
    }
}

abstract class Vehicle { 
    final private String vehicleType;
    final private String model;
    final private String licensePlate;
    final private String registrationNumber;
    final public double rentalPriceMultiplier;  
    private VehicleStatus vehicleStatus;
    public Vehicle(String vehicleType, String model, String licensePlate, String registrationNumber, double rentalPriceMultiplier, VehicleStatus vehicleStatus) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.licensePlate = licensePlate;
        this.registrationNumber = registrationNumber;
        this.rentalPriceMultiplier = rentalPriceMultiplier;
        this.vehicleStatus = vehicleStatus;
    }
    private String getVehicleType() {
        return vehicleType;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    } 
    public VehicleStatus getRentalStatus() {
        return vehicleStatus;
    }
    void displayVehicleInfo() {
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Base Rental Price: $" + rentalPriceMultiplier);
        System.out.println("Vehicle Status: " + vehicleStatus);
    }
}


//create 2-3 car subclasses below 
class Economy extends Vehicle {
    private static final double BASE_RENTAL_PRICE = 50.0;
    public Economy(String licensePlate,String model, String registrationNumber, double rentalPrice, VehicleStatus vehicleStatus) {
        super("Economy", model ,licensePlate, registrationNumber, BASE_RENTAL_PRICE, vehicleStatus);
    }
}
class Luxury extends Vehicle {
    private static final double BASE_RENTAL_PRICE = 100.0;
    public Luxury(String licensePlate,String model, String registrationNumber, double rentalPrice, VehicleStatus vehicleStatus) {
        super("Luxury", model, licensePlate, registrationNumber, BASE_RENTAL_PRICE, vehicleStatus);
    }
}
class SUV extends Vehicle {
    private static final double BASE_RENTAL_PRICE = 75.0;
    public SUV(String licensePlate,String model, String registrationNumber, double rentalPrice, VehicleStatus vehicleStatus) {
        super("SUV", model, licensePlate, registrationNumber, BASE_RENTAL_PRICE, vehicleStatus);
    }
}
class SuperCar extends Vehicle {
    private static final double BASE_RENTAL_PRICE = 200.0;
    public SuperCar(String licensePlate,String model, String registrationNumber, double rentalPrice, VehicleStatus vehicleStatus) {
        super("SuperCar", model, licensePlate, registrationNumber, BASE_RENTAL_PRICE, vehicleStatus);
    }
}
class VehicleFactory {
    public  Vehicle createVehicle(String vehicleType,String model, String licensePlate, String registrationNumber, double rentalPrice, VehicleStatus vehicleStatus){
        switch(vehicleType){
            case "Economy":
                return new Economy(licensePlate, model, registrationNumber, rentalPrice, vehicleStatus);
            case "Luxury":
                return new Luxury(licensePlate, model, registrationNumber, rentalPrice, vehicleStatus);
            case "SUV":
                return new SUV(licensePlate, model, registrationNumber, rentalPrice, vehicleStatus);
            case "SuperCar":
                return new SuperCar(licensePlate, model, registrationNumber, rentalPrice, vehicleStatus);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
    }
}

class CarStore {
    final private String location;
    final private String name;
    private Map<String,Vehicle> vehicles; // Registration Number (Key) -> Vehicle (Value)

    public CarStore(String location, String name) {
        this.location = location;
        this.name = name;
        this.vehicles = new HashMap<>();
    }
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getRegistrationNumber(), vehicle); 
    } 
    public void removeVehicle(String registrationNumber) {
        vehicles.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(String RegistrationNumber) {
        Vehicle vehicle = vehicles.get(RegistrationNumber);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle with registration number " + RegistrationNumber + " does not exist.");
        }
        return vehicle.getRentalStatus().equals(VehicleStatus.AVAILABLE); 
    }
    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }
    public List<Vehicle> getAvailableVehicles(Date startDate, Date endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles.values()) {
            if (vehicle.getRentalStatus() == VehicleStatus.AVAILABLE) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }
    public String getLocation() {return location;}
    public String getName() {return name;}
}

class User {
    final private String name;
    final private int id ;
    final private String email; 
    private List<Reservation> reservations;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
        this.id = new Random().nextInt(1000);  
    }
    private boolean makeReservation(Reservation reservation) {
        reservations.add(reservation);
        return true;
    }
    private boolean cancelReservation(Reservation reservation) {
        return reservations.remove(reservation);
    }

    private List<Reservation> getReservations() {
        return reservations;
    }
    public int getId() {
        return id;
    }
}

class Reservation {
    final private int id;
    final private User user;
    final private Vehicle vehicle;
    final private Date startDate;
    final private Date endDate;
    private double totalAmount;
    final private PaymentStatus paymentStatus;
    private ReservationStatus reservationStatus;

    public Reservation(User user, Vehicle vehicle, Date startDate, Date endDate) {
        this.id = new Random().nextInt(1000);  
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = PaymentStatus.PENDING;
        this.reservationStatus = ReservationStatus.PENDING;
    }
    public double calculateTotalAmount() {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = (diffInMillies / (1000 * 60 * 60 * 24)) + 1; 
        totalAmount = diffInDays * vehicle.rentalPriceMultiplier;
        return totalAmount;
    }
    public void confirmReservation() {
        if(reservationStatus == ReservationStatus.PENDING) {
            reservationStatus = ReservationStatus.CONFIRMED;
        }
    }
    public void cancelReservation() {
        if(reservationStatus == ReservationStatus.PENDING 
                || reservationStatus == ReservationStatus.CONFIRMED) {
            reservationStatus = ReservationStatus.CANCELED;
        }
    }
    public void startRental() {
        if(reservationStatus == ReservationStatus.CONFIRMED) {
            reservationStatus = ReservationStatus.IN_PROGRESS;
        }
    }
    public void completeRental() {
        if(reservationStatus == ReservationStatus.IN_PROGRESS) {
            reservationStatus = ReservationStatus.COMPLETED;
        }
    } 

    public int getId() {return id;}
    public User getUser() {return user;}
    public Vehicle getVehicle() {return vehicle;}
    public Date getStartDate() {return startDate;}
    public Date getEndDate() {return endDate;}
    public double getTotalAmount() {return totalAmount;}
    public ReservationStatus getReservationStatus() {return reservationStatus;}
}

class ReservationManager {
    private Map<Integer, Reservation> reservations; // Reservation ID (Key) -> Reservation (Value)

    public ReservationManager() {
        this.reservations = new HashMap<>();
    }
    public Reservation createReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }
    public boolean cancelReservation(int reservationId) {
        return reservations.remove(reservationId) != null;
    }
    public Reservation getReservation(int reservationId) {
        return reservations.get(reservationId);
    }
    public void startRental(int reservationId){
        Reservation reservation  = reservations.get(reservationId);
        if(reservation != null) {
            reservation.startRental();
        }
    }
    public void completeRental(int reservationId){
        Reservation reservation  = reservations.get(reservationId);
        if(reservation != null) {
            reservation.completeRental();
        }
    }
}
 
class CarRentalSystem {
    private static CarRentalSystem instance;
    final private List<CarStore> stores;  
    final private VehicleFactory vehicleFactory; 
    final private ReservationManager reservationManager;  
    final private PaymentProcessor paymentProcessor;  
    // private List<SystemObserver> observers;
    
    private CarRentalSystem() {
        this.stores = new ArrayList<>();
        this.vehicleFactory = new VehicleFactory();
        this.reservationManager = new ReservationManager();
        this.paymentProcessor = new PaymentProcessor();
    }
    public static synchronized CarRentalSystem getInstance() {
        if(instance == null) {
            instance = new CarRentalSystem();
        }
        return instance;
    }
    public void addStore(CarStore store) {
        stores.add(store);
    }
    public Vehicle findVehicle(String registrationNumber) {
        for(CarStore store : stores) {
            Vehicle vehicle = store.getVehicle(registrationNumber);
            if(vehicle != null) return vehicle;
        }
        return null;
    }
    public void startRental(int reservationId){
        reservationManager.startRental(reservationId);
    }
    public void completeRental(int reservationId){
        reservationManager.completeRental(reservationId);
    }
    public void cancelReservation(int reservationId){
        reservationManager.cancelReservation(reservationId);
    }
    public void removeStore(CarStore store) {
        stores.remove(store);
    }
    public Reservation reserveVehicle(
                                        User user,
                                        String registrationNumber,
                                        Date startDate,
                                        Date endDate,
                                        PaymentStrategy paymentStrategy
                                    ) {
        Vehicle vehicle = findVehicle(registrationNumber);
        if(vehicle == null) throw new RuntimeException("Vehicle not found");
        Reservation reservation = new Reservation(user, vehicle, startDate, endDate);
        double amount = reservation.calculateTotalAmount();
        boolean paymentSuccess = paymentProcessor.processPayment(amount, paymentStrategy);
        if(!paymentSuccess) throw new RuntimeException("Payment Failed");
        reservation.confirmReservation();
        reservationManager.createReservation(reservation);
        return reservation;
    }
}

public class Main {
    // public static void main(String[] args){
    //     CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();

    //     CarStore store1 = new CarStore("New York", "NY Car Rentals");
    //     CarStore store2 = new CarStore("Los Angeles", "LA Car Rentals");
    //     CarStore store3 = new CarStore("Chicago", "Chicago Car Rentals");

    //     carRentalSystem.addStore(store1);
    //     carRentalSystem.addStore(store2);
    //     carRentalSystem.addStore(store3);
    //     carRentalSystem.removeStore(store3);

    //     Vehicle vehicle1 = new Economy("NY1234", "Toyota Corolla", "REG1234", 50.0, VehicleStatus.AVAILABLE);
    //     Vehicle vehicle2 = new Luxury("NY5678", "Mercedes-Benz S-Class", "REG5678", 100.0, VehicleStatus.AVAILABLE);
    
    //     store1.addVehicle(vehicle1);
    //     store1.addVehicle(vehicle2);
        
        
    //     User user = new User("John Doe", "john@email.com");
    //     // User user2 = new User("Jane Smith", "jane@email.com");

    //     Date startDate = new Date();
    //      Date endDate = new Date(startDate.getTime() + (3L * 24 * 60 * 60 * 1000));

    //     Reservation reservation =
    //             carRentalSystem.reserveVehicle(
    //                     user,
    //                     "REG1234",
    //                     startDate,
    //                     endDate,
    //                     new CreditCardPayment()
    //             );

    //     System.out.println("Reservation Created: " + reservation.getId());
    //     System.out.println("Total Amount: " + reservation.getTotalAmount());

         
    //     carRentalSystem.startRental(reservation.getId());
    //     System.out.println("Rental Started");

         
    //     carRentalSystem.completeRental(reservation.getId());
    //     System.out.println("Rental Completed");
    // }

    public static void main(String[] args) {
       
        System.out.println("===== Car Rental System Test =====");

        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();

        // ---------------------------
        // 1. Create Stores
        // ---------------------------
        CarStore store1 = new CarStore("New York", "NY Car Rentals");
        CarStore store2 = new CarStore("Los Angeles", "LA Car Rentals");

        carRentalSystem.addStore(store1);
        carRentalSystem.addStore(store2);

        System.out.println("Stores added successfully.");

        // ---------------------------
        // 2. Create Vehicles
        // ---------------------------
        Vehicle vehicle1 = new Economy("NY1234", "Toyota Corolla", "REG1001", 50.0, VehicleStatus.AVAILABLE);
        Vehicle vehicle2 = new Luxury("NY5678", "Mercedes S-Class", "REG1002", 100.0, VehicleStatus.AVAILABLE);
        Vehicle vehicle3 = new SUV("NY9012", "Toyota Fortuner", "REG1003", 75.0, VehicleStatus.AVAILABLE);
        Vehicle vehicle4 = new SuperCar("LA1111", "Lamborghini Huracan", "REG2001", 200.0, VehicleStatus.AVAILABLE);

        store1.addVehicle(vehicle1);
        store1.addVehicle(vehicle2);
        store1.addVehicle(vehicle3);

        store2.addVehicle(vehicle4);

        System.out.println("Vehicles added to stores.");

        // ---------------------------
        // 3. Display Vehicles
        // ---------------------------
        System.out.println("\nVehicle Details:");
        vehicle1.displayVehicleInfo();
        vehicle2.displayVehicleInfo();
        vehicle3.displayVehicleInfo();
        vehicle4.displayVehicleInfo();

        // ---------------------------
        // 4. Create Users
        // ---------------------------
        User user1 = new User("John Doe", "john@email.com");
        User user2 = new User("Jane Smith", "jane@email.com");

        System.out.println("\nUsers created.");

        // ---------------------------
        // 5. Create Reservation
        // ---------------------------
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (2L * 24 * 60 * 60 * 1000));

        Reservation reservation1 =
                carRentalSystem.reserveVehicle(
                        user1,
                        "REG1001",
                        startDate,
                        endDate,
                        new CreditCardPayment()
                );

        System.out.println("\nReservation Created:");
        System.out.println("Reservation ID: " + reservation1.getId());
        System.out.println("Total Amount: $" + reservation1.getTotalAmount());

        // ---------------------------
        // 6. Start Rental
        // ---------------------------
        carRentalSystem.startRental(reservation1.getId());
        System.out.println("Rental started for reservation: " + reservation1.getId());

        // ---------------------------
        // 7. Complete Rental
        // ---------------------------
        carRentalSystem.completeRental(reservation1.getId());
        System.out.println("Rental completed for reservation: " + reservation1.getId());

        // ---------------------------
        // 8. Second Reservation
        // ---------------------------
        Reservation reservation2 =
                carRentalSystem.reserveVehicle(
                        user2,
                        "REG1002",
                        startDate,
                        endDate,
                        new CreditCardPayment()
                );

        System.out.println("\nSecond Reservation Created:");
        System.out.println("Reservation ID: " + reservation2.getId());
        System.out.println("Total Amount: $" + reservation2.getTotalAmount());

        // ---------------------------
        // 9. Cancel Reservation
        // ---------------------------
        carRentalSystem.cancelReservation(reservation2.getId());
        System.out.println("Reservation cancelled: " + reservation2.getId());

        // ---------------------------
        // 10. Test Vehicle Lookup
        // ---------------------------
        Vehicle foundVehicle = carRentalSystem.findVehicle("REG2001");

        if(foundVehicle != null){
            System.out.println("\nVehicle found in system:");
            foundVehicle.displayVehicleInfo();
        }

        // ---------------------------
        // 11. Remove Vehicle
        // ---------------------------
        store1.removeVehicle("REG1003");
        System.out.println("\nVehicle REG1003 removed from store1.");

        // ---------------------------
        // 12. Remove Store
        // ---------------------------
        carRentalSystem.removeStore(store2);
        System.out.println("Store2 removed from system.");

        System.out.println("\n===== Test Execution Completed ====="); 
        }
}