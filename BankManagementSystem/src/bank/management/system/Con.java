package bank.management.system;
import  java.sql.*;

public class Con implements AutoCloseable {
    Connection connection;
    public Statement statement;

    // Constructor to establish database connection and create a statement
    public Con() {
        try {
            // Establishing connection to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankManagement", // Database URL
                    "masum",                                      // Username
                    "Masum@2004"                                  // Password
            );

            // Creating a statement for executing SQL queries
            statement = connection.createStatement();
        } catch (Exception e) {
            // Print stack trace in case of connection failure
            e.printStackTrace();
        }
    }

    // AutoCloseable implementation to release resources
    @Override
    public void close() {
        try {
            // Close the statement if it's not null
            if (statement != null) {
                statement.close();
            }

            // Close the connection if it's not null
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            // Print stack trace in case of any errors during resource release
            e.printStackTrace();
        }
    }
}


 /*-> this one library replace import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;*/
/*

public class Con {
    Connection connection;
    Statement statement;
    public Con(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankManagement", "masum", "Masum@2004");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

*/
