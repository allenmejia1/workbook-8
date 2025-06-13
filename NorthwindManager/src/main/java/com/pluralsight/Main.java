package com.pluralsight;

import com.pluralsight.data.CategoriesDao;
import com.pluralsight.data.ProductsDao;
import com.pluralsight.data.SuppliersDao;
import com.pluralsight.ui.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("You must run this with three arguments, <username> <password> <url>");
            System.exit(-1);
        }

        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        CategoriesDao categoriesDao = new CategoriesDao(basicDataSource);
        ProductsDao productsDao = new ProductsDao(basicDataSource);
        SuppliersDao suppliersDao = new SuppliersDao(basicDataSource);
        UserInterface ui = new UserInterface(categoriesDao, productsDao, suppliersDao);

        ui.display();

    }

    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setUrl(url);
        return bds;
    }
}