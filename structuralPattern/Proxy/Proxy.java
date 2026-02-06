
import java.util.HashMap;
import java.util.Map;


//acts as a proxy for another class 
// one sentence : 

// Use Proxy whenever you want to do something on behalf of another object while
//  keeping the original object’s code clean and focused

//interface for funcitionalites of main functionallity + the 
// proxy class overidding the same funcitonality ( ie, the interface )

interface VideoService {

    void playVideo(String userId, String videoName);
}

class RealVideoService implements VideoService {

    @Override
    public void playVideo(String userId, String videoName) {
        System.out.println("Playing video: " + videoName + " for user: " + userId);
    }
}

class ProxyVideoService implements VideoService {

    private RealVideoService realVideoService;
    private Map<String, String> cachedVideos = new HashMap<>();
    private Map<String, Integer> requestCounts = new HashMap<>();
    public ProxyVideoService() {
        this.realVideoService = new RealVideoService();
    }

    @Override
    public void playVideo(String userId, String videoName) {
        // Check user permissions
        if (!userId.equals("premium") && videoName.startsWith("Premium")) {
            System.out.println(
                    "Access denied: Premium video requires a premium account.");
            return;
        }

        // Limit requests
        requestCounts.put(userId, requestCounts.getOrDefault(userId, 0) + 1);
        if (requestCounts.get(userId) > 5) {
            System.out.println("Access denied: Too many requests.");
            return;
        }

        // Caching logic
        if (cachedVideos.containsKey(videoName)) {
            System.out.println("Streaming cached video: " + videoName);
        } else {
            realVideoService.playVideo(userId, videoName);
            cachedVideos.put(videoName, videoName);
        }
    }
}

class Proxy {

    public static void main(String[] args) {
        VideoService videoService = new ProxyVideoService();

        videoService.playVideo("regular", "FreeVideo1");
        videoService.playVideo("regular", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1");
        videoService.playVideo("premium", "PremiumVideo1"); 

    }
}
