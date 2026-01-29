
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class ParticleType {
    private String sprite;
    private String color; 

    public ParticleType(String sprite, String color) {
        this.sprite = sprite;
        this.color = color; 
    }

    public void display(int x, int y) {
        System.out.println("Displaying particle '" + sprite + "' of color '" + color + "' at (" + x + ", " + y + ")");
    }
}

class ParticleFactory {
    private Map<String, ParticleType> particleTypes = new HashMap<>();
    public ParticleType getParticleType(String sprite, String color) {
        String key = sprite + "-" + color;
        if (!particleTypes.containsKey(key)) {
            particleTypes.put(key, new ParticleType(sprite, color));
        }
        return particleTypes.get(key);
    }
}

class Particle {
    private int x;
    private int y;
    private ParticleType type;

    public Particle(int x, int y, ParticleType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);
    }
}


class Flyweight {
    public static void main(String[] args){
        ParticleFactory factory = new ParticleFactory();
        List<Particle> particles = new ArrayList<>();
        ParticleType explosionType = factory.getParticleType("red", "explosion.png");
        for(int i=1;i<=30;i++){
            ParticleType type = factory.getParticleType("sparkle", "red");
            Particle particle = new Particle((int)(Math.random()*100), (int)(Math.random()*100), type);
            particles.add(particle);
        }
        for(Particle p : particles){
            p.display();
        }
    }
}