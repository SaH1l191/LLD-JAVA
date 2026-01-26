package ChainOfResponsibility;

// class LeaveRequestUgly {

//     public void processRequest(int leaveDays) {
//         if (leaveDays <= 3) {
//             System.out.println("Supervisor approved the leave.");
//         } else if (leaveDays <= 7) {
//             System.out.println("Manager approved the leave.");
//         } else if (leaveDays <= 14) {
//             System.out.println("Director approved the leave.");
//         } else {
//             System.out.println("Leave request denied. Too many days!");
//         }
//     }
// }

// public class Chain {

//     public static void main(String[] args) {
//         LeaveRequestUgly request = new LeaveRequestUgly();
//         request.processRequest(10);
//     }
// }



abstract class Approval {
    protected Approval nextApprover;
    public void setNextApprover(Approval nextApprover) {
        this.nextApprover = nextApprover;
    }
    public abstract void approveRequest(int leaveDays);
}
class Supervisor extends Approval {
    public void approveRequest(int leaveDays) {
        if (leaveDays <= 3) {
            System.out.println("Supervisor approved the leave.");
        } else if (nextApprover != null) {
            nextApprover.approveRequest(leaveDays);
        }
    }
}
class Manager extends Approval {
    public void approveRequest(int leaveDays) {
        if (leaveDays <= 7) {
            System.out.println("Manager approved the leave.");
        } else if (nextApprover != null) {
            nextApprover.approveRequest(leaveDays);
        }else{
            System.out.println("Leave request denied. Too many days!");
        }
    }
}


public class Chain {

    public static void main(String[] args) { 
        Approval supervisor = new Supervisor();
        Approval manager = new Manager();
        // You can add more approvers like Director here
        supervisor.setNextApprover(manager);
        // manager.setNextApprover(director);
        int leaveDays = 10;
        supervisor.approveRequest(leaveDays);
    }
}