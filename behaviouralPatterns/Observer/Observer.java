package Observer;


//naive way 

// class YoutubeChannel {
//     private String name;
//     private List<String> subscribers = new ArrayList<>();
//     public YoutubeChannel(String name) {
//         this.name = name;
//     }
//     public void addSubscriber(String subscriberName) {
//         subscribers.add(subscriberName);
//         System.out.println(subscriberName + " has subscribed to " + name);
//     }
//     public void addNewVideo(String videoTitle) {
//         System.out.println("New video uploaded: " + videoTitle);
//         notifySubscribers(videoTitle);
//     }
//     public void notifySubscribers(String videoTitle) {
//         for (String subscriber : subscribers) {
//             System.out.println("Notifying " + subscriber + " about new video: " + videoTitle);
//         }
//     }
// }
// class YoutubeSubscriber {
//     private String name;
//     public YoutubeSubscriber(String name) {
//         this.name = name;
//     }
//     public void subscribeToChannel(YoutubeChannel channel) {
//         channel.addSubscriber(name);
//     }
//     public void watchVideo(String videoTitle) {
//         System.out.println(name + " is watching: " + videoTitle);
//     }
// }
// public class Observer {
//     public static void main(String[] args) {
//         YoutubeChannel techChannel = new YoutubeChannel("Tech Channel");
//         YoutubeSubscriber alice = new YoutubeSubscriber("Alice");
//         YoutubeSubscriber bob = new YoutubeSubscriber("Bob"); 
//         alice.subscribeToChannel(techChannel);
//         bob.subscribeToChannel(techChannel); 
//         techChannel.addNewVideo("Observer Pattern in Java");
//         alice.watchVideo("Observer Pattern in Java");
//         bob.watchVideo("Observer Pattern in Java");
//     }
// }
//refactored code :
interface Subscriber {
    void update();
}

class YoutubeSubscriber implements Subscriber {
    private String name;
    public YoutubeSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void update() {
        System.out.println(name + " has been notified of a new video.");
    }
}
class EmailSubscriber implements Subscriber {
    private String name;
    public EmailSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void update() {
        System.out.println(name + " has been notified of a new video.");
    }
}

public class Observer {

    public static void main(String[] args) {

    }
}





// observer pattern ! 

// Obesrver 

// import java.util.ArrayList;
// import java.util.List;

// interface Subscriber {
//     void update();
// }

// class PushNotificationSubscriber implements Subscriber {

//     private String name;

//     public PushNotificationSubscriber(String name) {
//         this.name = name;
//     }
//     @Override
//     public void update() {
//         System.out.println("Push Notification sent to " + name);
//     }
// }

// class EmailNotificationSubscriber implements Subscriber {

//     private String name;

//     public EmailNotificationSubscriber(String name) {
//         this.name = name;
//     }
//     @Override
//     public void update() {
//         System.out.println("Email Notification sent to " + name);
//     }
// }

// interface YoutubeChannel {

//     void subscribe(Subscriber subscriber);
//     void unsubscribe(Subscriber subscriber);
//     void notifySubscribers();
// }

// class YoutubeChannelUtil implements YoutubeChannel {

//     private List<Subscriber> subscribers =new ArrayList<>() ; 
//     @Override
//     public void subscribe(Subscriber subscriber) {
//         subscribers.add(subscriber);
//     }

//     @Override
//     public void unsubscribe(Subscriber subscriber) {
//         subscribers.remove(subscriber);
//     }

//     @Override
//     public void notifySubscribers() {
//     for (Subscriber subscriber : subscribers) {
//             subscriber.update();
//         }
//     }
// }

// public class Observer {

//     public static void main(String[] args) {
//         Subscriber pushSubscriber = new PushNotificationSubscriber("Alice");
//         Subscriber emailSubscriber = new EmailNotificationSubscriber("Bob");
//         YoutubeChannelUtil channel = new YoutubeChannelUtil();
//         channel.subscribe(pushSubscriber);
//         channel.subscribe(emailSubscriber);
//         channel.notifySubscribers();
//         channel.unsubscribe(pushSubscriber);
//         channel.notifySubscribers();

//     }
// }



