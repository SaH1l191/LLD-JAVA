 


//wildcards in java => 
// 🌪️ What Are Wildcards in Generics?
// Wildcards are special symbols used in generics to represent an unknown type. 
// They provide flexibility when working with generic types and allow developers to define relationships between different types.


// represented as  ?  => eg :  = class Test<?>
// main distinction btwn wildcard and generics is that logic isnt concerned with the types in wildcard 

// types of wilcard : 
// 1. Unbounded Wildcard (?) : 
// • Represents an unknown type.
// • Useful when the type is not relevant to the logic.


// 2. Upper-Bounded Wildcard (? extends Type) : 
// • Restricts the type to Type or its subclasses.
// • Useful for read-only operations where the specific type is not required
// EG : 
// public class UpperBoundedWildcardExample {
//   public static void printNumbers(List<? extends Number> list) {
//     for (Number number : list) {
//       System.out.println(number);
//     }
//   }

//   public static void main(String[] args) {
//     List<Integer> intList = List.of(1, 2, 3);
//     printNumbers(intList);
//     List<Double> doubleList = List.of(1.1, 2.2, 3.3);
//     printNumbers(doubleList);
//     List<String> stringList = List.of("a", "b", "c");
//     printNumbers(stringList);
//     // Error: incompatible types: List<String> cannot be converted to List<?
//     // extends Number>
//   }
// }




// Upper Bound (? extends) — READ ONLY
// Code eg:
// public class UpperBoundExample {

//     // Accepts List of Number OR any subclass of Number
//     static void readNumbers(List<? extends Number> list) {

//         // Reading is allowed
//         Number n = list.get(0);
//         System.out.println(n);

//         //  Writing is NOT allowed
//         // list.add(10);       // compile-time error
//         // list.add(3.14);     // compile-time error
//     }

//     public static void main(String[] args) {
//         List<Integer> ints = List.of(1, 2, 3);
//         List<Double> doubles = List.of(1.1, 2.2);

//         readNumbers(ints);     // OK
//         readNumbers(doubles);  // OK
//     }
// }
// Why this works
// ? extends Number means “some unknown subtype of Number”
// Java guarantees reading is safe
// Java blocks adding to prevent type mismatch
//  Key idea:
// You don’t know the exact type → you can’t safely add.


// Lower Bound (? super) — WRITE ONLY
// Code eg:
// public class LowerBoundExample {

//     // Accepts Integer OR any parent of Integer
//     static void addIntegers(List<? super Integer> list) {

//         //  Writing is allowed
//         list.add(10);
//         list.add(20);

//         //  Reading as Integer is NOT allowed
//         // Integer i = list.get(0);  // compile-time error

//         // You can read as Object
//         Object obj = list.get(0);
//         System.out.println(obj);
//     }

//     public static void main(String[] args) {
//         List<Integer> ints = new ArrayList<>();
//         List<Number> nums = new ArrayList<>();
//         List<Object> objs = new ArrayList<>();

//         addIntegers(ints);   // OK
//         addIntegers(nums);   // OK
//         addIntegers(objs);   // OK
//     }
// }
// Why this works
// ? super Integer means “Integer or its parent”
// Parents can safely store Integers
// But Java only promises it’s an Object when reading
//  Key idea:
// You can add safely → but reading loses type information.



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