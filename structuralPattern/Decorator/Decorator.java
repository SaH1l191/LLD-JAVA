



interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
    public double getCost() {
        return 2.0;
    }
}

class Espresso implements Coffee {
    public String getDescription() {
        return "Espresso";
    }
    public double getCost() {
        return 3.0;
    }
}

abstract class CoffeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

class MilkDecorator extends CoffeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
    public double getCost() {
        return decoratedCoffee.getCost() + 0.2;
    }
}


class Decorator{
    public static void main(String[] args) {
        Espresso espresso = new Espresso();
        Coffee espressoWithMilk = new MilkDecorator(espresso);
        Coffee espressoWithMilkAndSugar = new SugarDecorator(espressoWithMilk);
        System.out.println(espressoWithMilkAndSugar.getDescription() + " $" + espressoWithMilkAndSugar.getCost());
    }
}