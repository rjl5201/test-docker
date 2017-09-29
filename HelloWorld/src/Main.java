import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Boolean isDocker = false;
        isDocker = System.getenv("SERVICE_NAME")!=null;

        if(isDocker) {
            String postgresUrl = System.getenv("DATASOURCE_JDBC_URL");
            String username = System.getenv("DATASOURCE_USER");
            String password = System.getenv("DATASOURCE_PASSWORD");

            createTable(postgresUrl, username, password);
        }
        else {
            String postgresUrl = "jdbc:postgresql://localhost:5432/";
            createTable(postgresUrl, "postgres", "postgres");
        }

    }

    public static Boolean createTable(String postgresUrl, String username, String password) {
        Boolean wasCreated = false;
        Connection conn = null;
        Statement stmt = null;

        System.out.println("Going to connect to: " + postgresUrl);
        try {
            Thread.sleep(5000);
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(postgresUrl,"postgres", "postgres");

            stmt = conn.createStatement();
            String sql = "CREATE TABLE EMPLOYEES " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            wasCreated = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Was I successful in my endeavors? " + wasCreated);

        return wasCreated;
    }
}
