package singleton;

class Logger {

    private static volatile Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
}

public class Singleton { 
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance(); 
        System.out.println(logger1 == logger2);   
    }
}
