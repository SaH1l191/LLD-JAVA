



class Animal { 
    void eat(){
        System.out.println("eating");
    }
}

//hierarchical  inheritance
class Dog extends Animal { 
    void eat(){
        System.out.println("dog walking");
    } 
}
class Cat extends Animal { 
    void eat(){
        System.out.println("cat eating");
    } 
}





public class Main{
    public static void main(String args[]){
        Animal a= new Animal();
        Dog d = new Dog();
        d.eat();



    }
}















