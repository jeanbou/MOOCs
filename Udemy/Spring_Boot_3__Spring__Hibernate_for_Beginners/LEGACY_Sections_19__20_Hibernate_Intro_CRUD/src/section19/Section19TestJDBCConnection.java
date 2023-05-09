package section19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Section19TestJDBCConnection {

    public static void main(String[] args) {
        final String jdbcURL = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        final String user_pass_idential = "hbstudent";
        Connection connection =  null;
        try {
            System.out.println("Connecting to DB...");
            connection = DriverManager.getConnection(jdbcURL,user_pass_idential,user_pass_idential);
            System.out.println("Connected to DB!");
        }
        catch (Exception e) {
           e.printStackTrace(); 
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
