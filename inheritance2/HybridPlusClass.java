class Animal {
    void eat() {
        System.out.println("eating");
    }

    void sound() {   
        System.out.println("generic animal sound");
    }
}

interface Dog {
    void dogsound();   
}

interface Cat {
    void catsound();   
}

class Smart extends Animal implements Dog, Cat {
    @Override
    public void sound() {
        System.out.println("hybrid animal sound");
    }

    @Override
    public void eat() {
        System.out.println("hybrid animal eating");
    }

    @Override
    public void dogsound() {
        System.out.println("dog barking");
    }

    @Override
    public void catsound() {
        System.out.println("cat meowing");
    }
}

public class HybridPlusClass {
    public static void main(String[] args) {
        Smart hybrid = new Smart();
        hybrid.sound();   
        hybrid.eat();     
        hybrid.dogsound();   
        hybrid.catsound(); 
    }
}

// inheritacnce adv : code reusablility , extensiablity ,
// overloading .


//disadv : 
// increased coupling   : cannot change modify parent , chldren will break 


// compleixty 




