
import java.util.ArrayList;
import java.util.List;



interface LogObserver {
    void update(String message);
}

class FileLogger implements LogObserver {
    @Override
    public void update(String message) {
        System.out.println("Logging to FILE: " + message);
    }
}

class ConsoleLogger implements LogObserver {
    @Override
    public void update(String message) {
        System.out.println("Logging to CONSOLE: " + message);
    }
}

class CloudLogger implements LogObserver {
    @Override
    public void update(String message) {
        System.out.println("Logging to CLOUD: " + message);
    }
}

interface LoggerSubject {
    void addObserver(LogObserver observer);
    void removeObserver(LogObserver observer);
    void notifyObservers(String message);
}

class Logger  implements LoggerSubject {

    private List<LogObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(LogObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(LogObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (LogObserver observer : observers) {
            observer.update(message);
        }
    }

    public void log(String message) {
        notifyObservers(message);
    }
}



class C{
    public static void main(String[] args) {
        Logger logger = new Logger();

        logger.addObserver(new FileLogger());
        logger.addObserver(new ConsoleLogger());
        logger.addObserver(new CloudLogger());
        logger.log("ERROR: Database connection failed");
    }
}