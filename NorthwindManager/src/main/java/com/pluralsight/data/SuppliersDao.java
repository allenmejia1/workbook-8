package com.pluralsight.data;

import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class SuppliersDao {
    private BasicDataSource dataSource;

    public SuppliersDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Supplier> getSuppliers(){
        return null;
    }



}