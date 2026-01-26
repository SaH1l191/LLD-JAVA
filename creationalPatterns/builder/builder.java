package builder;
class Car {

    private String model;
    private String color;
    private int year;

    private Car(CarBuilder builder) {
        this.model = builder.model;
        this.color = builder.color;
        this.year = builder.year;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public static class CarBuilder {

        private String model = "Default Model";
        private String color = "White";
        private int year = 2026;

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class builder {

    public static void main(String[] args) {
        Car.CarBuilder builder = new Car.CarBuilder().setColor("red").setModel("2021");
        Car c1 = builder.build();
        System.out.println("Car Model: " + c1.getModel());
        System.out.println("Car Color: " + c1.getColor());
    }
}
