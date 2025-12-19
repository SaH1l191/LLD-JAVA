
// constructor can call other constructors using this keyword
// a claass can be overloaded with different constructors using 
// different parameters 


//copy constructor to avoid shallow copy and do deep copy 

// can constructor be final static or abstract ? 
// none - read in notes 


//when a subcass obj is created , parent constructor caled first
// then subcllas const... for proper initializzation 


//constructo canno be inherited , thoruhg super keyword we 
//get acces to parents construc.. anyways 

//constructo  can return but not value 
// like if else early validaiton to return early 



//when we create object witohut defining the parameters , 
// and we only define the parametrized constructor in the class 
// and provide no default constructore => that will throw 
// compilation error => need to define default constructor
// if we plan to use parametrized constructor 

//eg 1: Throws errro 
// class Example{
//     int a ;
//     int b ;

//     // only parametrized constructor defined & 
//     // used default cons.. in main 
//     //throws error 
//     public   Example(int a ,int b){
//         this.a = a;
//         this.b= b ;
//     }

//     public static void main(String[] args){
//         Example eg = new Example();
//         System.out.println(eg.a + " " + eg.b);
//     }
// }



//eg -2 
class Car{

    String manufacturer;
    String model;
    int year;

    // parametrized constructor
    public Car(String manufacturer, String model, int year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }
    
    //creates a deep copy not a shallow one 
    public Car(Car other){
        this.manufacturer = other.manufacturer;
        this.model = other.model;
        this.year = other.year;
    }

    public void startEngine() {
        System.out.println("starting engine");
    }
 
}

