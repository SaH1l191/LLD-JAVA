

//generics allow reusable classes + methods 
// for better readability , maintainibility , reusability,  +

// Elimination of Type Casting:
// ○ Generics make errors to appear compile time than at run time 
// (It’s always better to know problems in your code at compile time rather than making your code fail at run time).

// Suppose you want to create an ArrayList that store name of students, and 
// if by mistake the programmer adds an integer object instead of a string, the 
// compiler allows it. But, when we retrieve this data from ArrayList, it causes problems at runtime.


//also supports multiple Types 
class Test<T,U>{
    public T obj ; 
    public U obj2;
    Test(T obj, U obj2){
        this.obj =obj;
        this.obj2= obj2;
    }
    T getObj(){
        return this.obj;
    } 
    U getObj2(){
        return this.obj2;
    }
}

public class Main{
    
    public static void main(String[] args) {
        Test NewTest = new Test<String,Integer>("Hello",21);
        System.out.println(NewTest.getObj());
        System.out.println(NewTest.getObj2());
    }
}