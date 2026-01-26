package State;



//too much coupling between the states and the context & violated openclose, readability,maintainability
// class TrafficLight {
//     private String color ; 
//     public TrafficLight() {
//         this.color = "RED" ; 
//     } 
//     public void next(){
//         if(color.equals("RED")){
//             color = "GREEN" ; 
//             System.out.println("Light is Green. You can go now.") ;
//         } else if (color.equals("GREEN")){
//             color = "YELLOW" ; 
//             System.out.println("Light is Yellow. Please slow down.") ;
//         } else if (color.equals("YELLOW")){
//             color = "RED" ;
//             System.out.println("Light is Red. Please stop.") ;
//         }
//     }
// }


// public class State {
//     public static void main(String[] args) {
//         TrafficLight light = new TrafficLight();
//         light.next();
//         light.next();
//         light.next();
//     }
// }



interface TrafficLightState {
    void next(TrafficLightContext context);
    String getColor();
}

class RedState implements TrafficLightState {
    public void next(TrafficLightContext context) {
        context.setState(new GreenState());
        System.out.println("Light is Green. You can go now.");
    }
    public String getColor() {
        return "RED";
    }
}
class GreenState implements TrafficLightState {
    public void next(TrafficLightContext context) {
        context.setState(new YellowState());
        System.out.println("Light is Yellow. Please slow down.");
    }
    public String getColor() {
        return "GREEN";
    }
}
class YellowState implements TrafficLightState {
    public void next(TrafficLightContext context) {
        context.setState(new RedState());
        System.out.println("Light is Red. Please stop.");
    }
    public String getColor() {
        return "YELLOW";
    }
}
class TrafficLightContext {
    private TrafficLightState state;
    public TrafficLightContext() {
        this.state = new RedState();
    }
    public void setState(TrafficLightState state) {
        this.state = state;
    }
    public void next() { 
        state.next(this);
    }
    public String getColor() {
        return state.getColor();
    }
}
 
public class State {
    public static void main(String[] args) { 
        TrafficLightContext light = new TrafficLightContext();
        light.next();
        light.next();
        light.next();
    }
}