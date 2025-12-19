 
public class Main{
    public static void main(String args[]){
        Car c1 = new Car("dfs","sdf",123);
        c1.startEngine();


        Car c2=  new Car("maruti","swift",23423);
        Car c3 = new Car(c2); // deep copy 

        System.out.println(c3.model == c2.model);//true
    }
}
