package logging;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerMain {
    private static Logger logger;

    static {
        logger = Logger.getLogger("LogUti");
        ConsoleHandler consoleWriter = new ConsoleHandler();
        consoleWriter.setLevel(Level.ALL);
        logger.addHandler(consoleWriter);
        logger.setLevel(Level.ALL);
    }
    public static Logger getLogger(){
        return logger;
    }

    public static String MyMessage(String message){
        SimpleDateFormat dateBuilder = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String MyDate = dateBuilder.format(new Date());
        return MyDate + ": " + message;
    }

    public static void loggerException(Exception e){
        logger.severe(MyMessage("Exception: " + e.getMessage()));
        e.printStackTrace();
    }
}
