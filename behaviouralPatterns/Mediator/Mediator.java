package Mediator;

import java.util.ArrayList;
import java.util.List;


// messy way  
// class Bidder { 
//     private String name;
//     public int amount ; 
//     public Bidder(String name){
//         this.name = name;
//     }
//     void receiveBid(int amount){
//         System.out.println(name + " is notified: " + this.name +" placed a bid of " + amount);
//     }
//     public void placeBid(Bidder[] bidders,int amount){
//         System.err.println("" + this.name + " places a bid of " + amount);
//         for(Bidder b : bidders){
//             if(b != this){
//                 b.receiveBid(amount);
//             } 
//         }
//     }
// }


// class Mediator {
//     public static void main(String[] args) {
//             Bidder alice = new Bidder("Alice");
//             Bidder bob = new Bidder("Bob");
//             Bidder charlie = new Bidder("Charlie");
//             Bidder[] bidders = {alice, bob, charlie};
//             alice.placeBid(bidders, 100);
//     }
// } 



interface AuctionMediator {
    void placeBid(Bidder bidder, int amount);
    void registerBidder(Bidder bidder);
}

class AuctionHouse implements AuctionMediator {
    private List<Bidder> bidders = new ArrayList<>();

    @Override
    public void registerBidder(Bidder bidder) {
        bidders.add(bidder);
    }
    @Override
    public void placeBid(Bidder bidder, int amount) {
        System.out.println(bidder.getName() + " places a bid of " + amount);
        for (Bidder b : bidders) {
            if (b != bidder) {
                b.receiveBid(bidder.getName(), amount);
            }
        }
    }
}
class Bidder {
    private String name;
    private AuctionMediator mediator;
    public Bidder(String name, AuctionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.registerBidder(this);
    }

    public String getName() {
        return name;
    }
    public void receiveBid(String bidderName, int amount) {
        System.out.println(name + " is notified: " + bidderName + " placed a bid of " + amount);
    }
    public void placeBid(int amount) {
        mediator.placeBid(this, amount);
    }
}
//now even if we want to extend the functionality of the mediator we can creat a new class (child class)
// of the base class ( auction house ) 
public class Mediator {
    public static void main(String[] args) {
        AuctionMediator auctionHouse = new AuctionHouse();
        Bidder alice = new Bidder("Alice", auctionHouse);
        Bidder bob = new Bidder("Bob", auctionHouse);
        Bidder charlie = new Bidder("Charlie", auctionHouse);
        alice.placeBid(100);
    }
}

