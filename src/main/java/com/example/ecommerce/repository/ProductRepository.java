package com.example.ecommerce.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.ecommerce.models.Stocks;
import dbconfig.DBConfig;
import com.example.ecommerce.models.Products;

public class ProductRepository {
    public static void main(String[] args) throws SQLException {
        //getProductList();

        //getProductEditList(3);

        //getUpdateProduct(1,"ADIZERO TAKUMI SEN 10 SHOES","https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/b698e5b811a440ef91f2b1bf",750,
        //        "Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.",
        //        "Core Black","Sport Shoes","Male");

        //getInsertProduct("ADIZERO TAKUMI SEN 10 SHOES","https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/b698e5b811a440ef91f2b1bf",750,
        //        "Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.",
        //        "Core Black","Sport Shoes","Male");

        //getDeleteProduct(44);

        //getStockProduct(1);

        //getRemoveStock(1520);

        getInsertStock(4,40.5,100);
    }

    //all products
    static List<Products> productsList = new ArrayList<>();

    //all stocks of product Id
    static List<Stocks> stocksList = new ArrayList<>();

    static String jdbcUrl = DBConfig.JDBC_URL;
    static String username = DBConfig.USERNAME;
    static String password = DBConfig.PASSWORD;

    //Database table and column names for products
    static String tableName = "products";
    static String idColumn = "product_id";
    static String nameColumn = "product_name";
    static String imageColumn = "product_image";
    static String priceColumn = "price";
    static String descColumn = "description";
    static String colorColumn = "color";
    static String categoryColumn = "category";
    static String genderColumn = "gender";

    //Database table and column names for stocks
    static String tableStock = "stocks";
    static String stockIdColumn = "stock_id";
    static String productIdColumn = "product_id";
    static String sizeColumn = "size";
    static String stockColumn = "stock";

    //Initialize
    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    //retrieve products from database
    public static List<Products> getProductList() throws SQLException {
        productsList.clear();

        try{
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl,username,password);

            //create a statement
            statement = connection.createStatement();

            //execute the query to retrieve data
            resultSet = statement.executeQuery("SELECT * FROM "+tableName);

            //print data
            while(resultSet.next()){
                int id = resultSet.getInt(idColumn);
                String name = resultSet.getString(nameColumn);

                //byte[] blob = resultSet.getBytes(blobColumn);
                String image = resultSet.getString(imageColumn);
                double price = resultSet.getDouble(priceColumn);
                String desc = resultSet.getString(descColumn);
                String color = resultSet.getString(colorColumn);
                String category = resultSet.getString(categoryColumn);
                String gender = resultSet.getString(genderColumn);
                Products products =  new Products(id, name, image, price, desc, color, category, gender);
                productsList.add(products);
            }

            System.out.println("Retrieve a list of products");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return productsList;
    }


    //retrieve the product of specific product id
    public static Products getEditProduct(int passId) throws SQLException {
        Products products = null;
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "SELECT * FROM " + tableName + " WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            //execute the query to retrieve data
            resultSet = preparedStatement.executeQuery();

            //print data
            while (resultSet.next()) {
                int id = passId;
                String name = resultSet.getString(nameColumn);

                String image = resultSet.getString(imageColumn);
                double price = resultSet.getDouble(priceColumn);
                String desc = resultSet.getString(descColumn);
                String color = resultSet.getString(colorColumn);
                String category = resultSet.getString(categoryColumn);
                String gender = resultSet.getString(genderColumn);
                products = new Products(id, name, image, price, desc, color, category, gender);
            }

            System.out.println("Retrieve Product by product id " +passId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return products;
    }

    //update product by id
    public static void getUpdateProduct(int passId, String nameToUpdate, String imageToUpdate,
                        double priceToUpdate, String descToUpdate, String colorToUpdate,
                        String catToUpdate, String genderToUpdate) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "UPDATE " + tableName + " SET " +
                    nameColumn + " = ? ," +imageColumn + " = ? ," +priceColumn + " = ? ," +descColumn + " = ? ," +
                    colorColumn + " = ? ," +categoryColumn + " = ? ," +genderColumn+ " = ? " +
                    " WHERE " + idColumn + " = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for all columns' placeholder
            preparedStatement.setString(1, nameToUpdate);
            preparedStatement.setString(2, imageToUpdate);
            preparedStatement.setDouble(3, priceToUpdate);
            preparedStatement.setString(4, descToUpdate);
            preparedStatement.setString(5, colorToUpdate);
            preparedStatement.setString(6, catToUpdate);
            preparedStatement.setString(7, genderToUpdate);
            preparedStatement.setInt(8, passId);

            // Execute the UPDATE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No records updated!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //insert product into database
    public static void getInsertProduct(String nameToInsert, String imageToInsert,
                                        double priceToInsert, String descToInsert, String colorToInsert,
                                        String catToInsert, String genderToInsert) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "INSERT INTO " + tableName + " (" +
                    idColumn + "," +nameColumn + "," + imageColumn + "," + priceColumn + "," +
                    descColumn + "," + colorColumn + "," + categoryColumn + "," + genderColumn+
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for all columns' placeholder
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, nameToInsert);
            preparedStatement.setString(3, imageToInsert);
            preparedStatement.setDouble(4, priceToInsert);
            preparedStatement.setString(5, descToInsert);
            preparedStatement.setString(6, colorToInsert);
            preparedStatement.setString(7, catToInsert);
            preparedStatement.setString(8, genderToInsert);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

    //delete product by id from database
    public static void getDeleteProduct(int passId) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            // Execute the DELETE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were deleted
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("No records deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //retrieve the stocks of specific product id
    public static List<Stocks> getStockProduct(int passId) throws SQLException {
        stocksList.clear();
        Stocks stocks = null;
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "SELECT * FROM " + tableStock + " WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            //execute the query to retrieve data
            resultSet = preparedStatement.executeQuery();

            //print data
            while (resultSet.next()) {
                int stockId = resultSet.getInt(stockIdColumn);
                int productId = resultSet.getInt(productIdColumn);
                double size = resultSet.getDouble(sizeColumn);
                int stock = resultSet.getInt(stockColumn);
                stocks = new Stocks(stockId, productId, size, stock);
                stocksList.add(stocks);
            }

            System.out.println("Retrieve Stock by product id " +passId);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return stocksList;
    }

    //delete product by id from database
    public static void getRemoveStock(int passId) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "DELETE FROM " + tableStock + " WHERE " + stockIdColumn + " = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            // Execute the DELETE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were deleted
            if (rowsAffected > 0) {
                System.out.println("Stock removed successfully!");
            } else {
                System.out.println("No records deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //insert product into database
    public static void getInsertStock(int prodIdToInsert, double sizeToInsert, int qtyToInsert) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "INSERT INTO " + tableStock + " (" +
                    productIdColumn + "," +sizeColumn + "," + stockColumn +
                    ") VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for all columns' placeholder
            preparedStatement.setInt(1, prodIdToInsert);
            preparedStatement.setDouble(2, sizeToInsert);
            preparedStatement.setInt(3, qtyToInsert);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Stock inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

    //update stock by id
    public static void getUpdateStock(int qty, int passId) throws SQLException {
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "UPDATE " + tableStock + " SET " +
                    stockColumn + " = ? " +
                    " WHERE " + stockIdColumn + " = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for all columns' placeholder
            preparedStatement.setInt(1, qty);
            preparedStatement.setInt(2, passId);

            // Execute the UPDATE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("No records updated!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
