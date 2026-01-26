package Visitor;

// class childPatient {
//     public void diagnosis(){

//     }
//     public void billing(){

//     }
// }
// class AdultPatient {
//     public void diagnosis(){

//     }
//     public void billing(){
        
//     }
// }
// class SeniorPatient {
//     public void diagnosis(){

//     }
//     public void billing(){
        
//     }
// }

// class Visitor {
//     public static void main(String[] args) {
//         Object patient = new AdultPatient();
//         if(patient instanceof AdultPatient){
//             ((AdultPatient)patient).billing();
//         }else if(){

//         }else if () {
            
//         }
//     }
// }

// Visitor pattern approach ( think in reverse doctor as visitor instead of patient )
interface Patient { 
    void accept(Viisitor visitor);
}

class ChildPatient implements  Patient{ 
    @Override
    public void accept(Viisitor visitor){
        visitor.visit(this);
    }
}
class AdultPatient implements  Patient{ 
    @Override
    public void accept(Viisitor visitor){
        visitor.visit(this);
    }
}
class SeniorPatient implements  Patient{ 
    @Override
    public void accept(Viisitor visitor){
        visitor.visit(this);
    }
}

interface  Viisitor{
    void visit(ChildPatient childPatient);
    void visit(AdultPatient childPatient);
    void visit(SeniorPatient childPatient);
}

class DiagnosisVisitor implements  Viisitor {
    @Override
    public void visit(ChildPatient childPatient){
        System.out.println("Diagnosing a child patient");
    }
    @Override
    public void visit(AdultPatient childPatient){
        System.out.println("Diagnosing a child patient");
    }
    @Override
    public void visit(SeniorPatient childPatient){
        System.out.println("Diagnosing a child patient");
    }
}
class BillingVisitor implements  Viisitor {
    @Override
    public void visit(ChildPatient childPatient){
        System.out.println("Billing a child patient");
    }
    @Override
    public void visit(AdultPatient childPatient){
        System.out.println("Billing a child patient");
    }
    @Override
    public void visit(SeniorPatient childPatient){
        System.out.println("Billing a child patient");
    }
}
 
class Visitor {
    public static void main(String[] args) {
        ChildPatient childPatient = new ChildPatient();
        DiagnosisVisitor diagnosisVisitor = new DiagnosisVisitor();
        BillingVisitor billingVisitor = new BillingVisitor();
        childPatient.accept(diagnosisVisitor);
        childPatient.accept(billingVisitor);
    }
}