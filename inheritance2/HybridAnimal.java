//multiple inheritance : not csuppport in java  
// creates a diamond problem 
// if animal
//   /  \ 
// dog  cat 
//soun() soun()
//    /  \ 
//     hybrid
//   hybrid.soun() which function >? 

// this is implementable via interfcaes 

 
interface Dog{
    void soun();
}
interface Cat{
    void soun();
} 

public class HybridAnimal implements Dog,Cat{
    @Override
    public void soun() {
        System.out.println("hybrid animal sound");
    }

    public static void main(String[] args) {
        HybridAnimal hybrid = new HybridAnimal();
        hybrid.soun();
    }
}