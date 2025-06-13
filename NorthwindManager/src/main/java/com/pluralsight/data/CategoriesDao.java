package com.pluralsight.data;

import com.pluralsight.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {
    private BasicDataSource dataSource;

    public CategoriesDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> getCategories(){
        ArrayList<Category> result = new ArrayList<Category>();

        String query = """
                SELECT
                CategoryID,
                CategoryName
                FROM
                categories""";

        try (
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
                ResultSet queryResults = s.executeQuery();
        ){
            while(queryResults.next()){
                int categoryId = queryResults.getInt(1);
                String categoryName = queryResults.getString(2);
                Category category = new Category(categoryId, categoryName);
                result.add(category);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }



        return result;
    }

    public Category getCategoryByName(String categoryName){

        String query = """
                 SELECT
                CategoryID,
                CategoryName
                FROM
                categories
                WHERE CategoryName = ?
                """;
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement s = c.prepareStatement(query);
        ) {
            s.setString(1, categoryName);

            try (ResultSet queryResults = s.executeQuery()) {
                if (queryResults.next()) {

                    int categoryId = queryResults.getInt(1);
                    return new Category(categoryId, categoryName);

                }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}
