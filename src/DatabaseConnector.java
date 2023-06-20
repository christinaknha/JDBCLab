import java.sql.*;

public class DatabaseConnector {

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }

    public Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:\\C:\\CTAC_Cohort5\\FullStack_104\\SQLiteAssignments\\Bookstore.db";
            connection = DriverManager.getConnection(url);

            System.out.println("Successfully connected to the database!");
        } catch (SQLException sqle) {
            System.out.println("Error connecting to the database.");
            sqle.printStackTrace();
        }
        return connection;
    }

    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.connect();
            String sql = "Select * from Books";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("Title") + "\t"
                        + rs.getString("Author") + "\t" + rs.getDouble("Price"));
            }
        } catch (SQLException sqle) {
            System.out.println("Error executing SELECT statement");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}