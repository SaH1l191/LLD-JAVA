



// Facade = one simple button/interface that hides a lot of messy work behind it

// You don’t remove complexity.
// You hide it behind a clean interface.


// Imagine you come home and want to “Relax Mode”:
// You must manually:
// Turn off lights
// Set AC to 24°C
// Close curtains
// Turn on music
// Arm security system
// Each device has:
// Different apps
// Different APIs
// Different setup steps
// You (client) are directly controlling subsystems → chaos.
// With Facade (Luxury Life 😌)
// You press one button:
// 👉 “Relax Mode”
// Behind the scenes:
// Lights subsystem → dim lights
// AC subsystem → set temperature
// Curtains subsystem → close
// Music subsystem → play playlist
// Security subsystem → partial lock
// You don’t care how it happens.
// That single button is the Facade.

// The Facade Design Pattern is designed to solve this problem by providing a 
// unified interface to a set of interfaces in a subsystem. It simplifies the 
// interaction between the client and the subsystems, reducing complexity and 
// decoupling the client from subsystem implementations.

class MusicPlayer {
    public void initializeAudioDrivers(){
        System.out.println("Audio Drivers Initialized");
    }
    public void decodeAudio(){
        System.out.println("Audio Decoded");
    }
    public void startPlayback(){
        System.out.println("Playback Started");
    }
}
class VideoPlayer  {
    public void setupRenderingEngine(){
        System.out.println("Rendering Engine Setup");
    }
    public void loadVideoFile(){
        System.out.println("Video File Loaded");
    }
    public void playVideo(){
        System.out.println("Video Playing");
    }
}

class ImageViewer  {
    public void applyScaling(){
        System.out.println("Scaling Applied");
    }
    public void loadImageFile(){
        System.out.println("Image File Loaded");
    }
    public void displayImage(){
        System.out.println("Image Displayed");
    }
}

class MultiMediaFacade {
    private MusicPlayer musicPlayer;
    private VideoPlayer videoPlayer;
    private ImageViewer imageViewer; 

    public MultiMediaFacade() {
        this.musicPlayer = new MusicPlayer();
        this.videoPlayer = new VideoPlayer();
        this.imageViewer = new ImageViewer();
    }
    
    public void performAction(String input){
        switch(input){
            case "music":
                musicPlayer.initializeAudioDrivers();
                musicPlayer.decodeAudio();
                musicPlayer.startPlayback();
                break;
            case "video": 
                videoPlayer.setupRenderingEngine();
                videoPlayer.loadVideoFile();
                videoPlayer.playVideo();
                break;
            case "image": 
                imageViewer.applyScaling();
                imageViewer.loadImageFile();
                imageViewer.displayImage();
                break;
            default:
                System.out.println("Unsupported media type");
                break;
        }
    }
    
}
class  Facade {
    public static void main(String[] args) {
        MultiMediaFacade facade = new MultiMediaFacade();
        facade.performAction("music");
        facade.performAction("video");
        facade.performAction("image");
    }
}