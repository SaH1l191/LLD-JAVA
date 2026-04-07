
abstract class Beverage {

    abstract void recipe();

    abstract void addCondiments();

    final void prepareBeverage() {
        boilWater();
        recipe();
        pourInCup();
        addCondiments();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

class Coffee extends Beverage {

    @Override
    void recipe() {
        System.out.println("Brewing coffee grounds");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

class Tea extends Beverage {

    @Override
    void recipe() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}

class Template {

    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee.prepareBeverage(); 
        System.out.println(); 
        Beverage tea = new Tea();
        tea.prepareBeverage();
    }
}
