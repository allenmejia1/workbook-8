package com.pluralsight.data;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    private BasicDataSource dataSource;

    public ProductsDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getProducts(){
        ArrayList<Product> result = new ArrayList<Product>();

        String query = """
                SELECT
                ProductID,
                ProductName,
                SupplierID,
                CategoryID,
                UnitPrice
                FROM products;
                """;

        try (
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
                ResultSet queryResults = s.executeQuery();
        ){
            while(queryResults.next()){
                int productId = queryResults.getInt(1);
                String productName = queryResults.getString(2);
                int supplierId = queryResults.getInt(3);
                int categoryId = queryResults.getInt(4);
                double price = queryResults.getDouble(5);

                Product product = new Product(productId, productName, categoryId,supplierId, price);

                result.add(product);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }



        return result;
    }

    public List<Product> getProductsByCategory(Category category){
        ArrayList<Product> result = new ArrayList<Product>();

        String query = """
                SELECT
                ProductID,
                ProductName,
                SupplierID,
                CategoryID,
                UnitPrice
                FROM products
                WHERE CategoryID = ?;
                """;

        try (
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
        ){
            s.setInt(1,category.getId());

            try(ResultSet queryResults = s.executeQuery()){
                while(queryResults.next()){
                    int productId = queryResults.getInt(1);
                    String productName = queryResults.getString(2);
                    int supplierId = queryResults.getInt(3);
                    int categoryId = queryResults.getInt(4);
                    double price = queryResults.getDouble(5);

                    Product product = new Product(productId, productName, categoryId,supplierId, price);

                    result.add(product);
                }
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Product> getProductsByPrice(double minPrice, double maxPrice){
        ArrayList<Product> result = new ArrayList<Product>();

        String query = """
                SELECT
                ProductID,
                ProductName,
                SupplierID,
                CategoryID,
                UnitPrice
                FROM products
                WHERE UnitPrice >= ? AND UnitPrice <= ?;
                """;

        try (
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
        ){
            s.setDouble(1,minPrice);
            s.setDouble(2,maxPrice);

            try(ResultSet queryResults = s.executeQuery()){
                while(queryResults.next()){
                    int productId = queryResults.getInt(1);
                    String productName = queryResults.getString(2);
                    int supplierId = queryResults.getInt(3);
                    int categoryId = queryResults.getInt(4);
                    double price = queryResults.getDouble(5);

                    Product product = new Product(productId, productName, categoryId,supplierId, price);

                    result.add(product);
                }
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }



        return result;
    }

    public List<Product> getProductsBySupplier(Supplier supplier){
        return null;
    }

}