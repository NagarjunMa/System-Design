import javax.management.remote.JMXServerErrorException;

public class Log {

    private static final Log instance = new Log();
    private LogConfig config;

    private Log(){
        config = new LogConfig(LogLevel.INFO, new ConsoleAppender());
    }

    public static Log getInstance(){
        return instance;
    }

    public void setConfig(LogConfig config){
        this.config = config;
    }

    public void log(LogLevel level, String message){
        if(level.ordinal() >= config.getLevel().ordinal()){
            LogMessage logMessage = new LogMessage(level, message);
            config.getAppender().append(logMessage);
        }
    }

    public void debug(String message){
        log(LogLevel.DEBUG,message);
    }

    public void info(String message){
        log(LogLevel.INFO,message);
    }

    public void warning(String message){
        log(LogLevel.WARNING,message);
    }

    public void error(String message){
        log(LogLevel.ERROR,message);
    }

    public void fatal(String message){
        log(LogLevel.FATAL,message);
    }


}
