public class LogConfig {
    private final LogLevel level;
    private final LogAppender appender;


    public LogConfig(LogLevel level, LogAppender appender){
        this.level = level;
        this.appender = appender;
    }

    public LogLevel getLevel() {
        return level;
    }

    public LogAppender getAppender() {
        return appender;
    }
}
