public class LogMessage {
    private final LogLevel level;
    private final String messaage;
    private final long timestamp;

    public LogMessage(LogLevel level, String messaage){
        this.level = level;
        this.messaage = messaage;
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessaage() {
        return messaage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return "[ "+ level +" ]" + timestamp + " - " + messaage;
    }
}
