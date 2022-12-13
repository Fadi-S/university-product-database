import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    public static Connection get()
    {
        try
        {
            return DriverManager.getConnection("jdbc:sqlite:database.db");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
//            ResultSet rs = statement.executeQuery("select * from person");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
