import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAppender implements LogAppender{

    private final String jbcurl;
    private final String username;
    private final String password;

    public DatabaseAppender(String jbcurl, String username, String password){
        this.jbcurl = jbcurl;
        this.username = username;
        this.password = password;
    }

    @Override
    public void append(LogMessage message){
        try(Connection connection = DriverManager.getConnection(jbcurl,username,password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs(level,message,timestamp) VALUES (?,?,?)")){
            statement.setString(1, message.getLevel().toString());
            statement.setString(2,message.getMessaage());
            statement.setLong(3,message.getTimestamp());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
