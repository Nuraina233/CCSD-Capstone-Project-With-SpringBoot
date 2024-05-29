//Hold

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbconfig.DBConfig;

public class CreateDbTable {

    public static void createTable() throws SQLException {
        //Database name
        String dbName = "ecommerce";

        //Table name and column (replace as needed)
        String tableName = "new_table";

        Connection connection = null;
        Statement statement = null;

        try{
            //Access connection details from DatabaseConfig file
            String jdbcUrl = DBConfig.JDBC_URL;
            String username = DBConfig.USERNAME;
            String password = DBConfig.PASSWORD;

            //Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //Create a statement object (can user PreparedStatement)
            statement = connection.createStatement();

            //Check if the db exists
            boolean dbExists = checkDatabaseExists(connection, dbName);

            if(!dbExists){
                System.out.println("Database " +dbName +" does not exist. Creating...");
                statement.execute("CREATE DATABASE " +dbName);
                System.out.println("Database '" +dbName +"' has been created.");
            }else {
                System.out.println("Database '" +dbName +"' already exists.");
            }

            //Create the table role
            String createTableSQL = "CREATE TABLE IF NOT EXISTS roles (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "name VARCHAR(15) NOT NULL" +
                    ")";
            statement.execute(createTableSQL);
            System.out.println("Table '" +tableName +"' has been created");

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //Close connection
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    private static boolean checkDatabaseExists(Connection connection, String dbName)throws SQLException{
        String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dbName);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
