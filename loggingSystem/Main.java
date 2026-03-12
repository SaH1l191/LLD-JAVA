import java.util.*;

enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(4),
    FATAL(5);

    final private int level;
    LogLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
}

class Log {
    final private LogLevel level;
    final private long timestamp;
    final private String message;

    public Log(LogLevel level, String message) {
        this.level = level;
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }

    //getters
    public LogLevel getLevel() {return level;} 
    public long getTimestamp() {return timestamp;}
    public String getMessage() {return message;}
}

interface LogFormatter {
    String format(Log logEntry);
}
class SimpleLogFormatter implements LogFormatter {
    @Override
    public String format(Log logEntry) {
        return String.format("[%s] %s: %s", new Date(logEntry.getTimestamp()),logEntry.getLevel(), 
                logEntry.getMessage());
    }
}

interface LogAppender {
    void append(Log log);
}

class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;
    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }
    @Override
    public void append(Log log) {
        System.out.println("Appending log to console:");
        System.out.println(formatter.format(log));
    }
}

class FileAppender implements LogAppender {
    private final LogFormatter formatter;

    public FileAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(Log log) {
        System.out.println("Appending log to file:");
        System.out.println(formatter.format(log));
    }
}

class DatabaseAppender implements LogAppender {
    private final LogFormatter formatter;
    public DatabaseAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }
    @Override
    public void append(Log log) {
        System.out.println("Appending log to database:");
        System.out.println(formatter.format(log));
    }
}

//class LoggingSystem does the handling 
// abstract class LogHandler {
//     protected LogHandler nextHandler;
//     protected LogLevel level;
//     protected List<LogAppender> appenders;

//     //set this handler + level
//     public LogHandler(LogLevel level, List<LogAppender> appenders) {
//         this.level = level; 
//         this.appenders = appenders;
//     }

//     //set next handler in chain
//     public void setNextHandler(LogHandler nextHandler) {
//         this.nextHandler = nextHandler;
//     }

//     //check & pass responsibility
//     public void handle(Log log) {
//         if (log.getLevel().getLevel() >= level.getLevel()) {
//             write(log);
//             if (nextHandler != null) { nextHandler.handle(log);}
//         } 
//     }

//     //write actual log + can have varied implmentation for different handlers can be written asbtract 
//     protected void write(Log log){
//         for(LogAppender app : appenders) app.append(log);
//     }
// }
//instead of this create a generic class : prevents creating classes for new code again and again 
// class InfoLogger extends LogHandler {
//     public InfoLogger(List<LogAppender> appenders) {
//         super(LogLevel.INFO, appenders);
//     }
// }

// class DebugLogger extends LogHandler {
//     public DebugLogger(List<LogAppender> appenders) {
//         super(LogLevel.DEBUG, appenders);
//     }
// }

// class ErrorLogger extends LogHandler {
//     public ErrorLogger(List<LogAppender> appenders) {
//         super(LogLevel.ERROR, appenders);
//     }
// }
// class FatalLogger extends LogHandler {
//     public FatalLogger(List<LogAppender> appenders){
//         super(LogLevel.FATAL, appenders);
//     }
// }
// class LevelLogger extends LogHandler {
//     public LevelLogger(LogLevel level, List<LogAppender> appenders) {
//         super(level, appenders);
//     }
//     @Override
//     protected void write(Log log) {
//         for (LogAppender appender : appenders)
//             appender.append(log);
//     }
// }


class LoggingSystem {
    private static final LoggingSystem instance = new LoggingSystem();
    private final List<LogAppender> appenders = new ArrayList<>();
    private LogLevel threshold = LogLevel.DEBUG; // default

    private LoggingSystem() {}

    public static LoggingSystem getInstance() { return instance; }

    public void setThreshold(LogLevel level) { this.threshold = level; }
    public void addAppender(LogAppender appender) { appenders.add(appender); }

    // core logging method
    public void log(Log log) {
        if (log.getLevel().getLevel() >= threshold.getLevel()) {
            for (LogAppender appender : appenders) {
                appender.append(log);
            }
        }
    }

    public void debug(String msg) { log(new Log(LogLevel.DEBUG, msg)); }
    public void info(String msg) { log(new Log(LogLevel.INFO, msg)); }
    public void warning(String msg) { log(new Log(LogLevel.WARNING, msg)); }
    public void error(String msg) { log(new Log(LogLevel.ERROR, msg)); }
    public void fatal(String msg) { log(new Log(LogLevel.FATAL, msg)); }   
}

public class Main {

    public static void main(String[] args) {
        LoggingSystem logger = LoggingSystem.getInstance();

        LogFormatter formatter = new SimpleLogFormatter();
        logger.addAppender(new ConsoleAppender(formatter));
        logger.addAppender(new FileAppender(formatter));
        logger.addAppender(new DatabaseAppender(formatter));

        // dev level logging :  show logs above warning level
        logger.setThreshold(LogLevel.WARNING);

        logger.debug("This won't print"); 
        logger.info("This is info");     
        logger.warning("This is warning");
        logger.error("This is error");   
        logger.fatal("This is fatal");   
    }
}
