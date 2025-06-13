package com.pluralsight.ui;

import com.pluralsight.data.CategoriesDao;
import com.pluralsight.data.ProductsDao;
import com.pluralsight.data.SuppliersDao;
import com.pluralsight.models.Category;
import com.pluralsight.models.Employee;
import com.pluralsight.models.Product;

import java.util.List;

public class UserInterface {

    private Employee currentEmployee;
    private Console console;
    private CategoriesDao categoriesDao;
    private ProductsDao productsDao;
    private SuppliersDao suppliersDao;

    public UserInterface(CategoriesDao categoriesDao, ProductsDao productsDao, SuppliersDao suppliersDao) {
        this.console = new Console();
        this.categoriesDao = categoriesDao;
        this.productsDao = productsDao;
        this.suppliersDao = suppliersDao;
    }

    public  void display(){
        System.out.println("Welcome to the Northwind Manager!");
        currentEmployee = loginUser();
        System.out.println("Welcome, " + this.currentEmployee.getFirstName() + "!");


        showHomeMenu();
    }


    private Employee loginUser(){
        String s = console.promptForString("Just hit enter to login as Matt", true);
        Employee e = new Employee(1, "Matt", "Christenson");
        return e;
    }

    private void showHomeMenu() {


        while (true) {
            System.out.println();
            System.out.println();

            String[] menuOptions = {
                    "list product categories",
                    "list all products",
                    "list products by category",
                    "list products by price",
                    "list all suppliers",
                    "list products by supplier",
                    "exit"
            };

            int userChoice = console.promptForOption(menuOptions);
            switch (userChoice) {
                case 1:
                    listCategoriesAll();
                    break;
                case 2:
                    listProductsAll();
                    break;
                case 3:
                    listProductsByCategory();
                    break;
                case 4:
                    listProductsByPrice();
                    break;
                case 5:
                    listSuppliersAll();
                    break;
                case 6:
                    listProductsBySupplier();
                    break;
                case 7:
                    System.exit(0);
            }


            console.promptForString("Please press <ENTER> to continue...", true);
        }
    }

    private void listProductsBySupplier() {

    }

    private void listSuppliersAll() {
    }

    private void listProductsByPrice() {
        double minPrice = console.promptForDouble("Enter the smallest price:");
        double maxPrice = console.promptForDouble("Enter the largest price:");
        List<Product> products = productsDao.getProductsByPrice(minPrice, maxPrice);
        displayProducts(products);
    }

    private void listProductsByCategory() {
        String categoryName = console.promptForString("Enter a Category Name:");
        Category category = categoriesDao.getCategoryByName(categoryName);
        if(category != null){
            //System.out.println("you selected category id : " + category.getId());
            List<Product> products = productsDao.getProductsByCategory(category);
            displayProducts(products);
        }
        else{
            System.out.println("There is no such category!");
            return;
        }

    }

    private void listProductsAll() {
        List<Product> products = productsDao.getProducts();
        displayProducts(products);

    }

    private void listCategoriesAll() {
        List<Category> categories = categoriesDao.getCategories();
        if(categories.stream().count() <=0 ){
            System.out.println("No Categories found.");
        }
        else{
            categories.stream().forEach(c -> System.out.println(c.getCategoryName()));
        }

    }

    private void displayProducts(List<Product> products){
        if(products.stream().count() <=0 ){
            System.out.println("No Products found.");
        }
        else{
            products.stream().forEach(p -> System.out.println(p));
        }
    }
}