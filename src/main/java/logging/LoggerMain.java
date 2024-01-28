package logging;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerMain {
    private static Logger logger;

    /**
     * Logger initialization
     */
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

    /**
     *Creates a formatted message with date and time
     * @param message String
     * @return the formatted message with date and time
     */
    public static String MyMessage(String message){
        SimpleDateFormat dateBuilder = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String MyDate = dateBuilder.format(new Date());
        return MyDate + ": " + message;
    }

    /**
     * Logs an exception and prints the formatted message to the consol
     * @param e the exception to be logged
     */
    public static void loggerException(Exception e){
        logger.severe(MyMessage("Exception: " + e.getMessage()));
        e.printStackTrace();
    }
}